package com.gmail.viktordudal.service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.logging.Logger;

public class Validator {

    private static final Logger LOGGER = Logger.getLogger(Validator.class.getName());

    public void cvValidator(Object object){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<Object>> personViolations = factory.getValidator().validate(object);
        for (ConstraintViolation<Object> personViolation : personViolations) {

            LOGGER.severe(personViolation.getMessage());
        }
    }
}