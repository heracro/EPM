package org.epm.common.model;

public interface IEntity {

    Integer getUid();
    void setUid(Integer id);
    Long getId();
    void setId(Long id);
    boolean isValidEntity();

    private Long getAction() {
        throw new UnsupportedOperationException("Action is not available in DTO");
    }

    private void setAction(String action) {
        throw new UnsupportedOperationException("Action is not available in DTO");
    }

}
