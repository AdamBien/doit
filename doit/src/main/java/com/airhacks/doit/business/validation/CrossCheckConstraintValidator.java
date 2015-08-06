package com.airhacks.doit.business.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author airhacks.com
 */
public class CrossCheckConstraintValidator implements ConstraintValidator<CrossCheck, ValidEntity> {

    @Override
    public void initialize(CrossCheck constraintAnnotation) {
    }

    @Override
    public boolean isValid(ValidEntity entity, ConstraintValidatorContext context) {
        return entity.isValid();
    }
}
