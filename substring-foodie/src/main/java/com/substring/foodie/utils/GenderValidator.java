package com.substring.foodie.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.logging.Logger;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //logic
        if (value == null || value.isEmpty()) {
            logger.warning("Invalid Gender String");
            return false;
        }

        if (value.toLowerCase().equals("male") || value.toLowerCase().equals("female")) {
            return true;
        }

        return false;
    }
}
