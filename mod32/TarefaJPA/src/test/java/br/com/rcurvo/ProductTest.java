package br.com.rcurvo;

import br.com.rcurvo.dao.IProductDAO;
import br.com.rcurvo.dao.ProductDAO;
import br.com.rcurvo.domain.Product;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class ProductTest {
    private IProductDAO productDAO;

    public ProductTest(){
        productDAO = new ProductDAO();
    }

    @Test
    public void register(){
        Product product = new Product();
        String code = UUID.randomUUID().toString();
        product.setCode("A3");
        product.setName("Televisão");
        product.setDescription("55 polegadas");
        product.setPrice(2999.00f);
        product = productDAO.register(product);

        assertNotNull(product);
        assertNotNull(product.getId());


    }
}
