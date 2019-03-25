package com.gmail.viktordudal;

//import com.gmail.viktordudal.dao.PersonDao;
import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

public class YamlTesting {

    private static  final Logger LOGGER = Logger.getLogger(YamlTesting.class.getName());

    public static void main(String[] args) throws IOException {

//        String inputFile = "src/main/resources/user.txt";
//
//        Person user = parseFile(inputFile);
//
//        String outputFile = "src/main/resources/output.yml";
//
//        writeToYamlFile(outputFile, user);

        Person pers = Person.builder()
                .id(1)
                .surname("Dudal")
                .name("Viktor")
                .dateOfBirth(LocalDate.of(1984,10,03))
                .contact(Contact.builder()
                        .city("Rivne")
                        .address("Street")
                        .phoneNumber("0660142900")
                        .email("vit@fdfg.com")
                        .build())
                .company(Company.builder()
                                .companyName("SoftServe")
                                .position("Junior Developer")
                                .workedFrom(LocalDate.of(2008,10,15))
                                .workedTill(LocalDate.of(2012,1,7))
                                .build())
                .company(Company.builder()
                                .companyName("SoftServe")
                                .position("Junior Developer")
                                .workedFrom(LocalDate.of(2008,10,15))
                                .workedTill(LocalDate.of(2012,1,7))
                                .build())
                .skills("HTML")
                .skills("CSS")
                .specialization(Specialization.JAVA)
                .build();
        LOGGER.severe(pers.toString());
    }
}