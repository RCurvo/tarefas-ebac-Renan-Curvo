package br.com.rcurvo;


import br.com.rcurvo.dao.CustomerDAO;
import br.com.rcurvo.dao.CustomerDAOMock;
import br.com.rcurvo.dao.ICustomerDAO;
import br.com.rcurvo.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;

public class CustomerServiceTest {


    @Test
    public void saveTest(){
        ICustomerDAO mock = new CustomerDAOMock();
        CustomerService service = new CustomerService(mock);
        String result = service.save();
        Assert.assertEquals("Success", result);

    }

    @Test(expected = UnsupportedOperationException.class)
    public void errorExpectedOnSave(){
        ICustomerDAO dao = new CustomerDAO();
        CustomerService service = new CustomerService(dao);
        String result = service.save();
        Assert.assertEquals("Success", result);

    }
}
