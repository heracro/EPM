package org.epm.changeLog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epm.changeLog.model.ChangeLogDTO;
import org.epm.changeLog.service.ChangeLogService;
import org.epm.common.controller.AbstractRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/projects/{parentUid}/changelogs")
@RequiredArgsConstructor
public class ChangeLogController extends AbstractRestController<ChangeLogDTO> {

    private final ChangeLogService changeLogService;

    @Override
    public ChangeLogService getEntityService() {
        return changeLogService;
    }

}
