package com.gmail.viktordudal.service;

import java.util.List;

import com.gmail.viktordudal.dao.PersonDao;
import com.gmail.viktordudal.model.Person;

public class PersonService {

    private PersonDao personDao = new PersonDao();


    public List<Person> getAll() {
        return personDao.getAll();
    }

    public Person getById(long id) {
        return personDao.getById(id);
    }

}