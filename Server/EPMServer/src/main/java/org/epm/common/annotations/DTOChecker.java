package org.epm.common.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class DTOChecker implements ConstraintValidator<DTOValidator, Object> {

    @Override
    public void initialize(DTOValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        Class<?> clazz = value.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if ("id".equals(field.getName())) {
                continue;
            }
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(value);
                if (fieldValue != null) {
                    return true; // At least one field is not null
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field value", e);
            }
        }
        return false; // All non-'id' fields are null
    }
}
