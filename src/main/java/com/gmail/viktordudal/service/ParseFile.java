package com.gmail.viktordudal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gmail.viktordudal.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

//    private static Person readFromTXTFile(String  file) throws FileNotFoundException {
//
//        Scanner scanner = new Scanner(new File(file));
//        Person user = new Person();
//        Contact contact = new Contact();
//        Set<Company> companies = new HashSet<>();
//        Set<String> skills = new HashSet<>();
//        Specialization specializations = null;
//        int i = 0;
//        while (scanner.hasNext()) {
//            String readLine = scanner.nextLine().trim();
//            if (readLine.contains(":") || readLine.isEmpty()){
//                continue;
//            }
//            if (readLine.contains("surname =")) {
//                user.setSurname(readLine.substring(readLine.indexOf('=') + 1).trim());
//                continue;
//            }
//            if (readLine.contains("name =")) {
//                user.setName(readLine.substring(readLine.indexOf('=') + 1).trim());
//                continue;
//            }
//            if (readLine.contains("dateOfBirth =")) {
//                user.setDateOfBirth(LocalDate.parse(readLine.substring(readLine.indexOf('=') + 1).trim(), DateTimeFormatter.ofPattern("dd-MM-uuuu")));
//                continue;
//            }
//            if (readLine.contains("city =")) {
//                contact.setCity(readLine.substring(readLine.indexOf('=') + 1).trim());
//                continue;
//            }
//            if (readLine.contains("address =")) {
//                contact.setAddress(readLine.substring(readLine.indexOf('=') + 1).trim());
//                continue;
//            }
//            if (readLine.contains("phoneNumber =")) {
//                contact.setPhoneNumber(readLine.substring(readLine.indexOf('=') + 1).trim());
//                continue;
//            }
//            if (readLine.contains("email =")) {
//                contact.setEmail(readLine.substring(readLine.indexOf('=') + 1).trim());
//                continue;
//            }
//            if (readLine.contains("companyName =")) {
//                companies.add( new Company());
//                companies.get(i).setCompanyName(readLine.substring(readLine.indexOf('=') + 1).trim());
//                continue;
//            }
//            if (readLine.contains("position =")) {
//                jobs.get(i).setPosition(readLine.substring(readLine.indexOf('=') + 1).trim());
//                continue;
//            }
//            if (readLine.contains("workedFrom =")) {
//                jobs.get(i).setWorkedFrom(LocalDate.parse(readLine.substring(readLine.indexOf('=') + 1).trim(), DateTimeFormatter.ofPattern("dd-MM-uuuu")));
//                continue;
//            }
//            if (readLine.contains("workedTill =")) {
//                jobs.get(i).setWorkedTill(LocalDate.parse(readLine.substring(readLine.indexOf('=') + 1).trim(), DateTimeFormatter.ofPattern("dd-MM-uuuu")));
//                continue;
//            }
//            if (readLine.contains("skill =")) {
//                skills.add(readLine.substring(readLine.indexOf('=') + 1).trim());
//                continue;
//            }
//            if (readLine.contains("spec =")) {
//                specializations.add(Specialization.valueOf(readLine.substring(readLine.indexOf('=') + 1).trim()));
//            }
//            i++;
//        }
//        scanner.close();
//        user.setResidence(address);
//        user.setContact(contact);
//        user.setJobs(jobs);
//        user.setSkills(skills);
//        user.setSpecialization(specializations);
//        return user;
//    }
}