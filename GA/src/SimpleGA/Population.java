package SimpleGA;

import java.util.Arrays;

public class Population {
    public static int [] defaultChromosome = {1,1,1,1,2,2,2,3,3,3,3,3};
    public static int chromosomeSize = defaultChromosome.length;
    public static int individualsSize = 10;
    public static int[][] generateIndividual() {
         int[][] individuals = new int[individualsSize][chromosomeSize];
        for (int i = 0; i < individualsSize; i++){
            individuals[i] = Utils.shuffleArray(defaultChromosome);
        }
        return individuals;
    }
    public static void setDefaultPopulation(int length) {
        individualsSize = length;
    }
    public static void setDefaultChromosome(int[] seed) {
        defaultChromosome = seed;
    }
    public static  void main(String[] args) {
        int[][] temp = generateIndividual();
        for (int[] ints : temp) System.out.println(Arrays.toString(ints));
            }
    }


