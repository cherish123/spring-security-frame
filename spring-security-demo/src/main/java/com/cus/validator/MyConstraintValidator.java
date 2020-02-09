package com.cus.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        logger.info("my validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        //可以做一些邏輯處理，現在這邊直接就認證通過
        return true;
    }
}
