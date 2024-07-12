package org.epm.bom.controller;

import org.epm.bom.model.BomDTO;
import org.epm.bom.service.BomService;
import org.epm.common.controller.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{projectId}/boms")
public class BomController extends AbstractRestController<BomDTO> {

    @Autowired
    public BomController(BomService bomService) {
        super(bomService);
    }


    @Override
    public String getMapping() {
        return "/projects/{projectId}/boms";
    }
}
