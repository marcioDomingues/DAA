package mdo.fc.ul;


/**
 * Created by mdomingues on 04/05/17.
 */
/******************************************************************************
 *  Compilation:  javac Knapsack.java
 *  Execution:    java Knapsack N W
 *
 *  Generates an instance of the 0/1 knapsack problem with N items
 *  and maximum weight W and solves it in time and space proportional
 *  to N * W using dynamic programming.
 *
 *  For testing, the inputs are generated at random with weights between 0
 *  and W, and profits between 0 and 1000.
 *
 *  %  java Knapsack 6 2000
 *  item    profit  weight  take
 *  1       874     580     true
 *  2       620     1616    false
 *  3       345     1906    false
 *  4       369     1942    false
 *  5       360     50      true
 *  6       470     294     true
 *
 ******************************************************************************/

public class work {

    public static void main(String[] args) {
        int N = 5;   // number of items
        int W = 11;   // maximum weight of knapsack

        int[] profit = new int[N+1];
        int[] weight = new int[N+1];

        //profit = {1, 6, 18, 22, 28};
        //weight = {1,2,5,6,7};

        profit[1]=1;
        profit[2]=6;
        profit[3]=18;
        profit[4]=22;
        profit[5]=28;

        weight[1]=1;
        weight[2]=2;
        weight[3]=5;
        weight[4]=6;
        weight[5]=7;


        // generate random instance, items 1..N
        //for (int n = 1; n <= N; n++) {
        //    profit[n] = StdRandom.uniform(1000);
        //    weight[n] = StdRandom.uniform(W);
        //}

        // opt[n][w] = max profit of packing items 1..n with weight limit w
        // sol[n][w] = does opt solution to pack items 1..n with weight limit w include item n?
        int[][] opt = new int[N+1][W+1];
        boolean[][] sol = new boolean[N+1][W+1];

        for (int n = 1; n <= N; n++) {
            for (int w = 1; w <= W; w++) {

                // don't take item n
                int option1 = opt[n-1][w];

                // take item n
                int option2 = Integer.MIN_VALUE;
                if (weight[n] <= w) option2 = profit[n] + opt[n-1][w-weight[n]];

                // select better of two options
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }

        // determine which items to take
        boolean[] take = new boolean[N+1];
        for (int n = N, w = W; n > 0; n--) {
            if (sol[n][w]) {
                take[n] = true;
                w = w - weight[n];
            }
            else {
                take[n] = false;
            }
        }




        // print results
        System.out.println("item" + " \t " + "profit" + " \t " + "weight" + " \t " + "take");
        System.out.println("----" + "--|--" + "------" + "--|--" + "------" + "--|--" + "----" );
        for (int n = 1; n <= N; n++) {
            System.out.println( String.format("%1$6s",n ) + " \t " + String.format("%1$6s", profit[n]) + " \t " + String.format("%1$6s", weight[n]) + " \t " + take[n]);
        }




        System.out.println( knapsack(weight, profit, W));

    }//end of main





    private static int knapsack( int[] w, int[] v, int W) {
        int[][] dp = new int[w.length][W + 1];

        dp[0][0] = 0;

        for(int i = 1 ; i < dp[0].length; i++){
            if(w[0] <= i){
                dp[0][i] = v[0];
            }else{
                dp[0][i] = 0;
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                // if a weight is more than the allowed weight, that weight cannot be picked.
                if(w[i] > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], v[i] + dp[i-1][j-w[i]]);
                }
            }
        }
        return dp[dp.length-1][W];
    }
}
