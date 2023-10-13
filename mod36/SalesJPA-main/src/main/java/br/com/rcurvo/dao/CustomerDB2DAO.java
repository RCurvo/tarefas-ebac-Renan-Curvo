package br.com.rcurvo.dao;

import br.com.rcurvo.dao.generic.GenericDAO;
import br.com.rcurvo.dao.generic.GenericDB2DAO;
import br.com.rcurvo.domain.Customer;

public class CustomerDB2DAO extends GenericDB2DAO<Customer, Long> implements ICustomerDAO<Customer> {
    public CustomerDB2DAO() {
        super(Customer.class);
    }
}
