package br.com.rcurvo.service;

import br.com.rcurvo.dao.IContractDAO;

public class ContractService implements IContractService {

    private IContractDAO contractDAO;
    public ContractService(IContractDAO dao) {
        this.contractDAO = dao;
    }

    @Override
    public String save() {
        contractDAO.save();
        return "Success saving";
    }

    @Override
    public String search() {
        contractDAO.search();
        return "Success searching";
    }

    @Override
    public String delete() {
        contractDAO.delete();
        return "Success deleting";
    }

    @Override
    public String update() {
        contractDAO.update();
        return "Success updating";
    }
}
