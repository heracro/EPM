package org.epm.common.model;

public interface IDTO {

    String getAction();
    void setAction(String action);
    boolean isValidDTO();

    private Long getId() {
        throw new UnsupportedOperationException("ID is not available in DTO");
    }

    private void setId(Long id) {
        throw new UnsupportedOperationException("ID is not available in DTO");
    }
}
