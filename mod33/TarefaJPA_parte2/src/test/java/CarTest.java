import br.com.rcurvo.dao.*;
import br.com.rcurvo.domain.Accessories;
import br.com.rcurvo.domain.Brand;
import br.com.rcurvo.domain.Car;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class CarTest {

    private ICarDAO carDAO;
    private IBrandDAO brandDAO;

    private IAccessoriesDAO accessoriesDAO;

    public CarTest(){
        carDAO = new CarDAO();
        brandDAO = new BrandDAO();
        accessoriesDAO = new AccessoriesDAO();
    }

    @Test
    public void register(){
        Brand brand = BuildBrand();
        Accessories accessories = BuildAccessories();
        Car car = BuildCar();
        Brand brandDB = brandDAO.register(brand);
        accessories.setBrand(brandDB);
        car.setBrand(brandDB);
        List<Accessories> accessoriesList = new ArrayList<Accessories>();
        accessoriesList.add(accessories);
        car.setAccessoriesList(accessoriesList);
        Car carDB = carDAO.register(car);
        accessories.setCar(carDB);
        Accessories accessoriesDB = accessoriesDAO.register(accessories);


        assertNotNull(brandDB);
        assertNotNull(brandDB.getId());

        assertNotNull(carDB);
        assertNotNull(carDB.getId());

        assertNotNull(accessoriesDB);
        assertNotNull(accessoriesDB.getId());
    }

    private static Accessories BuildAccessories(){
        Accessories accessories = new Accessories();
        String code = UUID.randomUUID().toString();
        accessories.setCode(code);
        accessories.setName("Radio");
        accessories.setPrice(BigDecimal.valueOf(200.00));
        return accessories;
    }

    private static Car BuildCar(){
        Car car = new Car();
        String code = UUID.randomUUID().toString();
        car.setCode(code);
        car.setModel("Etios");
        car.setPrice(BigDecimal.valueOf(50000.00));
        return car;
    }

    private static Brand BuildBrand(){
        Brand brand = new Brand();
        String code = UUID.randomUUID().toString();
        Accessories accessories = BuildAccessories();
        brand.setCode(code);
        brand.setName("Toyota");
        brand.setCountry("Japan");
        return brand;
    }
}
