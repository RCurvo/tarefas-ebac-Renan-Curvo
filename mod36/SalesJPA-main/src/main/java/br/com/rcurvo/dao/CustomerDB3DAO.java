package br.com.rcurvo.dao;

import br.com.rcurvo.dao.generic.GenericDB2DAO;
import br.com.rcurvo.dao.generic.GenericDB3DAO;
import br.com.rcurvo.domain.Customer;
import br.com.rcurvo.domain.Customer2;

public class CustomerDB3DAO extends GenericDB3DAO<Customer2, Long> implements ICustomerDAO<Customer2> {
    public CustomerDB3DAO() {
        super(Customer2.class);
    }
}
