package org.epm.common.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.AbstractModuleData;
import org.epm.common.model.IDTO;
import org.epm.common.service.IDependantService;
import org.epm.common.utils.FontColor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.epm.common.utils.ConsoleStringUtils.fontColor;

@Slf4j
public abstract class AbstractDependantResourceRestController
        <DTO extends AbstractModuleData & IDTO> {

    void info(String format, Object... args) {
        log.info(fontColor(FontColor.BRIGHT_GREEN, format, args));
    }

    public abstract IDependantService<DTO> getEntityService(); //dependant

    @PostMapping
    public ResponseEntity<?> create(
            @PathVariable final Integer parentUid,
            @RequestBody final DTO dto) {

        info("Creating entity: {} under parent uid {}", dto, parentUid);
        try {
            DTO createdDto = getEntityService().createEntity(parentUid, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Failed to create "
                    + getEntityService().getEntityName() + ": " + e.getMessage());
        }
    }

    @PutMapping("/{uid}")
    public ResponseEntity<?> replace(
            @PathVariable final Integer parentUid,
            @PathVariable final Integer uid,
            @RequestBody final DTO dto) {

        info("Replacing {} uid {} under parentUid {} with new entity: {}",
                getEntityService().getEntityName(), uid, parentUid, dto);
        try {
            DTO replaced = getEntityService().replaceEntity(parentUid, uid, dto);
            return ResponseEntity.ok(replaced);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{uid}")
    public ResponseEntity<?> updateMaterial(
            @PathVariable final Integer parentUid,
            @PathVariable final Integer uid,
            @RequestBody final DTO dto) {

        info("Updating {} uid {} with new entity: {}",
                getEntityService().getEntityName(), uid, dto);
        if (!dto.isValidDTO()) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }
        try {
            DTO updatedEntity = getEntityService().updateEntity(parentUid, uid, dto);
            return ResponseEntity.ok(updatedEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Request body contains invalid arguments");
        }
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<?> deleteMaterial(
            @PathVariable final Integer parentUid,
            @PathVariable Integer uid) {

        info("Deleting {} uid {}", getEntityService().getEntityName(), uid);
        try {
            getEntityService().deleteEntity(parentUid, uid);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<DTO>> findAll(
            @PathVariable final Integer parentUid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        info("Finding all {}s", getEntityService().getEntityName());
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getEntityService().findAllByParentUid(parentUid, pageable));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<DTO> findByUid(
            @PathVariable final Integer parentUid,
            @PathVariable final Integer uid) {

        info("Finding {} by uid {}", getEntityService().getEntityName(), uid);
        try {
            return ResponseEntity.ok(getEntityService().findByUidAndParentUid(parentUid, uid));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
