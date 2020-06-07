package com.example.demo.annotation.check;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Target({PARAMETER, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
// check annotation start
@NotNull
@Size(min = 10, max = 10)
public @interface CheckPassword {
    Class<?>[] groups() default {};

    String message() default "";

    Class<? extends Payload>[] payload() default {};
}
