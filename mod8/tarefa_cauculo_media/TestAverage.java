public class TestAverage {

    public static void main(String[] args){
        float score1 = 10.0f;
        float score2= 8.5f;
        float score3= 4.5f;
        float score4 = 7.3f;
      float result= testAverage(score1, score2, score3, score4);
      System.out.println(result);

    }


    public static float testAverage(float score1, float score2, float score3, float score4){
        float result = (score1 + score2 + score3 +score4)/4;
        return result;
    }
}
