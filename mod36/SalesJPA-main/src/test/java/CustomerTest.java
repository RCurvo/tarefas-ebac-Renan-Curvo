import br.com.rcurvo.dao.CustomerDAO;
import br.com.rcurvo.dao.ICustomerDAO;
import br.com.rcurvo.domain.Customer;
import java.util.Collection;
import br.com.rcurvo.exceptions.DAOException;
import br.com.rcurvo.exceptions.KeyTypeNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class CustomerTest {
    private ICustomerDAO<Customer> customerDAO;

    private Random rd;

    public CustomerTest(){
        this.customerDAO = new CustomerDAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<Customer> list = customerDAO.searchAll();
        list.forEach(cli -> {
            try {
                customerDAO.delete(cli);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void searchCustomer() throws DAOException, KeyTypeNotFoundException {
        Customer customer = createCustomer();
        customerDAO.register(customer);

        Customer fetchedCustomer = customerDAO.search(customer.getId());
        Assert.assertNotNull(fetchedCustomer);
    }

    @Test
    public void registerCustomer() throws DAOException, KeyTypeNotFoundException {
        Customer customer = createCustomer();
        Customer result = customerDAO.register(customer);
        Assert.assertNotNull(result);

        Customer fetchedCustomer = customerDAO.search(result.getId());
        Assert.assertNotNull(fetchedCustomer);

        customerDAO.delete(result);

        Customer fetchedCustomer1 = customerDAO.search(result.getId());
        Assert.assertNull(fetchedCustomer1);
    }

    @Test
    public void deleteCustomer() throws DAOException, KeyTypeNotFoundException {
        Customer customer = createCustomer();
        Customer result = customerDAO.register(customer);
        Assert.assertNotNull(result);

        Customer fetchedCustomer = customerDAO.search(customer.getId());
        Assert.assertNotNull(fetchedCustomer);

        customerDAO.delete(customer);
        fetchedCustomer = customerDAO.search(customer.getId());
        Assert.assertNull(fetchedCustomer);
    }

    @Test
    public void updateCustomer() throws DAOException, KeyTypeNotFoundException {
        Customer customer = createCustomer();
        Customer result = customerDAO.register(customer);
        Assert.assertNotNull(result);

        Customer fetchedCustomer = customerDAO.search(customer.getId());
        Assert.assertNotNull(fetchedCustomer);

        fetchedCustomer.setName("Renan Curvo");
        customerDAO.update(fetchedCustomer);

        Customer updatedCustomer = customerDAO.search(fetchedCustomer.getId());
        Assert.assertNotNull(updatedCustomer);
        Assert.assertEquals("Renan Curvo", updatedCustomer.getName());

        customerDAO.delete(customer);
        fetchedCustomer = customerDAO.search(updatedCustomer.getId());
        Assert.assertNull(fetchedCustomer);
    }

    @Test
    public void searchAll() throws KeyTypeNotFoundException, DAOException {
        Customer customer = createCustomer();
        Customer result = customerDAO.register(customer);
        Assert.assertNotNull(result);

        Customer customer1 = createCustomer();
        Customer result1 = customerDAO.register(customer1);
        Assert.assertNotNull(result1);

        Collection<Customer> list = customerDAO.searchAll();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                customerDAO.delete(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<Customer> list1 = customerDAO.searchAll();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }


    private Customer createCustomer(){
        Customer customer = new Customer();
        customer.setCpf(rd.nextLong());
        customer.setName("Renan");
        customer.setState("Alagoas");
        customer.setAddress("Address");
        customer.setCity("Maceio");
        customer.setPhone(rd.nextLong());
        customer.setHouseNumber(rd.nextInt());
        return customer;
    }




}
