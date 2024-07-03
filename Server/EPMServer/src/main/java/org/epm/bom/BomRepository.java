package org.epm.bom;

import org.epm.bom.model.Bom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BomRepository extends JpaRepository<Bom, Long> {
}
