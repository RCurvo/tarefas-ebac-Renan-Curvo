import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> carList = new ArrayList<Car>();
        Honda honda = new Honda("21413", "ABC");
        carList.add(honda);
        carList.add(new Toyota("A1B2D4", "Toyota"));

        for (Car car : carList){
            car.drive();
        }
    }
}