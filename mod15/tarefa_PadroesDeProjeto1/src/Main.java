import factory.*;

public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Honda", true, "car");
        VehicleFactory factory = getFactory(customer1);
        Vehicle customer1vehicle = factory.create(customer1.getTypeOfVehicle());
        customer1vehicle.drive();

    }

    private static VehicleFactory getFactory(Customer customer){
        if (!customer.CanPay()){
            System.out.println("Sorry, customer can't pay for the car");
            return null;
        }
        switch (customer.getDesiredManufacturer()){
            case "Toyota":
                return  new ToyotaFactory();
            case "Honda":
                return  new HondaFactory();
            default:
                System.out.println("We were not able to find manufacturer");
                return null;
        }
    }
}