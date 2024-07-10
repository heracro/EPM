package org.epm.common.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.epm.common.model.IDTO;
import org.epm.common.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public abstract class AbstractEntityController<DTO extends IDTO> {

    protected final IService<DTO> entityService;

    public abstract String getMapping();

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final DTO dto) {
        try {
            DTO createdDto = entityService.createEntity(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
        } catch (IllegalArgumentException ignored) {}
        return ResponseEntity.badRequest()
                .body("Failed to create " + entityService.getEntityName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replace(
            @PathVariable final Long id,
            @RequestBody final DTO dto) {
        try {
            DTO replaced = entityService.replaceEntity(id, dto);
            return ResponseEntity.ok(replaced);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<DTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        return ResponseEntity.ok(entityService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(entityService.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateMaterial(
            @PathVariable Long id, @RequestBody DTO dto) {
        if (!dto.isValidDTO()) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }
        try {
            DTO updatedEntity = entityService.updateEntity(id, dto);
            return ResponseEntity.ok(updatedEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Request body contains invalid arguments");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long id) {
        try {
            entityService.deleteEntity(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
