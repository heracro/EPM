package org.epm.common.model;

public interface IDTO {
    boolean isValidDTO();
    boolean equals(Object that);
    static IDTO randomInstance() {
        throw new UnsupportedOperationException("METHOD 'randomInstance()' NOT IMPLEMENTED");
    }

}
