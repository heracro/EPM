package org.epm.common.repository;

import lombok.NonNull;
import org.epm.common.model.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface IRepository<Entity extends IEntity> extends JpaRepository<Entity, Long> {

    Optional<Entity> findFirstByOrderByIdAsc();

    Optional<Entity> findFirstByOrderByIdDesc();

    Optional<Entity> findByUid(Integer uid);

    void deleteById(@NonNull Long id);
}
