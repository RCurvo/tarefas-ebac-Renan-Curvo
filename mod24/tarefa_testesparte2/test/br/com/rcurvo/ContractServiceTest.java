package br.com.rcurvo;

import br.com.rcurvo.dao.ContractDAO;
import br.com.rcurvo.dao.ContractDAOMock;
import br.com.rcurvo.dao.IContractDAO;
import br.com.rcurvo.service.ContractService;
import br.com.rcurvo.service.IContractService;
import org.junit.Assert;
import org.junit.Test;

public class ContractServiceTest {

    @Test
    public void saveTest(){
        IContractDAO dao = new ContractDAOMock();
        IContractService service = new ContractService(dao);
        String result = service.save();
        Assert.assertEquals("Success saving", result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void expectErrorSavingInDatabaseSave(){
        IContractDAO dao = new ContractDAO();
        IContractService service = new ContractService(dao);
        String result = service.save();
        Assert.assertEquals("Success saving", result);
    }

    @Test
    public void searchTest(){
        IContractDAO dao = new ContractDAOMock();
        IContractService service = new ContractService(dao);
        String result = service.search();
        Assert.assertEquals("Success searching", result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void expectErrorSavingInDatabaseSearch(){
        IContractDAO dao = new ContractDAO();
        IContractService service = new ContractService(dao);
        String result = service.search();
        Assert.assertEquals("Success searching", result);
    }
    @Test
    public void DeleteTest(){
        IContractDAO dao = new ContractDAOMock();
        IContractService service = new ContractService(dao);
        String result = service.delete();
        Assert.assertEquals("Success deleting", result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void expectErrorSavingInDatabaseDelete(){
        IContractDAO dao = new ContractDAO();
        IContractService service = new ContractService(dao);
        String result = service.delete();
        Assert.assertEquals("Success deleting", result);
    }
    @Test
    public void updateTest(){
        IContractDAO dao = new ContractDAOMock();
        IContractService service = new ContractService(dao);
        String result = service.update();
        Assert.assertEquals("Success updating", result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void expectErrorSavingInDatabaseUpdate(){
        IContractDAO dao = new ContractDAO();
        IContractService service = new ContractService(dao);
        String result = service.update();
        Assert.assertEquals("Success updating", result);
    }
}
