package br.com.rcurvo.dao;

import br.com.rcurvo.dao.generic.GenericDAO;
import br.com.rcurvo.dao.generic.GenericDB1DAO;
import br.com.rcurvo.domain.Sale;
import br.com.rcurvo.exceptions.DAOException;
import br.com.rcurvo.exceptions.KeyTypeNotFoundException;

public class SaleExclusionDAO extends GenericDB1DAO<Sale, Long> implements ISaleDAO {
    public SaleExclusionDAO() {
        super(Sale.class);
    }

    @Override
    public void finishSale(Sale sale) throws KeyTypeNotFoundException, DAOException {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public void cancelSale(Sale sale) throws KeyTypeNotFoundException, DAOException {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public Sale searchWithCollection(Long id) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }
}
