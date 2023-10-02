package br.com.rcurvo.dao;

import br.com.rcurvo.domain.Accessories;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AccessoriesDAO implements IAccessoriesDAO {
    @Override
    public Accessories register(Accessories accessories) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("JPACar");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(accessories);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return accessories;
    }
}
