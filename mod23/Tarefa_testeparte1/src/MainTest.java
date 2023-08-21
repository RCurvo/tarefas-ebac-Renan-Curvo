import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Test
    public void main() {
        System.out.println("Digite nomes seguidos por '-' e M ou F para representar o sexo, separados por vírgulas");
        List<String> personList = new ArrayList<>();
        addMembers(personList);
        List<String[]> nameAndSex = new ArrayList<>();

        System.out.println("********************************TODOS OS MEMBROS COM SEXO**************************************");
        personList.stream().forEach(p -> {
            System.out.println(p);
            nameAndSex.add(p.split("-"));
        });

        List<String[]> females = nameAndSex.stream().filter(person -> "f".equals(person[1].toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("*********************************MULHERES*************************************");
        females.stream().forEach(female -> System.out.println(female[0]));

        females.forEach(female -> {
            Assert.assertEquals("f", female[1]);
        });


    }

    public void addMembers(List list){
        list.add("Mariana-f");
        list.add("Bruna-f");
        list.add("Caio-m");
        list.add("Renan-m");
        list.add("Joao-m");
        list.add("Luiza-f");
    }
}