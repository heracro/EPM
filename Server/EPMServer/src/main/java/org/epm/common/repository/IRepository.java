package org.epm.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<Entity> extends JpaRepository<Entity, Long> {
    Entity findFirstByOrderByIdAsc();
    Entity findFirstByOrderByIdDesc();
}
