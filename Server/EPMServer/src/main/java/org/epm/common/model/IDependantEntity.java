package org.epm.common.model;

public interface IDependantEntity<ParentEntity extends IEntity> extends IEntity {
    void setParent(ParentEntity parent);

    ParentEntity getParent();
}
