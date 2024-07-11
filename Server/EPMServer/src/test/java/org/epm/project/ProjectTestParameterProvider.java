package org.epm.project;

import jakarta.validation.constraints.NotNull;
import org.epm.AbstractTestParameterProvider;
import org.epm.common.configuration.Config;
import org.epm.common.utils.RandomUtils;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectEntity;
import org.epm.project.model.ProjectMapper;
import org.epm.project.enums.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProjectTestParameterProvider
        extends AbstractTestParameterProvider<ProjectEntity, ProjectDTO> {

    @Autowired
    private ProjectMapper mapper;

    private final ProjectRandomizer randomizer = new ProjectRandomizer();

    public static int DTO_ATTR_COUNT = 12;

    @Override
    protected int getDTOAttrCount() {
        return DTO_ATTR_COUNT;
    }

    @Override
    protected ProjectMapper getMapper() {
        return mapper;
    }

    @Override
    protected ProjectEntity randomValidEntity() {
        return randomizer.provideValidEntity();
    }

    @Override
    protected ProjectDTO emptyDTO() {
        return randomizer.provideEmptyDTO();
    }

    @Override
    protected ProjectDTO provideSingleAttribute(@NotNull ProjectDTO dto, int caseNumber) {
        ProjectRandomizer randomizer = new ProjectRandomizer();
        switch (caseNumber % getDTOAttrCount()) {
            case 0 -> randomizer.setRandomName(dto);
            case 1 -> randomizer.setRandomBody(dto);
            case 2 -> randomizer.setRandomPlannedStartDate(dto);
            case 3 -> randomizer.setRandomPlannedEndDate(dto);
            case 4 -> randomizer.setRandomRealStartDate(dto);
            case 5 -> randomizer.setRandomRealEndDate(dto);
            case 6 -> randomizer.setRandomWorkHoursCount(dto);
            case 7 -> randomizer.setRandomCause(dto);
            case 8 -> randomizer.setRandomMaterialsReadyDate(dto);
            case 9 -> randomizer.setRandomProjectLocationUrl(dto);
            case 10 -> randomizer.setRandomLocationType(dto);
            case 11 -> randomizer.setRandomStatus(dto);
            default -> throw new IllegalStateException("Unexpected value: " + caseNumber);
        }
        return dto;
    }

    @Override
    protected ProjectDTO breakSingleAttribute(@NotNull ProjectDTO dto, int caseNumber) {
        switch (caseNumber % getDTOAttrCount()) {
            case 0 -> dto.setName("" + RandomUtils.randomInt(100));
            case 1 -> dto.setBody("" + RandomUtils.randomInt(100));
            case 2 -> {
                dto.setPlannedStartDate(LocalDate.now());
                dto.setPlannedEndDate(LocalDate.now().minusDays(RandomUtils.randomInt(180) + 1));
            }
            case 3 -> {
                dto.setPlannedStartDate(null);
                dto.setPlannedEndDate(RandomUtils
                        .randomDate(Config.BIG_BANG_DATE, Config.FAR_FAR_DATE));
            }
            case 4 -> {
                dto.setRealStartDate(LocalDate.now());
                dto.setRealEndDate(LocalDate.now().minusDays(RandomUtils.randomInt(180) + 1));
            }
            case 5 -> {
                dto.setRealStartDate(null);
                dto.setRealEndDate(RandomUtils
                        .randomDate(Config.BIG_BANG_DATE, Config.FAR_FAR_DATE));
            }
            case 6 -> dto.setWorkingHoursCount((-1) * RandomUtils.randomInt(100));
            case 7 -> dto.setCause(null);
            case 8 -> {
                dto.setMaterialsReadyDate(Config.FAR_FAR_DATE);
                dto.setStatus(ProjectStatus.ONGOING);
            }
            case 9 -> dto.setProjectLocationUrl("." + RandomUtils.randomInt(10));
            case 10 -> dto.setLocationType(null);
            case 11 -> dto.setStatus(null);
        }
        return dto;
    }

}
