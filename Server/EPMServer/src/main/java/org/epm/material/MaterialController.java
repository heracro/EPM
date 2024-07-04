package org.epm.material;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.Config;
import org.epm.delivery.model.Delivery;
import org.epm.material.model.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    //.POST /materials -> create material
    @PostMapping
    public ResponseEntity<?> createMaterial(@RequestBody Material material) {
        Material m = materialService.createMaterial(material);
        if (m.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(m);
        }
        return ResponseEntity.badRequest().body("Failed to create material");
    }

    //.POST /materials/bulk
    @PostMapping("/bulk")
    public ResponseEntity<?> createMaterials(@RequestBody List<Material> materials) {
        if (materials == null || materials.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }
        List<Material> createdMaterials = materialService.createMaterials(materials);
        if (createdMaterials == null || createdMaterials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create materials");
        } else if (materials.size() != createdMaterials.size()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(createdMaterials);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMaterials);
    }

    //.PUT /materials/{mid} -> replace material
    @PutMapping("/{id}")
    public ResponseEntity<?> replaceMaterial(@PathVariable Long id, @RequestBody Material material) {
        if (material.getId() != null && !material.getId().equals(id) || !material.isValidEntity()) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }
        Material replacedMaterial = materialService.replaceMaterial(id, material);
        if (replacedMaterial == null || replacedMaterial.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(replacedMaterial);
    }

    //.GET /materials -> get all materials [page, size, sort]
    @GetMapping
    public ResponseEntity<Page<Material>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "" + Config.MATERIALS_PER_PAGE) int size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        Page<Material> materials = materialService.findAll(pageable);
        return ResponseEntity.ok(materials);
    }

    //.GET /materials/{mid} -> find by id
    @GetMapping("/{id}")
    public ResponseEntity<Material> findById(@RequestParam Long id) {
        Optional<Material> material = materialService.findById(id);
        return material.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //.GET /materials/{mid}/deliveries -> find all deliveries for given mid
    @GetMapping("/{id}/deliveries")
    public ResponseEntity<?> findDeliveriesForMaterial(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "" + Config.DELIVERIES_PER_PAGE) int size) {
        try {
            Page<Delivery> deliveries = materialService.findDeliveriesForMaterial(id, PageRequest.of(page, size));
            return ResponseEntity.ok(deliveries);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //.PATCH /materials/{mid} -> update material
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateMaterial(@PathVariable Long id, @RequestBody Material material) {
        if (material.getId() != null && !material.getId().equals(id) || !material.isValidDTO()) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }
        try {
            Material updatedMaterial = materialService.updateMaterial(id, material);
            if (updatedMaterial == null || updatedMaterial.getId() == null) {
                return ResponseEntity.internalServerError().body("Oops, something went wrong...");
            }
            return ResponseEntity.ok(updatedMaterial);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Request body contains invalid arguments");
        }
    }

    //.DELETE /materials/{mid} -> delete material.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long id) {
        try {
            materialService.deleteMaterial(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred");
        }
    }



}
