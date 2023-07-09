import java.util.Scanner;

public class GradesPassFail {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite 4 notas apertando enter para submeter cada uma");
        float grade1 = sc.nextFloat();
        float grade2 = sc.nextFloat();
        float grade3 = sc.nextFloat();
        float grade4 = sc.nextFloat();

        float mean = (grade1 + grade2+grade3+grade4)/4;

        if(mean>= 7){
            System.out.println("Aprovado");
        } else if (mean <7 && mean >= 5) {
            System.out.println("Em Recuperação");

        } else {
            System.out.println("Reprovado");

        }
        System.out.println("Média final " + mean);
    }
}
