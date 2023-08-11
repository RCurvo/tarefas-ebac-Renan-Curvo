package factory;

public abstract class Vehicle {
    private String manufacturer;
    private int fuel;
    private String licensePlate;

    public Vehicle(String manufacturer, int fuel, String licensePlate){
        this.manufacturer = manufacturer;
        this.fuel = fuel;
        this.licensePlate = licensePlate;

    }

    public void addFuel(int fuel){
        this.fuel= fuel;
    }

    public void drive(){
        System.out.println(getClass().getSimpleName());
        System.out.println(this.manufacturer + " " + getClass().getSimpleName() + " is driving ");
        System.out.println("License Plate: " + this.licensePlate);
        this.fuel--;
        System.out.println("Fuel Level: " + this.fuel);
    }
}
