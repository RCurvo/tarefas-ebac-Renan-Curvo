public class AirConditioner {

    public static void main(String[] args){
AirConditioner airConditioner = new AirConditioner();
        airConditioner.turnOn();
        airConditioner.temperatureUp();
        airConditioner.temperatureUp();
        airConditioner.temperatureDown();
        airConditioner.temperatureDown();
        airConditioner.turnOff();

    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private int voltage;
    private int temperature;
    private String color;

    public int getVoltage() {
        return voltage;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getColor() {
        return color;
    }





    public void turnOn() {
        System.out.println("Ar Condicionado Ligado");
        this.setTemperature(20);
    }
    public void temperatureUp(){
        this.setTemperature(this.temperature+ 1);
        System.out.println("Temperatura aumentada para " + temperature);
    }

    public void temperatureDown(){
        this.setTemperature(this.temperature- 1);
        System.out.println("Temperatura reduzida para " + temperature);
    }

    public void turnOff() {
        System.out.println("Ar Condicionado desligado");
    }


}
