package org.epm.changeLog.service;

import org.epm.changeLog.model.ChangeLogDTO;
import org.epm.changeLog.model.ChangeLogEntity;
import org.epm.changeLog.model.ChangeLogMapper;
import org.epm.changeLog.repository.ChangeLogRepository;
import org.epm.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeLogService extends AbstractService<ChangeLogEntity, ChangeLogDTO> {

    private final ChangeLogRepository changeLogRepository;

    @Autowired
    public ChangeLogService(ChangeLogRepository changeLogRepository, ChangeLogMapper changeLogMapper) {
        super(changeLogMapper);
        this.changeLogRepository = changeLogRepository;
    }

    @Override
    protected ChangeLogRepository getRepository() {
        return changeLogRepository;
    }

    @Override
    public String getEntityName() {
        return "ChangeLog";
    }
}
