package factory;

public class ToyotaFactory extends VehicleFactory{
    @Override
    Vehicle retrieveVehicle(String typeOfVehicle) {
        switch (typeOfVehicle){
            case "car":
                    return new Car("Toyota", 0, "A1B2C3");
            case "bike":
                return  new Bike("Toyota", 0, "F1G2H3");
            default:
                return null;
        }
    }
}
