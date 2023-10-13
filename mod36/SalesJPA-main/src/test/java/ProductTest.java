import br.com.rcurvo.dao.IProductDAO;
import br.com.rcurvo.dao.ProductDAO;
import br.com.rcurvo.domain.Product;
import br.com.rcurvo.exceptions.DAOException;
import br.com.rcurvo.exceptions.KeyTypeNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Random;

public class ProductTest {

    public IProductDAO productDAO;

    public Random rd;

    public ProductTest(){
        this.productDAO = new ProductDAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<Product> list = productDAO.searchAll();
        list.forEach(pro -> {
            try {
                productDAO.delete(pro);
            } catch (DAOException e){
                e.printStackTrace();
            }
        });
    }

    @Test
    public void search() throws DAOException, KeyTypeNotFoundException {
        Product product = createProduct();
        Assert.assertNotNull(product);
        Product productDB = this.productDAO.search(product.getId());
        Assert.assertNotNull(productDB);
    }

    @Test
    public void save() throws DAOException, KeyTypeNotFoundException {
        Product product = createProduct();
        Assert.assertNotNull(product.getId());
        Assert.assertNotNull(product);
    }

    @Test
    public void delete() throws DAOException, KeyTypeNotFoundException {
        Product product = createProduct();
        Assert.assertNotNull(product);
        productDAO.delete(product);
        Product productDB = productDAO.search(product.getId());
        Assert.assertNull(productDB);
    }

    @Test
    public void update() throws DAOException, KeyTypeNotFoundException {
        Product product = createProduct();
        product.setName("Prod 42");
        productDAO.update(product);
        Product productDB = productDAO.search(product.getId());
        Assert.assertNotNull(productDB);
        Assert.assertEquals("Prod 42", productDB.getName());
        Assert.assertEquals(product.getId(), productDB.getId());
    }

    @Test
    public void searchAll() throws DAOException, KeyTypeNotFoundException {
        createProduct();
        createProduct();
        Collection<Product> list = productDAO.searchAll();
        Assert.assertTrue(list != null);
        Assert.assertTrue(list.size() == 2);

        for (Product prod : list) {
            this.productDAO.delete(prod);
        }

        list = productDAO.searchAll();
        Assert.assertTrue(list != null);
        Assert.assertTrue(list.size() == 0);

    }

    private Product createProduct() throws DAOException, KeyTypeNotFoundException {
        Product product = new Product();
        product.setCode(rd.nextLong());
        product.setName("Prod 1");
        product.setDescription("Desc 1");
        product.setPrice(BigDecimal.valueOf(1000));
        productDAO.register(product);
        return product;
    }
}
