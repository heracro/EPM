package org.epm.changeLog.service;

import lombok.RequiredArgsConstructor;
import org.epm.changeLog.model.ChangeLogDTO;
import org.epm.changeLog.model.ChangeLogEntity;
import org.epm.changeLog.model.ChangeLogMapper;
import org.epm.changeLog.repository.ChangeLogRepository;
import org.epm.common.service.AbstractDependantResourceService;
import org.epm.project.model.ProjectEntity;
import org.epm.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeLogService extends AbstractDependantResourceService<ChangeLogEntity, ProjectEntity, ChangeLogDTO> {

    private final ProjectRepository projectRepository;
    private final ChangeLogRepository changeLogRepository;
    private final ChangeLogMapper changeLogMapper;

    @Override
    public ChangeLogMapper getMapper() {
        return changeLogMapper;
    }

    @Override
    protected ProjectRepository getParentRepository() {
        return projectRepository;
    }

    @Override
    protected ChangeLogRepository getRepository() {
        return changeLogRepository;
    }

    @Override
    public String getEntityName() {
        return "ChangeLog";
    }

    @Override
    public String getParentEntityName() {
        return "Project";
    }
}
