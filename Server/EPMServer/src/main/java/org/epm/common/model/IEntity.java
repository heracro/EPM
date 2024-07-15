package org.epm.common.model;

public interface IEntity {

    Integer getUid();
    void setUid(Integer id);
    Long getId();
    void setId(Long id);
    boolean isValidEntity();

    default void initializeId() {
        if (getUid() == null || getUid() == 0) {
            setUid((int)(Math.random() * 999999 + 1));
        }
    }

    private Long getAction() {
        throw new UnsupportedOperationException("Action is not available in DTO");
    }

    private void setAction(String action) {
        throw new UnsupportedOperationException("Action is not available in DTO");
    }

}
