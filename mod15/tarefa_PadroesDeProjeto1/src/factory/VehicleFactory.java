package factory;

public abstract class VehicleFactory {
    public Vehicle create(String typeOfVehicle){
        Vehicle vehicle = retrieveVehicle(typeOfVehicle);
        vehicle = prepareVehicle(vehicle);
        return vehicle;
    }

    private Vehicle prepareVehicle(Vehicle vehicle){
        System.out.println("Cleaning vehicle");
        System.out.println("Checking vehicle");
        vehicle.addFuel(100);
        return vehicle;
    }

    abstract Vehicle retrieveVehicle(String typeOfVehicle);
}
