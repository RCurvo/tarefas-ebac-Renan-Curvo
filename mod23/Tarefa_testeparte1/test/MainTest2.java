import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class MainTest2 {


    @Test
    public void testIfAllAreFemales() {
        String simulatedUserInput = "Mariana-f,Bruna-f,Caio-m,Renan-m,Raissa-f,Rodrigo-M";
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);


        Main.main(null);
        List<String[]> females = Main.getFemales();

        females.forEach(female -> {
            Assert.assertEquals("f", female[1]);
        });


    }

}