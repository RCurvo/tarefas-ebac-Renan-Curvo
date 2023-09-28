package br.com.rcurvo.dao;

import br.com.rcurvo.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProductDAO implements IProductDAO{
    @Override
    public Product register(Product product) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("TarefaJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return product;
    }
}
