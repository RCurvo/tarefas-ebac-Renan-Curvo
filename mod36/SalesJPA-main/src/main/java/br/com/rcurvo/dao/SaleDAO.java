package br.com.rcurvo.dao;

import br.com.rcurvo.dao.generic.GenericDAO;
import br.com.rcurvo.dao.generic.GenericDB1DAO;
import br.com.rcurvo.domain.Customer;
import br.com.rcurvo.domain.Product;
import br.com.rcurvo.domain.Sale;
import br.com.rcurvo.exceptions.DAOException;
import br.com.rcurvo.exceptions.KeyTypeNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class SaleDAO extends GenericDB1DAO<Sale, Long> implements ISaleDAO {

    public SaleDAO() {
        super(Sale.class);
    }

    @Override
    public void finishSale(Sale sale) throws KeyTypeNotFoundException, DAOException {
        super.update(sale);
    }

    @Override
    public void cancelSale(Sale sale) throws KeyTypeNotFoundException, DAOException {
        super.update(sale);
    }

    @Override
    public void delete(Sale sale){
        throw new UnsupportedOperationException("Delete Sale not allowed");
    }

    @Override
    public Sale register(Sale sale) throws DAOException {
        try {
            openConnection();
            sale.getProducts().forEach(prod -> {
                Product product = entityManager.merge(prod.getProduct());
                prod.setProduct(product);
            });
            Customer customer = entityManager.merge(sale.getCustomer());
            sale.setCustomer(customer);
            entityManager.persist(sale);
            entityManager.getTransaction().commit();
            closeConnection();
            return sale;
        } catch (Exception e){
            throw new DAOException("ERROR SAVING SALE ", e);
        }
    }

    @Override
    public Sale searchWithCollection(Long id) {
        openConnection();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Sale> query = builder.createQuery(Sale.class);
        Root<Sale> root = query.from(Sale.class);
        root.fetch("customer");
        root.fetch("products");
        query.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Sale> tpQuery =
                entityManager.createQuery(query);
        Sale sale = tpQuery.getSingleResult();
        closeConnection();
        return sale;
    }
}
