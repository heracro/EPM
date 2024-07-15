package org.epm.bom.controller;

import lombok.RequiredArgsConstructor;
import org.epm.bom.model.BomDTO;
import org.epm.bom.service.BomService;
import org.epm.common.controller.AbstractDependantResourceRestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{parentUid}/boms")
@RequiredArgsConstructor
public class BomController extends AbstractDependantResourceRestController<BomDTO> {

    private final BomService bomService;

    @Override
    public BomService getEntityService() {
        return bomService;
    }

}
