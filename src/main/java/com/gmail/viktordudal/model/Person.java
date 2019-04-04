package com.gmail.viktordudal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.viktordudal.service.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

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
    private Set<Company> companies = new HashSet<>();

    private Set<String> skills = new HashSet<>();

    private Specialization specialization;

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

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public void addCompany(Company company) {
        this.companies.add(company);
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills=skills;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public static class PersonBuilder {
        private Person newPerson;

        private Set<Company> newCompany = new HashSet<>();
        private Set<String> newSkils = new HashSet<>();

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
            newPerson.addCompany(job);
            return this;
        }

        public PersonBuilder company(Set<Company> job){
            newCompany.addAll(job);
            return this;
        }

        public PersonBuilder skills(Set<String> skills){
            newSkils.addAll(skills);
            return this;
        }

        public PersonBuilder skill(String skill){
            newSkils.add(skill);
            return this;
        }

        public PersonBuilder specialization(Specialization  specialization){
            newPerson.setSpecialization(specialization);
            return this;
        }

        public Person build(){
            newPerson.setCompanies(newCompany);
            newPerson.setSkills(newSkils);
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
                Objects.equals(companies, person.companies) &&
                Objects.equals(skills, person.skills) &&
                specialization == person.specialization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, dateOfBirth, contact, companies, skills, specialization);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", contact=" + contact +
                ", companies=" + companies +
                ", skills=" + skills +
                ", specialization=" + specialization +
                '}';
    }
}
