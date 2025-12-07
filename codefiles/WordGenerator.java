package codefiles;
import java.util.Random;

public class WordGenerator {
   private static RandWords words;

   public static int getRandomIndex() {
        Random random = new Random();
        return random.nextInt(15);
   }

   public static void updateWords(){ //Needs to be updates when the year level changes
      RandWords words = RandWordsFactory.createRandWords(GradGoMad.currentYearLevel);
   }

   public static String generateWord(){
      int indexNum = getRandomIndex();
      return words.getRandword(indexNum);
   }
}