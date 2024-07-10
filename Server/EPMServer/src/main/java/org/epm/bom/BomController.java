package org.epm.bom;

import org.epm.bom.model.BomDTO;
import org.epm.common.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{projectId}/boms")
public class BomController extends AbstractEntityController<BomDTO> {

    public BomController(BomService bomService) {
        super(bomService);
    }


    @Override
    public String getMapping() {
        return "/projects/{projectId}/boms";
    }
}
