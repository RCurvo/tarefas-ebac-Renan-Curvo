package br.com.rcurvo.dao.generic;

import br.com.rcurvo.domain.Persistent;
import br.com.rcurvo.exceptions.DAOException;
import br.com.rcurvo.exceptions.KeyTypeNotFoundException;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

public interface IGenericDAO<T extends Persistent, E extends Serializable> {

    public T register(T entity) throws KeyTypeNotFoundException, DAOException;

    public void delete(T entity) throws DAOException;

    public T update(T entity) throws KeyTypeNotFoundException, DAOException;

    public T search(E id) throws DAOException;

    public Collection<T> searchAll() throws DAOException;
}
