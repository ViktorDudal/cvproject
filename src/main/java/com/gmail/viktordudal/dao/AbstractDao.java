package com.gmail.viktordudal.dao;

import com.gmail.viktordudal.model.Person;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<E> {

    public abstract List<E> getAll();

    public abstract E getById(long id);

    public abstract void insert(E entity);

    public abstract E update(E entity);

    public abstract boolean deleteById(Long id);

}
