package org.epm.common.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.epm.common.model.IDTO;
import org.epm.common.model.IEntity;
import org.epm.common.model.IMapper;
import org.epm.common.repository.IRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractService<Entity extends IEntity, DTO extends IDTO>
        implements IService<DTO> {

    public abstract IMapper<Entity, DTO> getMapper();
    protected abstract IRepository<Entity> getRepository();
    @Override
    public abstract String getEntityName();

    @Override
    public DTO createEntity(final DTO dto) throws IllegalArgumentException {
        Entity entity = getMapper().toEntity(dto);
        if (!entity.isValidEntity()) {
            throwIllegalArgument(entity);
        }
        return getMapper().toDto(getRepository().save(entity));
    }

    @Override
    public DTO replaceEntity(final Integer uid, final DTO dto)
            throws IllegalArgumentException, EntityNotFoundException {
        Entity replacedEntity = getRepository().findByUid(uid).orElseThrow(this::throwNotFound);
        Entity replacementEntity = getMapper().toEntity(dto);
        replacementEntity.setUid(replacedEntity.getUid());
        replacementEntity.setId(replacedEntity.getId());
        if(!replacementEntity.isValidEntity()) {
            throwIllegalArgument(replacedEntity);
        }
        return getMapper().toDto(getRepository().save(replacementEntity));
    }

    @Override
    public DTO updateEntity(final Integer uid, final DTO dto)
            throws EntityNotFoundException, IllegalArgumentException {
        Entity existingEntity = getRepository().findByUid(uid).orElseThrow(this::throwNotFound);
        getMapper().updateEntityFromDto(dto, existingEntity);
        if (!existingEntity.isValidEntity()) {
            throwIllegalArgument(existingEntity);
        }
        return getMapper().toDto(getRepository().save(existingEntity));
    }

    @Override
    public void deleteEntity(final Integer uid) throws EntityNotFoundException {
        Entity entity = getRepository().findByUid(uid).orElseThrow(this::throwNotFound);
        getRepository().deleteById(entity.getId());
    }

    @Override
    public Page<DTO> findAll(final Pageable pageable) {
        Page<Entity> entityPage = getRepository().findAll(pageable);
        return entityPage.map(getMapper()::toDto);
    }

    @Override
    public DTO findByUid(final Integer uid) throws EntityNotFoundException {
        Optional<Entity> optionalEntity = getRepository().findByUid(uid);
        return optionalEntity.map(getMapper()::toDto).orElseThrow(this::throwNotFound);
    }

    private void throwIllegalArgument(Entity entity) {
        throw new IllegalArgumentException(getEntityName() + " has invalid values: " + entity.toString());
    }
    private EntityNotFoundException throwNotFound() {
        throw new EntityNotFoundException(getEntityName() + " not found");
    }
}
