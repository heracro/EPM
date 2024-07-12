package org.epm.changeLog.controller;

import lombok.extern.slf4j.Slf4j;
import org.epm.changeLog.model.ChangeLogDTO;
import org.epm.changeLog.service.ChangeLogService;
import org.epm.common.controller.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/projects/{projectId}/changelogs")
public class ChangeLogController extends AbstractRestController<ChangeLogDTO> {

    @Autowired
    public ChangeLogController(ChangeLogService changeLogService) {
        super(changeLogService);
    }

    @Override
    public String getMapping() {
        return "/projects/{projectId}/changelogs";
    }
}
