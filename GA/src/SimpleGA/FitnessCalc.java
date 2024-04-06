package SimpleGA;

import java.util.Arrays;
public class FitnessCalc {
    public static int [] populationFitness(int [][] individuals){
        int [] setSolution = new int[individuals.length];
        for (int k = 0; k < individuals.length; k++){
            int [][] decodedInformation = decodeChromosome(individuals[k]);
            int [] timeMatrix = processingTimeCalculator(decodedInformation);
            setSolution[k] = Arrays.stream(timeMatrix).max().getAsInt();
        }
        return setSolution;
    }
    public static int [][] decodeChromosome(int[] chromosome) {
        int[] processCode = new int[chromosome.length];
        int[] machine = new int[chromosome.length];
        int[] processTime = new int[chromosome.length];
        int workPiece1 = 1, workPiece2 = 1, workPiece3 = 1;
        for (int i = 0; i < chromosome.length; i++) {
            if (chromosome[i] == 1) { //still, the matrix chromosome comes from population file
                processCode[i] = 100 + workPiece1;
                machine[i] = WorkPieceData.workPiece1Data[workPiece1 - 1][1]; //how to utilize variable being defined at other place?
                processTime[i] = WorkPieceData.workPiece1Data[workPiece1 - 1][2];
                workPiece1++;
            } else if (chromosome[i] == 2) {
                processCode[i] = 200 + workPiece2;
                machine[i] = WorkPieceData.workPiece2Data[workPiece2 - 1][1];
                processTime[i] = WorkPieceData.workPiece2Data[workPiece2 - 1][2];
                workPiece2++;
            } else if (chromosome[i] == 3) {
                processCode[i] = 300 + workPiece3;
                machine[i] = WorkPieceData.workPiece3Data[workPiece3 - 1][1];
                processTime[i] = WorkPieceData.workPiece3Data[workPiece3 - 1][2];
                workPiece3++;
            }
        }
        int[][] information = new int [3][];
        information[0] = processCode;
        information[1] = machine;
        information[2] = processTime;
        return information; // is there a better way to return the information out of chromosome?
    }
    public static int [] processingTimeCalculator(int [][] information) {
        int [][] timeMatrix = new int[3][5]; // not necessary; all you need is to store one step ahead
        int machine1Time = 0;
        int machine2Time = 0;
        int machine3Time = 0;
        int machine4Time = 0;
        int machine5Time = 0;
        for (int j = 0; j < information[0].length; j++) {
            int processNumber  = information[0][j] % 100;
            int workPieceNumber = information[0][j] / 100;
            if (information[1][j] == 1)
                machine1Time = calculateMachineTime(workPieceNumber,processNumber,machine1Time,timeMatrix,information[2][j]);
            if (information[1][j] == 2)
                machine2Time = calculateMachineTime(workPieceNumber,processNumber,machine2Time,timeMatrix,information[2][j]);
            if (information[1][j] == 3)
                machine3Time = calculateMachineTime(workPieceNumber,processNumber,machine3Time,timeMatrix,information[2][j]);
            if (information[1][j] == 4)
                machine4Time = calculateMachineTime(workPieceNumber,processNumber,machine4Time,timeMatrix,information[2][j]);
            if (information[1][j] == 5)
                machine5Time = calculateMachineTime(workPieceNumber,processNumber,machine5Time,timeMatrix,information[2][j]);        }
        int[] machineTimeMatrix = {machine1Time,machine2Time,machine3Time,machine4Time,machine5Time};
        return machineTimeMatrix;
    }

    public static int calculateMachineTime(int workPieceNumber,int processNumber,int machineTime,int[][] timeStorage, int processTime) {
        int lastWorkPieceTime = processNumber == 1? 0:timeStorage[workPieceNumber-1][processNumber-2];
        machineTime = processNumber == 1 ? machineTime + processTime : Math.max(machineTime, lastWorkPieceTime) + processTime;
        timeStorage[workPieceNumber-1][processNumber-1] = machineTime;
        return machineTime;
    }

        public static  void main(String[] args) {
            int[][] temp = Population.generateIndividual();
            //int [] test = populationFitness(temp);
            int[] timeMatrix = new int[10];
            int[][] decodedInformation = new int[10][12];
            decodedInformation = decodeChromosome(temp[0]);
            timeMatrix = processingTimeCalculator(decodedInformation);
            for (int[] temps : decodedInformation) System.out.println(Arrays.toString(temps));
            for (int ints : timeMatrix) System.out.println(ints);


        }
}
