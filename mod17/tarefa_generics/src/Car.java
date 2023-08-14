public abstract class Car {
    private String licensePlate;
    private String model;

    public Car(String licensePlate, String model){
        this.licensePlate = licensePlate;
        this.model = model;
    }

    public void drive(){
        String name = this.getClass().getSimpleName();
        System.out.println(name + " is driving");

    }
}
