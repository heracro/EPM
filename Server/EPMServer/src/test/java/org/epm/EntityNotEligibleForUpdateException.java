package org.epm;

public class EntityNotEligibleForUpdateException extends Exception {

    public EntityNotEligibleForUpdateException(String message) {
        super(message);
    }

    public EntityNotEligibleForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

}
