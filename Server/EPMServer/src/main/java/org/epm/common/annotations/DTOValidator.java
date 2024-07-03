package org.epm.common.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DTOChecker.class)
public @interface DTOValidator {
    String message() default "Invalid DTO";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
