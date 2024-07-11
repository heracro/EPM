package org.epm.changeLog.controller;

import lombok.extern.slf4j.Slf4j;
import org.epm.changeLog.model.ChangeLogDTO;
import org.epm.changeLog.service.ChangeLogService;
import org.epm.common.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/projects/{projectId}/changelogs")
public class ChangeLogController extends AbstractEntityController<ChangeLogDTO> {

    public ChangeLogController(ChangeLogService changeLogService) {
        super(changeLogService);
    }

    @Override
    public String getMapping() {
        return "/projects/{projectId}/changelogs";
    }
}
