package KnapsackProblem;

import BinarySearch.Algo;

import java.util.Arrays;
import java.util.List;

public class Knapsack {
     int Binary(int W, int N, int[] wt, int[] val){
        assert  N == wt.length;
        assert N == val.length;
        // base case initialization
        int [][] dp = new int [N+1][W+1];
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= W; j++){
                if (j - wt[i - 1] < 0){
                    // not enough space for item i, has no choice but not to put in
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(
                            dp[i-1][j-wt[i-1]] + val[i-1],
                            dp[i-1][j]
                    );
                }
            }
        }
        return dp[N][W];
    }
    }
