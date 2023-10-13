package br.com.rcurvo.dao;

import br.com.rcurvo.dao.generic.IGenericDAO;
import br.com.rcurvo.domain.Sale;
import br.com.rcurvo.exceptions.DAOException;
import br.com.rcurvo.exceptions.KeyTypeNotFoundException;

public interface ISaleDAO extends IGenericDAO<Sale, Long> {

    public void finishSale(Sale sale) throws KeyTypeNotFoundException, DAOException;

    public void cancelSale(Sale sale) throws KeyTypeNotFoundException, DAOException;

    public Sale searchWithCollection(Long id);
}
