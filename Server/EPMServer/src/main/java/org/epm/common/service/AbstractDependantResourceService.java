package org.epm.common.service;

import jakarta.persistence.EntityNotFoundException;
import org.epm.common.model.IDTO;
import org.epm.common.model.IDependantEntity;
import org.epm.common.model.IEntity;
import org.epm.common.model.IMapper;
import org.epm.common.repository.IDependantRepository;
import org.epm.common.repository.IRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AbstractDependantResourceService
        <Entity extends IDependantEntity<ParentEntity>, ParentEntity extends IEntity, DTO extends IDTO>
        implements IDependantService<DTO> {

    public abstract IMapper<Entity, DTO> getMapper();
    protected abstract IRepository<ParentEntity> getParentRepository();
    protected abstract IDependantRepository<Entity> getRepository();

    @Override
    public abstract String getEntityName();
    @Override
    public abstract String getParentEntityName();

    @Override
    public DTO createEntity(final Integer parentUid, DTO dto) {
        ParentEntity parent = getParentRepository().findByUid(parentUid)
                .orElseThrow(this::throwParentNotFound);
        Entity entity = getMapper().toEntity(dto);
        entity.setParent(parent);
        if (!entity.isValidEntity()) {
            throwIllegalArgument(entity);
        }
        return getMapper().toDto(getRepository().save(entity));
    }

    @Override
    public DTO replaceEntity(final Integer parentUid, final Integer uid, final DTO dto)
            throws IllegalArgumentException, EntityNotFoundException {
        getParentRepository().findByUid(parentUid).orElseThrow(this::throwParentNotFound);
        Entity replaced = getRepository().findByParentUidAndUid(parentUid, uid)
                .orElseThrow(this::throwNotFound);
        Entity replacement = getMapper().toEntity(dto);
        replacement.setParent(replaced.getParent());
        replacement.setId(replaced.getId());
        if (!replacement.isValidEntity()) {
            throwIllegalArgument(replacement);
        }
        return getMapper().toDto(getRepository().save(replacement));
    }

    @Override
    public DTO updateEntity(final Integer parentUid, final Integer uid, final DTO dto)
            throws IllegalArgumentException, EntityNotFoundException {
        getParentRepository().findByUid(parentUid).orElseThrow(this::throwParentNotFound);
        Entity existingEntity = getRepository().findByParentUidAndUid(parentUid, uid)
                .orElseThrow(this::throwNotFound);
        getMapper().updateEntityFromDto(dto, existingEntity);
        if (!existingEntity.isValidEntity()) {
            throwIllegalArgument(existingEntity);
        }
        return getMapper().toDto(getRepository().save(existingEntity));
    }

    @Override
    public void deleteEntity(final Integer parentUid, final Integer uid) {
        getParentRepository().findByUid(parentUid).orElseThrow(this::throwParentNotFound);
        Entity entity = getRepository().findByParentUidAndUid(parentUid, uid).orElseThrow(this::throwNotFound);
        getRepository().deleteById(entity.getId());
    }

    @Override
    public Page<DTO> findAllByParentUid(final Integer parentUid, final Pageable pageable) {
        getParentRepository().findByUid(parentUid).orElseThrow(this::throwParentNotFound);
        Page<Entity> entities = getRepository().findAllByParentUid(parentUid, pageable);
        return entities.map(getMapper()::toDto);
    }

    @Override
    public DTO findByUidAndParentUid(final Integer uid, final Integer parentUid) {
        getParentRepository().findByUid(parentUid).orElseThrow(this::throwParentNotFound);
        Entity entity = getRepository().findByParentUidAndUid(parentUid, uid).orElseThrow(this::throwNotFound);
        return getMapper().toDto(entity);
    }

    private void throwIllegalArgument(Entity entity) {
        throw new IllegalArgumentException(getEntityName() + " has invalid values: " + entity.toString());
    }
    private EntityNotFoundException throwNotFound() {
        throw new EntityNotFoundException(getEntityName() + " not found");
    }
    private EntityNotFoundException throwParentNotFound() {
        throw new EntityNotFoundException(getParentEntityName() + " not found");
    }
}
