import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) {
        CustomerDAO customerTable = new CustomerDAO();
        customerTable.list.add(new Customer("Renan"));
        Customer customer = customerTable.list.get(0);
        System.out.println(customer.getName());
//      Annotation[] annotations = customerTable.getClass().getAnnotations();
        Table table = customerTable.getClass().getAnnotation(Table.class);
        String value = table.value();
        System.out.println("Tipo: " + table.annotationType());
        System.out.println("valor: " + value);

//        for (Annotation an : annotations){
//            System.out.println(an.toString());
//        }
    }
}