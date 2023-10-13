package br.com.rcurvo.dao.generic;

import br.com.rcurvo.domain.Persistent;

import java.io.Serializable;

public abstract class GenericDB2DAO <T extends Persistent, E extends Serializable> extends GenericDAO<T,E> {
    public GenericDB2DAO(Class<T> persistenteClass) {
        super(persistenteClass, "Postgres2");
    }
}

