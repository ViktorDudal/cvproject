package com.gmail.viktordudal.dao;

import com.gmail.viktordudal.model.Person;

import java.util.List;

public abstract class AbstractDao<E> {

    public abstract List<E> getAll();

    public abstract E getById(long id);

    public abstract void insertPersons(List<Person> person);

    public abstract E update(E entity);

    public abstract boolean deleteById(Long id);

}
