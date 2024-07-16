package org.epm.common.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.AbstractModuleData;
import org.epm.common.model.IDTO;
import org.epm.common.service.IService;
import org.epm.common.utils.FontColor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.epm.common.utils.ConsoleStringUtils.fontColor;

@Slf4j
public abstract class AbstractRestController<DTO extends AbstractModuleData & IDTO> {

    public abstract IService<DTO> getEntityService();

    private void info(String format, Object... args) {
        log.info(fontColor(FontColor.BRIGHT_BLUE, format, args));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final DTO dto) {
        info("Creating entity: {}", dto);
        try {
            DTO createdDto = getEntityService().createEntity(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Failed to create "
                    + getEntityService().getEntityName() + ": " + e.getMessage());
        }
    }

    @PutMapping("/{uid}")
    public ResponseEntity<?> replace(
            @PathVariable final Integer uid,
            @RequestBody final DTO dto) {
        info("Replacing {} uid {} with new entity: {}", getEntityService().getEntityName(), uid, dto);
        try {
            DTO replaced = getEntityService().replaceEntity(uid, dto);
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
            @RequestParam(defaultValue = "uid") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        info("Finding all {}s", getEntityService().getEntityName());
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));
        return ResponseEntity.ok(getEntityService().findAll(pageable));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<DTO> findByUid(@PathVariable Integer uid) {
        info("Finding {} by uid {}", getEntityService().getEntityName(), uid);
        try {
            return ResponseEntity.ok(getEntityService().findByUid(uid));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{uid}")
    public ResponseEntity<?> updateMaterial(
            @PathVariable Integer uid, @RequestBody DTO dto) {
        info("Updating {} uid {} with new entity: {}", getEntityService().getEntityName(), uid, dto);
        if (!dto.isValidDTO()) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }
        try {
            DTO updatedEntity = getEntityService().updateEntity(uid, dto);
            return ResponseEntity.ok(updatedEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Request body contains invalid arguments");
        }
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Integer uid) {
        info("Deleting {} uid {}", getEntityService().getEntityName(), uid);
        try {
            getEntityService().deleteEntity(uid);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
