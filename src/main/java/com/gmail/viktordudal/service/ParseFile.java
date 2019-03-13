package com.gmail.viktordudal.service;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gmail.viktordudal.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.xml.XmlMapper;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseFile {

    private static ObjectMapper mapper;
    private static XmlMapper xmlMapper;

    public static char fileExtension(StringBuilder builder) {
        return builder.charAt(0);
    }

    public static Person parseFile(char fileExtension, String file) {
        Person user = null;
        mapper = new ObjectMapper(new YAMLFactory());
        xmlMapper = new XmlMapper();

        try {
            if (fileExtension == '{') {
                user = mapper.readValue(new File(file), Person.class);
            }
            if (fileExtension == '-' || fileExtension == '#') {
                user = mapper.readValue(new File(file), Person.class);
            }
            if (fileExtension == '<') {
                user = xmlMapper.readValue(new File(file), Person.class);
            }
            if (fileExtension=='s'){
                user = readFromTXTFile(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private static Person readFromTXTFile(String  file) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(file));
        Person user = new Person();
        Address address = new Address();
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
            if (readLine.contains("surname =")) {
                user.setSurname(readLine.substring(readLine.indexOf('=') + 1).trim());
                continue;
            }
            if (readLine.contains("name =")) {
                user.setName(readLine.substring(readLine.indexOf('=') + 1).trim());
                continue;
            }
            if (readLine.contains("dateOfBirth =")) {
                user.setDateOfBirth(LocalDate.parse(readLine.substring(readLine.indexOf('=') + 1).trim(), DateTimeFormatter.ofPattern("dd-MM-uuuu")));
                continue;
            }
            if (readLine.contains("city =")) {
                address.setCity(readLine.substring(readLine.indexOf('=') + 1).trim());
                continue;
            }
            if (readLine.contains("street =")) {
                address.setStreet(readLine.substring(readLine.indexOf('=') + 1).trim());
                continue;
            }
            if (readLine.contains("house =")) {
                address.setHouse(Integer.parseInt(readLine.substring(readLine.indexOf('=') + 1).trim()));
                continue;
            }
            if (readLine.contains("apartment =")) {
                address.setApartment(Integer.parseInt(readLine.substring(readLine.indexOf('=') + 1).trim()));
                continue;
            }
            if (readLine.contains("zip =")) {
                address.setZip(Integer.parseInt(readLine.substring(readLine.indexOf('=') + 1).trim()));
                continue;
            }
            if (readLine.contains("phoneNumber =")) {
                contact.setPhoneNumber(Long.parseLong(readLine.substring(readLine.indexOf('=') + 1).trim()));
                continue;
            }
            if (readLine.contains("email =")) {
                contact.setEmail(readLine.substring(readLine.indexOf('=') + 1).trim());
                continue;
            }
            if (readLine.contains("companyName =")) {
                jobs.add( new Company());
                jobs.get(i).setCompanyName(readLine.substring(readLine.indexOf('=') + 1).trim());
                continue;
            }
            if (readLine.contains("position =")) {
                jobs.get(i).setPosition(readLine.substring(readLine.indexOf('=') + 1).trim());
                continue;
            }
            if (readLine.contains("workedFrom =")) {
                jobs.get(i).setWorkedFrom(LocalDate.parse(readLine.substring(readLine.indexOf('=') + 1).trim(), DateTimeFormatter.ofPattern("dd-MM-uuuu")));
                continue;
            }
            if (readLine.contains("workedTill =")) {
                jobs.get(i).setWorkedTill(LocalDate.parse(readLine.substring(readLine.indexOf('=') + 1).trim(), DateTimeFormatter.ofPattern("dd-MM-uuuu")));
                continue;
            }
            if (readLine.contains("skill =")) {
                skills.add(readLine.substring(readLine.indexOf('=') + 1).trim());
                continue;
            }
            if (readLine.contains("spec =")) {
                specializations.add(Specialization.valueOf(readLine.substring(readLine.indexOf('=') + 1).trim()));
            }
            i++;
            }
        scanner.close();
        user.setResidence(address);
        user.setContact(contact);
        user.setJobs(jobs);
        user.setSkills(skills);
        user.setSpecialization(specializations);
        return user;
    }
}