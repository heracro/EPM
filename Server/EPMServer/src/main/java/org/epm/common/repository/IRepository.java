package org.epm.common.repository;

import org.epm.common.model.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<Entity extends IEntity> extends JpaRepository<Entity, Long> {
    Entity findFirstByOrderByIdAsc();
    Entity findFirstByOrderByIdDesc();
}
