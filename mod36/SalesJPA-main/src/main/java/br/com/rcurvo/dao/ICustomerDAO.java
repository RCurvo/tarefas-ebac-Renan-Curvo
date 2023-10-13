package br.com.rcurvo.dao;

import br.com.rcurvo.dao.generic.IGenericDAO;
import br.com.rcurvo.domain.Customer;
import br.com.rcurvo.domain.Persistent;

public interface ICustomerDAO<T extends Persistent> extends IGenericDAO<T, Long> {


}
