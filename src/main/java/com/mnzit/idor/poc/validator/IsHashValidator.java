package com.mnzit.idor.poc.validator;

import com.mnzit.idor.poc.converter.IsHash;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Slf4j
public class IsHashValidator implements ConstraintValidator<IsHash, Long> {

    @Override
    public void initialize(IsHash constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        log.debug("id : {}",id);
        if(id > 0){
            return true;
        }
        return false;
    }
}
