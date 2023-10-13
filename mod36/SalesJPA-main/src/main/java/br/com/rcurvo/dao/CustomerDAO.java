package br.com.rcurvo.dao;

import br.com.rcurvo.dao.generic.GenericDAO;
import br.com.rcurvo.dao.generic.GenericDB1DAO;
import br.com.rcurvo.domain.Customer;

public class CustomerDAO extends GenericDB1DAO<Customer, Long> implements ICustomerDAO<Customer> {

    public CustomerDAO(){
        super(Customer.class);
    }

}
