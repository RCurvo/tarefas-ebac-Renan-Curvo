package br.com.rcurvo.service;

import br.com.rcurvo.dao.ICustomerDAO;

public class CustomerService {

    private ICustomerDAO customerDAO;

    public CustomerService(ICustomerDAO customerDAO){
        this.customerDAO = customerDAO;

    }

    public String save(){
        customerDAO.save();
        return "Success";
    }
}
