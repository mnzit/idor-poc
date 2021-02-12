package com.mnzit.idor.poc.annotation;

import com.mnzit.idor.poc.validator.IsHashValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsHashValidator.class)
@Documented
public @interface IsHash {

    String message() default "Field value should be from list of ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
