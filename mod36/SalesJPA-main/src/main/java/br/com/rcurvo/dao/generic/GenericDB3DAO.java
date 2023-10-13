package br.com.rcurvo.dao.generic;

import br.com.rcurvo.domain.Persistent;

import java.io.Serializable;

public abstract class GenericDB3DAO<T extends Persistent, E extends Serializable> extends GenericDAO<T,E> {
    public GenericDB3DAO(Class<T> persistentClass) {
        super(persistentClass, "Mysql1");
    }
}
