import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortNames {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite nomes separados por vírgulas");
        String result = sc.nextLine();
        List<String> list = new ArrayList<>();
        list.addAll(List.of(result.split(",")));
        Collections.sort(list);
         System.out.println(list);
    }
}