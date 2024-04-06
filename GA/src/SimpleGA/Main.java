package SimpleGA;

import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        double mutationRate = 0.03;
        double crossOverRate = 0.4;
        int maxIterationNumber = 100;
        int [] minTime = new int [maxIterationNumber];
        int[][] population = Population.generateIndividual();  // population initialization;
        for (int iterationNumber = 0; iterationNumber < maxIterationNumber;iterationNumber++){
            int [] setSolution = FitnessCalc.populationFitness(population);// population fitness calculation;
            minTime[iterationNumber] = Arrays.stream(setSolution).min().getAsInt();
            int [][] newPopulation = Algorithm.selection(population,setSolution);//step 1:selection;
            int [][] crossOvered = Algorithm.crossOver(newPopulation,crossOverRate);//step 2:crossover;
            int [][] mutated = Algorithm.mutation(crossOvered,mutationRate);//step 3: mutation;
            Algorithm.elitistCount = 0;
            population = (int[][]) mutated.clone();
            }
        System.out.println(Arrays.toString(minTime));

    }
}