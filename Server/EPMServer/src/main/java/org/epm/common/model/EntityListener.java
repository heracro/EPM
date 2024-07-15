package org.epm.common.model;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class EntityListener {

    @PrePersist
    public void prePersist(IEntity entity) {
        if (!entity.isValidEntity()) throw new IllegalArgumentException("Entity is not valid");
        entity.initializeId();
    }

    @PreUpdate
    public void preUpdate(IEntity entity) {
        if (!entity.isValidEntity()) throw new IllegalArgumentException("Entity is not valid");
    }

}
