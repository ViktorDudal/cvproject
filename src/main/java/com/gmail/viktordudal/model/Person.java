package com.gmail.viktordudal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.viktordudal.service.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {

    private long id;

    @Size(min = 2, max = 25, message = "Field 'surname' must be between 2 and 25 characters")
    private String surname;

    @Size(min = 2, max = 15, message = "Field 'name' must be between 2 and 15 characters")
    private String name;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Past(message = "Wrong date of Birth")
    private LocalDate dateOfBirth;

    private Contact contact;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Company> jobs = new ArrayList<>();

    private List<String> skills = new ArrayList<>();

//    private Specialization specialization;
    private String  specialization;

    public static PersonBuilder builder(){
        return new PersonBuilder();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public void setJobs(Company job) {
        this.jobs.add(job);
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(String skill) {
        this.skills.add(skill);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public static class PersonBuilder {
        private Person newPerson;

        private PersonBuilder() {
            newPerson = new Person();
        }

        public PersonBuilder id(long id) {
            newPerson.id = id;
            return this;
        }

        public PersonBuilder surname(String surname) {
            newPerson.surname = surname;
            return this;
        }

        public PersonBuilder name(String name){
            newPerson.name = name;
            return this;
        }

        public PersonBuilder dateOfBirth(LocalDate dateOfBirth){
            newPerson.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonBuilder contact(Contact contact){
            newPerson.setContact(contact);
            return this;
        }

        public PersonBuilder company(Company job){
            newPerson.setJobs(job);
            return this;
        }

        public PersonBuilder skills(String skills){
            newPerson.setSkills(skills);
            return this;
        }

        public PersonBuilder specialization(String  specialization){
            newPerson.setSpecialization(specialization);
            return this;
        }

        public Person build(){
            new ValidatorModel().validate(newPerson);
            return newPerson;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(name, person.name) &&
                Objects.equals(dateOfBirth, person.dateOfBirth) &&
                Objects.equals(contact, person.contact) &&
                Objects.equals(jobs, person.jobs) &&
                Objects.equals(skills, person.skills) &&
                Objects.equals(specialization, person.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, dateOfBirth, contact, jobs, skills, specialization);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", contact=" + contact +
                ", jobs=" + jobs +
                ", skills=" + skills +
                ", specialization=" + specialization +
                '}';
    }
}
