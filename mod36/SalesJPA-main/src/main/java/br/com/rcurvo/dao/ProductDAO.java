package br.com.rcurvo.dao;

import br.com.rcurvo.dao.generic.GenericDAO;
import br.com.rcurvo.dao.generic.GenericDB1DAO;
import br.com.rcurvo.domain.Product;

public class ProductDAO  extends GenericDB1DAO<Product, Long> implements IProductDAO {
    public ProductDAO() {
        super(Product.class);
    }
}
