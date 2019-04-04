package com.gmail.viktordudal.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.gmail.viktordudal.dao.PersonDao;
import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;

public class PersonService {

    private PersonDao personDao = new PersonDao();

    public List<Person> getAll() {
        return personDao.getAll();
    }

    public List<Person> getBySpec(Specialization specialization) {
        return personDao.getAll().stream()
                .filter(person -> specialization.equals(person.getSpecialization()))
                .collect(Collectors.toList());
    }

    public Person getById(long id) {
        return personDao.getById(id);
    }


    public Person insertNewPerson(Person newPerson) {
        return personDao.insertPerson(newPerson);
    }

    public boolean deleteById(long id) {
        return personDao.deleteById(id);
    }

    public Person updatePerson(Long id, Person person) {
        return personDao.updatePerson(id, person);
    }

    public boolean addPersonFromFile(String absolutePath) {
        return true;
    }
}