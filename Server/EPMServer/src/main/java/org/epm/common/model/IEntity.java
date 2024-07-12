package org.epm.common.model;

public interface IEntity extends DataModel {

    Long getPrivateId();
    void setPrivateId(Long id);

    default void initializeId() {
        if (getId() == null || getId() == 0) {
            setId((int)(Math.random() * 999999 + 1));
        }
    }
}
