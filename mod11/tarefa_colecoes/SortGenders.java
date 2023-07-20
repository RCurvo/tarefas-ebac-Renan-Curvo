import java.util.*;

public class SortGenders {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite nomes seguidos por '-' e M ou F para representar o sexo, separados por vírgulas");
        String result = sc.nextLine();
        String[] list = result.split(",");
        List<String> maleList = new ArrayList<>();
        List<String> femaleList = new ArrayList<>();
//        System.out.println(Arrays.toString(list));

        for(String person : list){
            String[] nameAndSex = person.split("-");
            if(nameAndSex[1].toLowerCase().charAt(0) == 'm'){
                maleList.add(nameAndSex[0]);
            } else if (nameAndSex[1].toLowerCase().charAt(0) == 'f') {
                femaleList.add(nameAndSex[0]);
            }
        }
        System.out.println("Lista de homens");
        System.out.println(maleList);
        System.out.println("Lista de mulheres");
        System.out.println(femaleList);
    }
}