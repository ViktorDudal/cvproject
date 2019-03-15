package com.gmail.viktordudal.dao;

import java.util.List;

public abstract class AbstractDao<E> {

    public abstract List<E> getAll();

    public abstract E getById(long id);

    public abstract E update(E entity);

    public abstract E insert(E entity);

    public abstract boolean delete(E entity);

}
