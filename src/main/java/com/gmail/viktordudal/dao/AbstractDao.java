package com.gmail.viktordudal.dao;

import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;

import java.util.List;
import java.util.Set;

public abstract class AbstractDao<E> {

    public abstract List<E> getAll();

    public abstract E getById(long id);

    public abstract Person insertPerson(Person person);

    public abstract Person updatePerson(Long id, Person person);

    public abstract boolean deleteById(Long id);

}
