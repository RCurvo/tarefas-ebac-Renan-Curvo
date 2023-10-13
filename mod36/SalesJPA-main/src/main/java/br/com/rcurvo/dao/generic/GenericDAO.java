package br.com.rcurvo.dao.generic;

import br.com.rcurvo.domain.Persistent;
import br.com.rcurvo.exceptions.DAOException;
import br.com.rcurvo.exceptions.KeyTypeNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


public class GenericDAO<T extends Persistent, E extends Serializable> implements IGenericDAO<T, E>{

    private static final String PERSISTENCE_UNIT_NAME = "Postgre1";
    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    private String persistenceUnitName;
    private Class<T> persistentClass;

    public GenericDAO(Class<T> persistentClass, String persistenceUnitName){
        this.persistentClass = persistentClass;
        this.persistenceUnitName = persistenceUnitName;

    }

    @Override
    public T register(T entity) throws KeyTypeNotFoundException, DAOException {
        openConnection();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public void delete(T entity) throws DAOException {
        openConnection();
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        closeConnection();
    }

    @Override
    public T update(T entity) throws KeyTypeNotFoundException, DAOException {
        openConnection();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public T search(E id) throws DAOException {
        openConnection();
        T entity = entityManager.find(this.persistentClass, id);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public Collection<T> searchAll() throws DAOException {
        openConnection();
        List<T> list =
                entityManager.createQuery(getSelectSql(), this.persistentClass).getResultList();
        closeConnection();
        return list;
    }

    protected void openConnection() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory(getPersistenceUnitName());
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    protected void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }

    protected String getSelectSql(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT obj FROM ");
        sb.append(this.persistentClass.getSimpleName());
        sb.append(" obj");
        return sb.toString();
    }

    private String getPersistenceUnitName() {
        if (persistenceUnitName != null
                && !"".equals(persistenceUnitName)) {
            return persistenceUnitName;
        } else {
            return PERSISTENCE_UNIT_NAME;
        }
    }
}
