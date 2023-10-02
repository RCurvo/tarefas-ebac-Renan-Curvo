package br.com.rcurvo.dao;

import br.com.rcurvo.domain.Brand;
import br.com.rcurvo.domain.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CarDAO implements ICarDAO {
    @Override
    public Car register(Car car) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("JPACar");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return car;
    }
}
