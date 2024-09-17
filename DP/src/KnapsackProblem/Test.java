package KnapsackProblem;

import BinarySearch.Algo;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String args[]) {
        Knapsack knapsack= new Knapsack();
        int N = 3;
        int W = 4;
        int[] wt = {2, 1, 3};
        int[] val = {4, 2, 3};
        Integer solution; //test failed
        solution = knapsack.Binary(W,N,wt,val);
        System.out.println(solution);
    }
}
