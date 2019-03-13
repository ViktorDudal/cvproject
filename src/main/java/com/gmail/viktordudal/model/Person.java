package com.gmail.viktordudal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.viktordudal.service.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {

    @NotNull(message = "Surname cannot be null")
    @Size(min = 2, max = 25, message = "Surname must be between 10 and 25 characters")
    private String surname;
    private String name;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @XmlJavaTypeAdapter(type=LocalDate.class,
            value=LocalDateAdapter.class)
    private LocalDate dateOfBirth;
    private Address residence;
    private Contact contact;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Company> jobs = new ArrayList<>();
    private List<String> skills = new ArrayList<>();
    private List<Specialization> specialization = new ArrayList<>();

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getDateOfBirth() {
//        return dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
//    }
//
//    public void setDateOfBirth(String birthString) {
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
////        dateOfBirth = LocalDate.parse(birthString, formatter);
//    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getResidence() {
        return residence;
    }

    public void setResidence(Address residence) {
        this.residence = residence;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Company> getJobs() {
        return jobs;
    }

    public void setJobs(List<Company> jobs) {
        this.jobs = jobs;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<Specialization> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<Specialization> specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Person{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birth=" + dateOfBirth +
                ", residence=" + residence +
                ", jobs=" + jobs +
                ", skills=" + skills +
                ", specialization=" + specialization +
                '}';
    }
}
