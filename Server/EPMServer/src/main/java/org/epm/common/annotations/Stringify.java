package org.epm.common.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Stringify {
//    String message() default "";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
}
