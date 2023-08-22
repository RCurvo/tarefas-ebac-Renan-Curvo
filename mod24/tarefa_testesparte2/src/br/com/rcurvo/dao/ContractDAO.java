package br.com.rcurvo.dao;

public class ContractDAO implements IContractDAO {

    @Override
    public void save() {
        throw new UnsupportedOperationException("Database is not working, failed to save");
    }

    @Override
    public void search() {
        throw new UnsupportedOperationException("Database is not working, failed to search");
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Database is not working, failed to delete");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Database is not working, failed to update");
    }
}
