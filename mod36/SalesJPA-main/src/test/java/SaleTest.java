import br.com.rcurvo.dao.*;
import br.com.rcurvo.domain.Customer;
import br.com.rcurvo.domain.Product;
import br.com.rcurvo.domain.Sale;
import br.com.rcurvo.domain.Sale.Status;
import br.com.rcurvo.exceptions.DAOException;
import br.com.rcurvo.exceptions.KeyTypeNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.*;

public class SaleTest {

    private final ISaleDAO saleDAO;

    private ISaleDAO saleExclusionDAO;

    private ICustomerDAO customerDAO;

    private IProductDAO productDAO;

    private Random rd;

    private Customer customer;

    private Product product;

    public SaleTest(){
        this.saleDAO = new SaleDAO();
        saleExclusionDAO = new SaleExclusionDAO();
        this.customerDAO = new CustomerDAO();
        this.productDAO = new ProductDAO();
        rd = new Random();
    }

    @Before
    public void init() throws DAOException, KeyTypeNotFoundException {
        this.customer = createCustomer();
        this.product = createProduct(BigDecimal.TEN);
    }

    @After
    public void end() throws DAOException {
        deleteSales();
        deleteProducts();
        customerDAO.delete(this.customer);
    }

    @Test
    public void search() throws DAOException, KeyTypeNotFoundException {
        Sale sale = createSale("A2");
        Sale result = saleDAO.register(sale);
        assertNotNull(result);
        Sale searchedSale = saleDAO.search(sale.getId());
        assertNotNull(searchedSale);
        assertEquals(sale.getCode(), searchedSale.getCode());
    }

    @Test
    public void register() throws DAOException, KeyTypeNotFoundException {
        Sale sale = createSale("A2");
        Sale result = saleDAO.register(sale);
        assertNotNull(result);

        assertTrue(sale.getTotalSum().equals(BigDecimal.valueOf(20)));
        assertTrue(sale.getStatus().equals(Status.STARTED));

        Sale searchedSale = saleDAO.search(sale.getId());
        assertTrue(searchedSale.getId() != null);
        assertEquals(sale.getCode(), searchedSale.getCode());
    }

    @Test
    public void cancelSale() throws DAOException, KeyTypeNotFoundException {
        String saleCode = "A3";
        Sale sale = createSale(saleCode);
        Sale resultSale = saleDAO.register(sale);
        assertNotNull(resultSale);
        assertNotNull(sale);
        assertEquals(saleCode, sale.getCode());

        resultSale.setStatus(Status.CANCELED);
        saleDAO.cancelSale(sale);

        Sale searchedSale = saleDAO.search(sale.getId());
        assertEquals(saleCode, searchedSale.getCode());
        assertEquals(Status.CANCELED, searchedSale.getStatus());
    }

    @Test
    public void addSameProduct() throws DAOException, KeyTypeNotFoundException {
        String saleCode = "A4";
        Sale sale = createSale(saleCode);
        Sale result = saleDAO.register(sale);
        assertNotNull(result);
        assertNotNull(sale);
        assertEquals(saleCode, sale.getCode());

        Sale searchedSale = saleDAO.searchWithCollection(sale.getId());
        searchedSale.addProduct(product, 1);

        assertTrue(searchedSale.getProductsQuantity() == 3);
        BigDecimal totalSum = BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(searchedSale.getTotalSum().equals(totalSum));
        assertTrue(searchedSale.getStatus().equals(Status.STARTED));
    }

    @Test
    public void addDifferentProduct() throws DAOException, KeyTypeNotFoundException {
        String saleCode = "A5";
        Sale sale = createSale(saleCode);
        Sale result = saleDAO.register(sale);
        assertNotNull(result);
        assertNotNull(sale);
        assertEquals(saleCode, sale.getCode());

        Product difProd = createProduct(BigDecimal.valueOf(50));
        assertNotNull(difProd);

        Sale searchedSale = saleDAO.searchWithCollection(sale.getId());
        searchedSale.addProduct(difProd, 1);
        assertTrue(searchedSale.getProductsQuantity() == 3);

        BigDecimal totalSum = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(searchedSale.getTotalSum().equals(totalSum));
        assertTrue(searchedSale.getStatus().equals(Status.STARTED));
    }

    @Test(expected = DAOException.class)
    public void registerSaleWithSameCode() throws DAOException, KeyTypeNotFoundException {
        Sale sale = createSale("A6");
        Sale result = saleDAO.register(sale);
        assertNotNull(result);
        assertNotNull(sale);
        assertEquals(sale.getCode(), result.getCode());

        Sale sale1 = createSale("A6");
        Sale result1 = saleDAO.register(sale1);
        assertNull(result1);
        assertTrue(result.getStatus().equals(Status.STARTED));
    }

    @Test
    public void removeProduct() throws DAOException, KeyTypeNotFoundException {
        String saleCode = "A7";
        Sale sale = createSale(saleCode);
        Sale result = saleDAO.register(sale);
        assertNotNull(result);
        assertNotNull(sale);
        assertEquals(saleCode, sale.getCode());

        Product difProd = createProduct(BigDecimal.valueOf(50));
        assertNotNull(difProd);

        Sale searchedSale = saleDAO.searchWithCollection(sale.getId());
        searchedSale.addProduct(difProd, 1);
        assertTrue(searchedSale.getProductsQuantity() == 3);

        BigDecimal totalSum = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(searchedSale.getTotalSum().equals(totalSum));

        searchedSale.removeProduct(difProd,1);
        assertTrue(searchedSale.getProductsQuantity() == 2);
        totalSum = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(searchedSale.getTotalSum().equals(totalSum));
        assertTrue(searchedSale.getStatus().equals(Status.STARTED));
    }

