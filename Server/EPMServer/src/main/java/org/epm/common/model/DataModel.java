package org.epm.common.model;

public interface DataModel {
    Integer getId();
    void setId(Integer id);
    boolean isValidEntity();
    boolean isValidDTO();
}
