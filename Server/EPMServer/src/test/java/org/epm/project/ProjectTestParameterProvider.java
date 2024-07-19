package org.epm.project;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.epm.AbstractMainTestParameterProvider;
import org.epm.common.utils.FontColor;
import org.epm.project.enums.LocationType;
import org.epm.project.enums.ProjectCause;
import org.epm.project.enums.ProjectStatus;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectEntity;
import org.epm.project.model.ProjectMapper;
import org.epm.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.epm.common.utils.ConsoleStringUtils.fontColor;

@Slf4j
@Component
public class ProjectTestParameterProvider
        extends AbstractMainTestParameterProvider<ProjectEntity, ProjectDTO> {

    private final int OBJECT_COUNT_IN_VALID_STREAMS = 25 * RUN_COUNT;
    private final Random random = new Random();
    private final int ALL_ATTR_COUNT = 13;
    private final int CORRUPTIBLE_ATTR_COUNT = 10;
    private final int RUNS = ALL_ATTR_COUNT * CORRUPTIBLE_ATTR_COUNT * RUN_COUNT;

    private void info(String format, Object... args) {
        log.info(fontColor(FontColor.BRIGHT_GREEN, format, args));
    }

    @Autowired
    private ProjectMapper mapper;

    @Autowired
    private ProjectRepository repository;

    private final ProjectRandomizer randomizer = new ProjectRandomizer();

    @Override
    protected Integer provideUidOfExistingEntity() {
        info("provideUidOfExistingEntity()");
        ProjectEntity project = repository.findFirstByOrderByUidAsc().orElseThrow(
                () -> new IllegalStateException("Database is empty!")
        );
        info("provideUidOfExistingEntity() --> getting ID from: {}", project);
        return project.getUid();
    }

    @Override
    protected Integer provideUidOfInvalidEntity() {
        return 0;
    }

    @Override
    protected Integer provideUidOfUnconstrainedEntity() {
        return 0;
    }

    @Override
    protected Stream<ProjectDTO> provideFewDTOsWhichAreValidEntity() {
        return validProjects.stream().map(mapper::toDto);
    }

    @Override
    protected Stream<ProjectDTO> provideFewDTOsWhichAreInvalidEntity() {
        return Stream.empty();
    }

    @Override
    protected Stream<ProjectDTO> provideDTOsWithSingleValidAttribute() {
        return Stream.empty();
    }

    @Override
    protected Stream<ProjectDTO> provideDTOsWithSingleInvalidAttribute() {
        return Stream.empty();
    }

    protected ProjectDTO provideSingleAttribute(@NotNull ProjectDTO dto, int caseNumber) {
        return dto;
    }

    protected ProjectDTO breakSingleAttribute(@NotNull ProjectDTO dto, int caseNumber) {
        return dto;
    }

    List<ProjectEntity> validProjects = List.of(
            ProjectEntity.builder()
                    .name("Projekt Planowany 1")
                    .body("To jest przykładowy opis projektu, który jest planowany.")
                    .plannedStartDate(LocalDate.of(2024, 7, 20))
                    .plannedEndDate(LocalDate.of(2024, 8, 20))
                    .workingHoursCount(100)
                    .workingHoursPlanned(120)
                    .projectLocation("Lokalizacja Projektu 1")
                    .locationType(LocationType.LOCAL)
                    .status(ProjectStatus.PLANNED)
                    .cause(ProjectCause.HOBBY)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt Planowany 2")
                    .body("To jest przykładowy opis projektu, który jest planowany.")
                    .plannedStartDate(LocalDate.of(2024, 9, 1))
                    .plannedEndDate(LocalDate.of(2024, 10, 1))
                    .workingHoursCount(80)
                    .workingHoursPlanned(90)
                    .projectLocation("Lokalizacja Projektu 2")
                    .locationType(LocationType.REMOTE_HTTP)
                    .status(ProjectStatus.PLANNED)
                    .cause(ProjectCause.EDUCATIONAL)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt Oczekujący na Materiały 1")
                    .body("To jest przykładowy opis projektu oczekującego na materiały.")
                    .plannedStartDate(LocalDate.of(2024, 5, 10))
                    .plannedEndDate(LocalDate.of(2024, 6, 10))
                    .materialsReadyDate(LocalDate.of(2024, 7, 25))
                    .workingHoursCount(60)
                    .workingHoursPlanned(70)
                    .projectLocation("Lokalizacja Projektu 3")
                    .locationType(LocationType.REMOTE_HTTPS)
                    .status(ProjectStatus.AWAITING_MATERIALS)
                    .cause(ProjectCause.ORDERED_COMMERCIAL)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt Oczekujący na Materiały 2")
                    .body("To jest przykładowy opis projektu oczekującego na materiały.")
                    .plannedStartDate(LocalDate.of(2024, 4, 15))
                    .plannedEndDate(LocalDate.of(2024, 5, 15))
                    .materialsReadyDate(LocalDate.of(2024, 7, 26))
                    .workingHoursCount(50)
                    .workingHoursPlanned(60)
                    .projectLocation("Lokalizacja Projektu 4")
                    .locationType(LocationType.REMOTE_FTP)
                    .status(ProjectStatus.AWAITING_MATERIALS)
                    .cause(ProjectCause.ORDERED_NON_COMMERCIAL)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt Gotowy do rozpoczęcia 1")
                    .body("To jest przykładowy opis projektu gotowego do rozpoczęcia.")
                    .plannedStartDate(LocalDate.of(2024, 3, 20))
                    .plannedEndDate(LocalDate.of(2024, 4, 20))
                    .materialsReadyDate(LocalDate.now().minusDays(1))
                    .workingHoursCount(30)
                    .workingHoursPlanned(40)
                    .projectLocation("Lokalizacja Projektu 5")
                    .locationType(LocationType.SHELF)
                    .status(ProjectStatus.READY)
                    .cause(ProjectCause.GIFT)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt Gotowy do rozpoczęcia 2")
                    .body("To jest przykładowy opis projektu gotowego do rozpoczęcia.")
                    .plannedStartDate(LocalDate.of(2024, 1, 10))
                    .plannedEndDate(LocalDate.of(2024, 2, 10))
                    .materialsReadyDate(LocalDate.now())
                    .workingHoursCount(40)
                    .workingHoursPlanned(50)
                    .projectLocation("Lokalizacja Projektu 6")
                    .locationType(LocationType.LOCAL)
                    .status(ProjectStatus.READY)
                    .cause(ProjectCause.HOBBY)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt W Toku 1")
                    .body("To jest przykładowy opis projektu będącego w toku.")
                    .plannedStartDate(LocalDate.of(2024, 6, 1))
                    .plannedEndDate(LocalDate.of(2024, 7, 1))
                    .realStartDate(LocalDate.of(2024, 6, 2))
                    .materialsReadyDate(LocalDate.now().minusDays(1))
                    .workingHoursCount(90)
                    .workingHoursPlanned(100)
                    .projectLocation("Lokalizacja Projektu 7")
                    .locationType(LocationType.REMOTE_HTTP)
                    .status(ProjectStatus.ONGOING)
                    .cause(ProjectCause.HOME_IMPROVEMENT)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt W Toku 2")
                    .body("To jest przykładowy opis projektu będącego w toku.")
                    .plannedStartDate(LocalDate.of(2024, 8, 15))
                    .plannedEndDate(LocalDate.of(2024, 9, 15))
                    .realStartDate(LocalDate.of(2024, 8, 16))
                    .materialsReadyDate(LocalDate.now().minusDays(1))
                    .workingHoursCount(110)
                    .workingHoursPlanned(120)
                    .projectLocation("Lokalizacja Projektu 8")
                    .locationType(LocationType.REMOTE_HTTPS)
                    .status(ProjectStatus.ONGOING)
                    .cause(ProjectCause.ORDERED_COMMERCIAL)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt Zakończony 1")
                    .body("To jest przykładowy opis zakończonego projektu.")
                    .plannedStartDate(LocalDate.of(2024, 2, 1))
                    .plannedEndDate(LocalDate.of(2024, 3, 1))
                    .realStartDate(LocalDate.of(2024, 2, 5))
                    .realEndDate(LocalDate.of(2024, 2, 28))
                    .materialsReadyDate(LocalDate.of(2024, 1, 20))
                    .workingHoursCount(100)
                    .workingHoursPlanned(110)
                    .projectLocation("Lokalizacja Projektu 9")
                    .locationType(LocationType.REMOTE_FTP)
                    .status(ProjectStatus.COMPLETED)
                    .cause(ProjectCause.EDUCATIONAL)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt Zakończony 2")
                    .body("To jest przykładowy opis zakończonego projektu.")
                    .plannedStartDate(LocalDate.of(2024, 5, 1))
                    .plannedEndDate(LocalDate.of(2024, 6, 1))
                    .realStartDate(LocalDate.of(2024, 5, 5))
                    .realEndDate(LocalDate.of(2024, 5, 30))
                    .materialsReadyDate(LocalDate.of(2024, 4, 20))
                    .workingHoursCount(80)
                    .workingHoursPlanned(90)
                    .projectLocation("Lokalizacja Projektu 10")
                    .locationType(LocationType.LOCAL)
                    .status(ProjectStatus.COMPLETED)
                    .cause(ProjectCause.ORDERED_NON_COMMERCIAL)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt Anulowany 1")
                    .body("To jest przykładowy opis anulowanego projektu.")
                    .plannedStartDate(LocalDate.of(2024, 1, 1))
                    .realStartDate(LocalDate.of(2024, 1, 2))
                    .materialsReadyDate(LocalDate.of(2023, 12, 25))
                    .workingHoursCount(70)
                    .workingHoursPlanned(80)
                    .projectLocation("Lokalizacja Projektu 11")
                    .locationType(LocationType.REMOTE_HTTP)
                    .status(ProjectStatus.CANCELLED)
                    .cause(ProjectCause.GIFT)
                    .build(),

            ProjectEntity.builder()
                    .name("Projekt Anulowany 2")
                    .body("To jest przykładowy opis anulowanego projektu.")
                    .plannedStartDate(LocalDate.of(2024, 3, 1))
                    .plannedEndDate(LocalDate.of(2024, 3, 30))
                    .materialsReadyDate(LocalDate.of(2024, 2, 25))
                    .workingHoursCount(90)
                    .workingHoursPlanned(100)
                    .projectLocation("Lokalizacja Projektu 12")
                    .locationType(LocationType.REMOTE_HTTPS)
                    .status(ProjectStatus.CANCELLED)
                    .cause(ProjectCause.HOME_IMPROVEMENT)
                    .build()
    );

}
