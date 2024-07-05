package org.epm.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepository<Entity> extends JpaRepository<Entity, Long> {

}
