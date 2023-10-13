import br.com.rcurvo.dao.CustomerDAO;
import br.com.rcurvo.dao.CustomerDB2DAO;
import br.com.rcurvo.dao.CustomerDB3DAO;
import br.com.rcurvo.dao.ICustomerDAO;
import br.com.rcurvo.domain.Customer;
import br.com.rcurvo.domain.Customer2;
import br.com.rcurvo.exceptions.DAOException;
import br.com.rcurvo.exceptions.KeyTypeNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class Customer3DatabasesTest {
    private ICustomerDAO<Customer> customerDAO;

    private ICustomerDAO<Customer> customerDB2DAO;

    private ICustomerDAO<Customer2> customerDB3DAO;

    private Random rd;

    public Customer3DatabasesTest(){
        this.customerDAO = new CustomerDAO();
        this.customerDB2DAO = new CustomerDB2DAO();
        this.customerDB3DAO = new CustomerDB3DAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<Customer> list1 = customerDAO.searchAll();
        list1.forEach(cli -> {
            try {
                customerDAO.delete(cli);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });

        Collection<Customer> list2 = customerDB2DAO.searchAll();
        list2.forEach(cli -> {
            try {
                customerDB2DAO.delete(cli);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });

        Collection<Customer2> list3 = customerDB3DAO.searchAll();
        list3.forEach(cli -> {
            try {
                customerDB3DAO.delete(cli);
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

        customer.setId(null);
        customerDB2DAO.register(customer);

        Customer fetchedCustomer1 = customerDB2DAO.search(customer.getId());
        Assert.assertNotNull(fetchedCustomer1);

        Customer2 customer2 = createCustomer2();
        customerDB3DAO.register(customer2);

        Customer2 fetchedCustomer2 = customerDB3DAO.search(customer2.getId());
        Assert.assertNotNull(fetchedCustomer2);
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

    private Customer2 createCustomer2(){
        Customer2 customer = new Customer2();
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
