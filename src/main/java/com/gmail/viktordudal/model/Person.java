package com.gmail.viktordudal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.viktordudal.service.*;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {



    @NotNull(message = "Field 'surname' cannot be null")
    @Size(min = 2, max = 25, message = "Field 'surname' must be between 2 and 25 characters")
    private String surname;

    @NotNull(message = "Field 'name' cannot be null")
    @Size(min = 2, max = 15, message = "Field 'name' must be between 2 and 15 characters")
    private String name;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "Field 'dateOfBirth' cannot be null")
    @Past(message = "Wrong date of Birth")
    private LocalDate dateOfBirth;

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    /*public static class Builder {
        private Person newPerson;

        public Builder() {
            newPerson = new Person();
        }

//        @NotNull(message = "Field 'surname' cannot be null")
//        @Size(min = 2, max = 25, message = "Field 'surname' must be between 2 and 25 characters")
        public Builder withSurname(String surname) {
            newPerson.surname = surname;
            return this;
        }

        public Builder withName(String name){
            newPerson.name = name;
            return this;
        }

        public Builder withDateofBirth(LocalDate dateofBirth){
            newPerson.dateOfBirth = dateofBirth;
            return this;
        }

        public Builder withContact(Contact contact){
            newPerson.contact = contact;
            return this;
        }

        public Builder withCompany(List<Company> jobs){
            newPerson.jobs = jobs;
            return this;
        }

        public Builder withSkills(List<String> skills){
            newPerson.skills = skills;
            return this;
        }

        public Builder withSpecialization(List<Specialization> specialization){
            newPerson.specialization = specialization;
            return this;
        }

        public Person build(){
            return validator(newPerson);
        }
    }*/

    @Override public String toString() {
        return "Person{" +
            "surname='" + surname + '\'' +
            ", name='" + name + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", contact=" + contact +
            ", jobs=" + jobs +
            ", skills=" + skills +
            ", specialization=" + specialization +
            "}\n\r";
    }
}
