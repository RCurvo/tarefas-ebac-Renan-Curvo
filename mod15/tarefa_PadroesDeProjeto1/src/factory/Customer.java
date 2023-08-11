package factory;

public class Customer {
    public String getDesiredManufacturer() {
        return desiredManufacturer;
    }
    public String getTypeOfVehicle(){
        return typeOfVehicle;
    }
    public boolean CanPay() {
        return hasMoney;
    }

    private String desiredManufacturer;
    private String typeOfVehicle;
    private boolean hasMoney;

    public Customer(String desiredManufacturer, boolean hasMoney, String typeOfVehicle){
        this.desiredManufacturer = desiredManufacturer;
        this.hasMoney = hasMoney;
        this.typeOfVehicle = typeOfVehicle;
    }
}
