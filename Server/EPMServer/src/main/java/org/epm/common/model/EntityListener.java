package org.epm.common.model;

import jakarta.persistence.PrePersist;

public class EntityListener {

    @PrePersist
    public void prePersist(IEntity entity) {
        entity.initializeId();
    }

}
