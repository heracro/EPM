package org.epm.material;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.Config;
import org.epm.common.controller.AbstractEntityController;
import org.epm.common.service.IService;
import org.epm.delivery.model.DeliveryDTO;
import org.epm.material.model.MaterialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/materials")
public class MaterialController extends AbstractEntityController<MaterialDTO> {
    private final MaterialService materialService;

    @Autowired
    public MaterialController(IService<MaterialDTO> entityService, MaterialService materialService) {
        super(entityService);
        this.materialService = materialService;
    }

    //.GET /materials/{mid}/deliveries -> find all deliveries for given mid
    @GetMapping("/{id}/deliveries")
    public ResponseEntity<?> findDeliveriesForMaterial(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "" + Config.DELIVERIES_PER_PAGE) int size) {
        try {
            Page<DeliveryDTO> deliveries = materialService.findDeliveriesForMaterial(id, PageRequest.of(page, size));
            return ResponseEntity.ok(deliveries);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
