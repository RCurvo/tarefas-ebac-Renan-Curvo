package br.com.rcurvo.dao;

import br.com.rcurvo.domain.Brand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BrandDAO implements IBrandDAO{
    @Override
    public Brand register(Brand brand) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("JPACar");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(brand);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return brand;
    }

}
