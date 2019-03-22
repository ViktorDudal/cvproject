package com.gmail.viktordudal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gmail.viktordudal.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseFile {

    private static ObjectMapper mapper;
    private static XmlMapper xmlMapper;
    private static final Logger LOGGER = Logger.getLogger(ParseFile.class.getName());

    public static Person parseFile(String file) throws FileNotFoundException {

        Person user = null;
        mapper = new ObjectMapper(new YAMLFactory());
        xmlMapper = new XmlMapper();

        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()){
                char firstChar = line.trim().charAt(0);
                try {
                    if (firstChar == '{' || firstChar == '-') {
                        user = mapper.readValue(new File(file), Person.class);
                    }
                    if (firstChar == '<') {
                        user = xmlMapper.readValue(new File(file), Person.class);
                    }
                    if (firstChar == 's'){
//                        user = readFromTXTFile(file);
                    }
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "IOException: ", e);
                }
            }
        }
        return user;
    }

    /*private static Person readFromTXTFile(String  file) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(file));
        Person user = new Person();
        Contact contact = new Contact();
        List<Company> jobs = new ArrayList<>();
        List<String> skills = new ArrayList<>();
        List<Specialization> specializations = new ArrayList<>();
        int i = 0;
        while (scanner.hasNext()) {
            String readLine = scanner.nextLine().trim();
            if (readLine.contains(":") || readLine.isEmpty()){
                continue;
            }
            String readLineSubstring = readLine.substring(readLine.indexOf('=') + 1).trim();
            if (readLine.contains("surname =")) {
                user = Person.PersonBuilder.builder().surname(readLineSubstring).build();
//                user.setSurname(readLineSubstring);
                continue;
            }
            if (readLine.contains("name =")) {
                user = Person.PersonBuilder.builder().name(readLineSubstring).build();
//                user.setName(readLineSubstring);
                continue;
            }
            if (readLine.contains("dateOfBirth =")) {
                user = Person.PersonBuilder.builder().dateofBirth(LocalDate.parse(readLineSubstring, DateTimeFormatter.ofPattern("dd-MM-uuuu"))).build();
//                user.setDateOfBirth();
                continue;
            }
            if (readLine.contains("city =")) {
                user = Person.PersonBuilder.builder().contact(Contact.ContactBuilder.builder().city(readLineSubstring).build()).build();
//                contact.setCity(readLineSubstring);
                continue;
            }
            if (readLine.contains("address =")) {
                user = Person.PersonBuilder.builder().contact(Contact.ContactBuilder.builder().address(readLineSubstring).build()).build();
//                contact.setAddress(readLineSubstring);
                continue;
            }
            if (readLine.contains("phoneNumber =")) {
                user = Person.PersonBuilder.builder().contact(Contact.ContactBuilder.builder().phoneNumber(readLineSubstring).build()).build();
//                contact.setPhoneNumber(readLineSubstring);
                continue;
            }
            if (readLine.contains("email =")) {
                user = Person.PersonBuilder.builder().contact(Contact.ContactBuilder.builder().email(readLineSubstring).build()).build();
//                contact.setEmail(readLineSubstring);
                continue;
            }
            if (readLine.contains("companyName =")) {
//                Person.PersonBuilder.builder().company(jobs.get(i)).build();
                Company.CompanyBuilder.builder().companyName(readLineSubstring).build();
//                jobs.get(i).setCompanyName(readLineSubstring);
                continue;
            }
            if (readLine.contains("position =")) {
//                Person.PersonBuilder.builder().company(jobs.get(i));
//                Company.CompanyBuilder.builder().position(readLineSubstring).build();
//                jobs.get(i).setPosition(readLineSubstring);
                continue;
            }
            if (readLine.contains("workedFrom =")) {
//                Person.PersonBuilder.builder().company(jobs.get(i));
//                Company.CompanyBuilder.builder().workedFrom(LocalDate.parse(readLineSubstring, DateTimeFormatter.ofPattern("dd-MM-uuuu"))).build();
//                jobs.get(i).setWorkedFrom(LocalDate.parse(readLineSubstring, DateTimeFormatter.ofPattern("dd-MM-uuuu")));
                continue;
            }
            if (readLine.contains("workedTill =")) {
//                Person.PersonBuilder.builder().company(jobs.get(i));
//                Company.CompanyBuilder.builder().workedTill(LocalDate.parse(readLineSubstring, DateTimeFormatter.ofPattern("dd-MM-uuuu"))).build();
//                jobs.get(i).setWorkedTill(LocalDate.parse(readLineSubstring, DateTimeFormatter.ofPattern("dd-MM-uuuu")));
                continue;
            }
            if (readLine.contains("skill =")) {
                Person.PersonBuilder.builder().skills(readLineSubstring);
//                skills.add(readLineSubstring);
                continue;
            }
            if (readLine.contains("spec =")) {
                Person.PersonBuilder.builder().specialization(Specialization.valueOf(readLineSubstring));
//                specializations.add(Specialization.valueOf(readLineSubstring));
            }
            i++;
            }
        scanner.close();
//        user.;
        user = jobs.add(Person.PersonBuilder.builder().company(jobs.get(i)).build());
//        user.setSkills(skills);
//        user.setSpecialization(specializations);
        return user;
    }*/
}