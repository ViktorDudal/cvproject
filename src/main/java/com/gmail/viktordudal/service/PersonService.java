package com.gmail.viktordudal.service;

import java.util.List;
import java.util.stream.Collectors;

import com.gmail.viktordudal.dao.PersonDao;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;

public class PersonService {

    private PersonDao personDao = new PersonDao();

    private static long counter = 1;

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


    public Person insertNewPerson(Person newPerson, Contact newContact) {
        return personDao.insertPerson(newPerson, newContact);
    }
//
    public boolean deleteById(long id) {
        return personDao.deleteById(id);
    }
//
//    public void updatePerson(Person newPerson) {
//        int idToUpdate = 0;
//        for (int i = 0; i < persons.size(); i++) {
//            if (persons.get(i).getId() == newPerson.getId()){
//                idToUpdate = i;
//            }
//        }
//        persons.remove(persons.get(idToUpdate));
//        persons.add(idToUpdate, newPerson);
//    }

    public boolean addPersonFromFile(String absolutePath) {
        //use parser to parse file and add to DB
        return true;
    }
}