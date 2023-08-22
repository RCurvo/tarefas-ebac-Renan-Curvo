import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static List<String[]> getFemales() {
        return females;
    }

    private static List<String[]> females = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite nomes seguidos por '-' e M ou F para representar o sexo, separados por vírgulas");
        String result = sc.nextLine();
        List<String> PersonList = Arrays.asList(result.split(","));
        List<String[]> nameAndSex = new ArrayList<>();


        System.out.println("********************************TODOS OS MEMBROS COM SEXO**************************************");
        PersonList.stream().forEach(p -> {
            System.out.println(p);
            nameAndSex.add(p.split("-"));
        });

        females = nameAndSex.stream().filter(person -> "f".equals(person[1].toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("*********************************MULHERES*************************************");
        females.stream().forEach(female -> System.out.println(female[0]));


    }
}