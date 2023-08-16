import java.util.ArrayList;
import java.util.List;

@Table(value = "CustomerTable")
public class CustomerDAO {

    CustomerDAO(){}
    List<Customer> list = new ArrayList<Customer>();
}
