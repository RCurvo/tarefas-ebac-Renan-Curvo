import java.util.Scanner;

public class Wrapper {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um número");
        float result = sc.nextFloat();
        Float resultWrapper = result;

        System.out.println(resultWrapper);
    }
}
