package com.gmail.viktordudal.service;

import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.logging.Logger;

public class Validator {

    private static final Logger LOGGER = Logger.getLogger(Validator.class.getName());

    public static Person validator(Person user){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();

        Set<ConstraintViolation<Person>> violationsPerson = validator.validate(user);
        for (ConstraintViolation<Person> violation : violationsPerson) {
            LOGGER.warning(violation.getMessage());
        }
        Set<ConstraintViolation<Contact>> violationsContact = validator.validate(user.getContact());
        for (ConstraintViolation<Contact> violation : violationsContact) {
            LOGGER.warning(violation.getMessage());
        }

        return user;
    }
}
