package org.epm.common.model;

public interface IEntity {
    void setId(Long id);
    boolean isValidEntity();
    Long getId();
    static IDTO randomInstance() {
        throw new UnsupportedOperationException("METHOD 'randomInstance()' NOT IMPLEMENTED");
    }
}
