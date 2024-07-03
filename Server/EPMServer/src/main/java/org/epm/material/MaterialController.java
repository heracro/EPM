package org.epm.material;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.epm.material.model.Material;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    //.POST /materials -> create material
    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        if (material.isValidEntity()) {
            Material m = materialService.createMaterial(material);
            if (m != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(m);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    //.PUT /materials/{mid} -> replace material
    @PutMapping("/{mid}")
    public ResponseEntity<Material> replaceMaterial(
            @PathVariable Long mid, @RequestBody Material material) {
        if (material.getId() != null) {
            return ResponseEntity.badRequest().body(null);
        }
        Material m = materialService.replaceMaterial(mid, material);
        if (m == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(m);
    }
}
