package org.epm.bom;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{projectId}/boms")
@RequiredArgsConstructor
public class BomController {
    private final BomService bomService;
}