    @Test
    public void removeOneProduct() throws DAOException, KeyTypeNotFoundException {
        String saleCode = "A8";
        Sale sale = createSale(saleCode);
        Sale result = saleDAO.register(sale);
        assertNotNull(result);
        assertNotNull(sale);
        assertEquals(saleCode, sale.getCode());

        Product difProd = createProduct(BigDecimal.valueOf(50));
        assertNotNull(difProd);

        Sale searchedSale = saleDAO.searchWithCollection(sale.getId());
        searchedSale.addProduct(difProd, 1);
        assertTrue(searchedSale.getProductsQuantity() == 3);

        BigDecimal totalSum = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(searchedSale.getTotalSum().equals(totalSum));

        searchedSale.removeProduct(product,1);
        assertTrue(searchedSale.getProductsQuantity() == 2);
        totalSum = BigDecimal.valueOf(60).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(searchedSale.getTotalSum().equals(totalSum));
        assertTrue(searchedSale.getStatus().equals(Status.STARTED));
    }

    @Test
    public void removeAllProducts() throws DAOException, KeyTypeNotFoundException {
        String saleCode = "A8";
        Sale sale = createSale(saleCode);
        Sale result = saleDAO.register(sale);
        assertNotNull(result);
        assertNotNull(sale);
        assertEquals(saleCode, sale.getCode());

        Product difProd = createProduct(BigDecimal.valueOf(50));
        assertNotNull(difProd);

        Sale searchedSale = saleDAO.searchWithCollection(sale.getId());
        searchedSale.addProduct(difProd, 1);
        assertTrue(searchedSale.getProductsQuantity() == 3);

        BigDecimal totalSum = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(searchedSale.getTotalSum().equals(totalSum));

        searchedSale.removeAllProducts();
        assertTrue(searchedSale.getProductsQuantity() == 0);
        totalSum = BigDecimal.valueOf(0);
        assertTrue(searchedSale.getTotalSum().equals(totalSum));
        assertTrue(searchedSale.getStatus().equals(Status.STARTED));
    }

    @Test
    public void finishSale() throws DAOException, KeyTypeNotFoundException {
        String saleCode = "A10";
        Sale sale = createSale(saleCode);
        Sale result = saleDAO.register(sale);
        assertNotNull(result);
        assertNotNull(sale);
        assertEquals(saleCode, sale.getCode());

        sale.setStatus(Status.DONE);
        saleDAO.finishSale(sale);

        Sale searchedSale = saleDAO.searchWithCollection(sale.getId());
        assertEquals(searchedSale.getId(),sale.getId());
        assertEquals(Status.DONE, searchedSale.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void tryToAddProductFinishedSale() throws DAOException, KeyTypeNotFoundException {
        String saleCode = "A10";
        Sale sale = createSale(saleCode);
        Sale result = saleDAO.register(sale);
        assertNotNull(result);
        assertNotNull(sale);
        assertEquals(saleCode, sale.getCode());

        sale.setStatus(Status.DONE);
        saleDAO.finishSale(sale);

        Sale searchedSale = saleDAO.searchWithCollection(sale.getId());
        assertEquals(searchedSale.getId(),sale.getId());
        assertEquals(Status.DONE, searchedSale.getStatus());

        Product difProd = createProduct(BigDecimal.valueOf(50));
        assertNotNull(difProd);
        searchedSale.addProduct(difProd, 1);
    }

    private void deleteSales() throws DAOException {
        Collection<Sale> list = this.saleExclusionDAO.searchAll();
        list.forEach(sale -> {
            try {
                this.saleExclusionDAO.delete(sale);
            } catch (DAOException e){
                e.printStackTrace();
            }
        });
    }

    private void deleteProducts() throws DAOException {
        Collection<Product> list = this.productDAO.searchAll();
        list.forEach(prod -> {
            try {
                this.productDAO.delete(prod);
            } catch (DAOException e){
                e.printStackTrace();
            }
        });
    }

    private Customer createCustomer() throws DAOException, KeyTypeNotFoundException {
        Customer customer = new Customer();
        customer.setCpf(rd.nextLong());
        customer.setName("Renan");
        customer.setState("Alagoas");
        customer.setAddress("Address");
        customer.setCity("Maceio");
        customer.setPhone(rd.nextLong());
        customer.setHouseNumber(rd.nextInt());
        customerDAO.register(customer);
        return customer;
    }

    private Product createProduct(BigDecimal price) throws DAOException, KeyTypeNotFoundException {
        Product product = new Product();
        product.setCode(rd.nextLong());
        product.setName("Prod 1");
        product.setDescription("Desc 1");
        product.setPrice(price);
        productDAO.register(product);
        return product;
    }

    private Sale createSale(String code){
        Sale sale = new Sale();
        sale.setCode(code);
        sale.setDateSale(Instant.now());
        sale.setCustomer(this.customer);
        sale.addProduct(this.product, 2);
        sale.setStatus(Status.STARTED);
        return sale;
    }
}
