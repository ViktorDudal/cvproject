package com.gmail.viktordudal.dao;

import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public abstract class AbstractDao<E> {

    public abstract List<E> getAll();

    public abstract E getById(long id);

//    public abstract E insertPerson(E entity);

    public abstract Person insertPerson(Person person, Contact contact, Set<String> skills);

    public abstract E update(E entity);

    public abstract boolean deleteById(Long id);

}
