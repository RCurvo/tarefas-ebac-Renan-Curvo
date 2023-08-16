import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        CustomerDAO customerTable = new CustomerDAO();
        customerTable.list.add(new Customer("Renan"));
        Customer customer = customerTable.list.get(0);
        System.out.println(customer.getName());
    }
}