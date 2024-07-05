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
public abstract class AbstractEntityService<Entity extends IEntity, DTO extends IDTO>
        implements IService<DTO> {
    protected final IMapper<Entity, DTO> mapper;

    @Override
    public DTO createEntity(final DTO dto) throws IllegalArgumentException {
        Entity entity = mapper.toEntity(dto);
        if (!entity.isValidEntity()) {
            throw throwIllegalArgument();
        }
        return mapper.toDto(getRepository().save(entity));
    }

    @Override
    public DTO replaceEntity(final Long id, final DTO dto)
            throws IllegalArgumentException, EntityNotFoundException {
        getRepository().findById(id).orElseThrow(this::throwNotFound);
        Entity replacementEntity = mapper.toEntity(dto);
        replacementEntity.setId(id);
        if(!replacementEntity.isValidEntity()) {
            throw throwIllegalArgument();
        }
        return mapper.toDto(getRepository().save(replacementEntity));
    }

    @Override
    public Page<DTO> findAll(final Pageable pageable) {
        Page<Entity> entityPage = getRepository().findAll(pageable);
        return entityPage.map(mapper::toDto);
    }

    @Override
    public DTO findById(final Long id) throws EntityNotFoundException {
        Optional<Entity> optionalEntity = getRepository().findById(id);
        return optionalEntity.map(mapper::toDto).orElseThrow(this::throwNotFound);
    }

    @Override
    public DTO updateEntity(final Long id, final DTO dto)
            throws EntityNotFoundException, IllegalArgumentException {
        Entity existingEntity = getRepository().findById(id).orElseThrow(this::throwNotFound);
        mapper.updateEntityFromDto(dto, existingEntity);
        if (!existingEntity.isValidEntity()) {
            throw throwIllegalArgument();
        }
        return mapper.toDto(getRepository().save(existingEntity));
    }

    @Override
    public void deleteEntity(final Long id) throws EntityNotFoundException {
        Entity entity = getRepository().findById(id).orElseThrow(this::throwNotFound);
        getRepository().deleteById(id);
    }

    private IllegalArgumentException throwIllegalArgument() {
        return new IllegalArgumentException(getEntityName() + " has invalid values");
    }
    private EntityNotFoundException throwNotFound() {
        return new EntityNotFoundException(getEntityName() + " not found");
    }

    protected abstract IRepository<Entity> getRepository();
    @Override
    public abstract String getEntityName();
}
