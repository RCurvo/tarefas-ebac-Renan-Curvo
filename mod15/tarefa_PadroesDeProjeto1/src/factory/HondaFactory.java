package factory;

public class HondaFactory extends VehicleFactory{
    @Override
    Vehicle retrieveVehicle(String typeOfVehicle) {
        switch (typeOfVehicle){
            case "car":
                return new Car("Honda", 0, "A1B2C3");
            case "bike":
                return  new Bike("Honda", 0, "F1G2H3");
            default:
                return null;
        }
    }
}
