package com.example.demo.annotation.check;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Target({PARAMETER})
@Retention(RUNTIME)
@Size(min = 2, max = 10)
@Pattern(regexp = "^[a-z]+$")
@Constraint(validatedBy = {})
public @interface CheckName {
    Class<?>[] groups() default {};

    String message() default "";

    Class<? extends Payload>[] payload() default {};
}
