package SimpleGA;
import java.util.Random;
public class Utils {
    public static int[] shuffleArray(int[] array) { // reminder: remove shuffleArray method to utils
        int[] newArray = array.clone();
        Random rand = new Random();
        for (int i = newArray.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            // Swap elements
            int temp = newArray[index];
            newArray[index] = newArray[i];
            newArray[i] = temp;
        }
        return newArray;
    }
    public static int count(int [] array, int target){
        int numberCount = 0;
        for (int element : array) {
            if (element == target) {
                numberCount++;
            }
        }
    return numberCount;
    }
}
