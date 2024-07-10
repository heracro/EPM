package org.epm.project;

import jakarta.validation.constraints.NotNull;
import org.epm.AbstractTestParameterProvider;
import org.epm.common.Config;
import org.epm.common.utils.RandomUtils;
import org.epm.project.model.ProjectDTO;
import org.epm.project.model.ProjectEntity;
import org.epm.project.model.ProjectMapper;
import org.epm.project.model.enums.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProjectTestParameterProvider
        extends AbstractTestParameterProvider<ProjectEntity, ProjectDTO> {

    @Autowired
    private ProjectMapper mapper;
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
    protected ProjectEntity randomInstance() {
        return ProjectEntity.randomInstance();
    }

    @Override
    protected ProjectDTO emptyInstance() {
        return ProjectDTO.emptyInstance();
    }

    @Override
    protected ProjectDTO provideSingleAttribute(@NotNull ProjectDTO dto, int caseNumber) {
        switch (caseNumber % getDTOAttrCount()) {
            case 0 -> dto.setRandomName();
            case 1 -> dto.setRandomBody();
            case 2 -> dto.setRandomPlannedStartDate();
            case 3 -> dto.setRandomPlannedEndDate();
            case 4 -> dto.setRandomRealStartDate();
            case 5 -> dto.setRandomRealEndDate();
            case 6 -> dto.setRandomWorkHoursCount();
            case 7 -> dto.setRandomCause();
            case 8 -> dto.setRandomMaterialsReadyDate();
            case 9 -> dto.setRandomProjectLocationUrl();
            case 10 -> dto.setRandomLocationType();
            case 11 -> dto.setRandomStatus();
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
