package com.gmail.viktordudal;

import com.gmail.viktordudal.dao.PersonDao;
import com.gmail.viktordudal.model.Person;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

import static com.gmail.viktordudal.service.ParseFile.parseFile;
import static com.gmail.viktordudal.service.WriteToYamlFile.writeToYamlFile;

public class YamlTesting {

    private static  final Logger LOGGER = Logger.getLogger(YamlTesting.class.getName());

    public static void main(String[] args) throws IOException {

        String inputFile = "src/main/resources/user.txt";

        Person user = parseFile(inputFile);

        String outputFile = "src/main/resources/output.yml";

        writeToYamlFile(outputFile, user);

//        Person man1 = new Person(), man2 = new Person();
//        man1.setSurname("Petrov");
//        man1.setName("Fedir");
//
//        ArrayList<Person> newParsons = new ArrayList<>();
//        newParsons.add(man1);
//
//        PersonDao next = new PersonDao();
//        next.insertPersons(newParsons);
    }
}