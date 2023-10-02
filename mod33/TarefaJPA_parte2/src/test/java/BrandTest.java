import br.com.rcurvo.dao.BrandDAO;
import br.com.rcurvo.dao.IBrandDAO;
import br.com.rcurvo.domain.Brand;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class BrandTest {

    private IBrandDAO brandDAO;

    public BrandTest(){
        brandDAO = new BrandDAO();
    }

    @Test
    public void register(){
        Brand brand = new Brand();
        String code = UUID.randomUUID().toString();
        brand.setCode(code);
        brand.setName("Toyota");
        brand.setCountry("Japan");
        Brand brandDB = brandDAO.register(brand);

        assertNotNull(brandDB);
        assertNotNull(brandDB.getId());
    }

}
