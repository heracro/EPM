package org.epm.material.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.controller.AbstractRestController;
import org.epm.material.model.MaterialDTO;
import org.epm.material.service.MaterialService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController extends AbstractRestController<MaterialDTO> {

    private final MaterialService service;

    @Override
    public MaterialService getEntityService() {
        return service;
    }

}
