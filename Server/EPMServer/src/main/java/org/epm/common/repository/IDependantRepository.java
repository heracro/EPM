package org.epm.common.repository;

import org.epm.common.model.IEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface IDependantRepository<Entity extends IEntity> extends JpaRepository<Entity, Long> {

    Page<Entity> findAllByParentUid(Integer parentUid, Pageable pageable);

    Optional<Entity> findByParentUidAndUid(Integer parentUid, Integer uid);
}
