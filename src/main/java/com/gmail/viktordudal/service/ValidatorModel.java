package com.gmail.viktordudal.service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.logging.Logger;

public class ValidatorModel {

    private static final Logger LOGGER = Logger.getLogger(ValidatorModel.class.getName());

    public void validate(Object object){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<Object>> personViolations = factory.getValidator().validate(object);
        for (ConstraintViolation<Object> personViolation : personViolations) {

            LOGGER.severe(personViolation.getMessage());
        }
    }
}