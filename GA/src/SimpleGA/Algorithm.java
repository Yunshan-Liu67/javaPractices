package SimpleGA;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static SimpleGA.Utils.count;
import static SimpleGA.Utils.shuffleArray;
public class Algorithm {
    public static int elitistCount = 0;
    public static int [][] selection(int [][] individuals, int[] timeMatrix){
        int minTime = Arrays.stream(timeMatrix).min().getAsInt();
        float [] detection = new float [individuals.length];
        for (int i = 0; i < individuals.length; i++){
            detection[i] = (float)(timeMatrix[i])/IntStream.of(timeMatrix).sum();}
        int[][] newGeneration = (int[][]) individuals.clone();
        for (int j = 0; j < individuals.length; j++){
            if (timeMatrix[j] == minTime){
                newGeneration[elitistCount] = individuals[j];
                elitistCount ++;
                detection[j] = 2;
            }
        }
        int shuffleNumber = elitistCount;
        for (int k = 0; k < individuals.length; k++){
            if (shuffleNumber < individuals.length){
               double rand = Math.random(); //change variable name!
                if (detection[k] < (float)(rand)){
                    newGeneration[shuffleNumber] = individuals[k];
                    shuffleNumber ++;
                }
            }
        }
        return newGeneration;
    }
    public static int [][] mutation(int [][] individuals,double mutationRate){
    for (int l = elitistCount; l < individuals.length; l ++){
        if (mutationRate > Math.random()){
            Random rand = new Random();
            int position1 = rand.nextInt(individuals[0].length - 1); //what I want to do here: a random integer from 1 to 12
            int position2 = rand.nextInt(individuals[0].length - 1);
            while(position1 == position2){
                position2 = rand.nextInt(individuals[0].length - 1);
            }
            int [] chromosome = individuals[l].clone();
            int swap = chromosome[position1];
            chromosome[position1] = chromosome[position2];
            chromosome[position2] = swap;
            individuals[l] = chromosome.clone();
            }
        }
    return individuals;
    }
    public static int [][] crossOver(int[][] individuals, double crossoverRate){ // Partially mapped crossover
        int [][] newPopulation = individuals.clone();
        int [] beforePermutation = {0,1,2,3,4,5,6,7,8,9};
        int [] individualPosition = shuffleArray(beforePermutation);
        for ( int m = elitistCount ; m < individuals.length - 1; m ++){
            double permutationOdd = Math.random();
            if (crossoverRate > permutationOdd){
                if (individualPosition[m] < elitistCount -1 || individualPosition[m+1] < elitistCount -1) break;
                int [] chromosome1 = individuals[individualPosition[m]].clone();
                int [] chromosome2 = individuals[individualPosition[m+1]].clone();
                int crossOverPoint = (int)(Math.floor(permutationOdd * individuals[0].length));
                int [] crossedChromosome1 = Arrays.copyOfRange(chromosome2,crossOverPoint,individuals[0].length);
                int [] crossedChromosome2 = Arrays.copyOfRange(chromosome1,crossOverPoint,individuals[0].length);
                int crossed1WorkPiece1Count = count(crossedChromosome1,1);
                int crossed1WorkPiece2Count = count(crossedChromosome1,2);
                int crossed1WorkPiece3Count = count(crossedChromosome1,3);
                int crossed2WorkPiece1Count = count(crossedChromosome2,1);
                int crossed2WorkPiece2Count = count(crossedChromosome2,2);
                int crossed2WorkPiece3Count = count(crossedChromosome2,3);
                int [] postProcessedChromosome1 = new int[chromosome1.length - (crossed1WorkPiece1Count + crossed1WorkPiece2Count + crossed1WorkPiece3Count)];
                int [] postProcessedChromosome2 = new int[chromosome2.length - (crossed2WorkPiece1Count + crossed2WorkPiece2Count + crossed2WorkPiece3Count)];
                int connectPt1 = WorkPieceData.workPiece1Data.length - crossed1WorkPiece1Count;
                Arrays.fill(postProcessedChromosome1,0,connectPt1,1);
                int connectPt2 = connectPt1 + WorkPieceData.workPiece2Data.length - crossed1WorkPiece2Count;
                Arrays.fill(postProcessedChromosome1,connectPt1,connectPt2 ,2); // number is not right;
                int connectPt3 = connectPt2 + WorkPieceData.workPiece3Data.length - crossed1WorkPiece3Count;
                Arrays.fill(postProcessedChromosome1,connectPt2,connectPt3,3);
                int connectPt1_ = WorkPieceData.workPiece1Data.length - crossed2WorkPiece1Count;
                Arrays.fill(postProcessedChromosome2,0,connectPt1_,1);
                int connectPt2_ = connectPt1_ + WorkPieceData.workPiece2Data.length - crossed2WorkPiece2Count;
                Arrays.fill(postProcessedChromosome2,connectPt1_,connectPt2_,2);
                int connectPt3_ = connectPt2_ + WorkPieceData.workPiece3Data.length - crossed2WorkPiece3Count;
                Arrays.fill(postProcessedChromosome2,connectPt2_,connectPt3_,3);
                int [] mutatedChromosome1 = shuffleArray(postProcessedChromosome1);
                int [] mutatedChromosome2 = shuffleArray(postProcessedChromosome2);
                int [] finalChromosome1 = IntStream.concat(Arrays.stream(mutatedChromosome1), Arrays.stream(crossedChromosome1)).toArray();
                int [] finalChromosome2 = IntStream.concat(Arrays.stream(mutatedChromosome2), Arrays.stream(crossedChromosome2)).toArray();
                newPopulation[individualPosition[m]] = finalChromosome1.clone();
                newPopulation[individualPosition[m + 1]] = finalChromosome2.clone();
            }
        }
        return  newPopulation;
    }
    // step 1: initialize the population, calculate the objective (fitness)
    //step 2: check termination condition: if: 90% of the population is the same chromosome, terminate. else: go to step 3
    //step 3: selection, crossover, mutation and go back to step 2
}
