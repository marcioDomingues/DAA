package mdo.fc.ul;

/**
 * Created by mdo on 5/5/17.
 * cuting rod example
 */
public class testes {
    /* Returns the best obtainable price for a rod of
       length n and price[] as prices of different pieces */
    static int cutRod(int price[],int n)
    {
        int val[] = new int[n+1];
        val[0] = 0;

        // Build the table val[] in bottom up manner and return
        // the last entry from the table
        for (int i = 1; i<=n; i++)
        {
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++)
                max_val = Math.max(max_val,
                        price[j] + val[i-j-1]);
            val[i] = max_val;
        }

        return val[n];
    }

    /* Driver program to test above functions */
    public static void main(String args[])
    {
        //int arr[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        int arr[] = new int[] {0,3,2,-3,-2,-1,0};
        int size = arr.length;
        if (validate ( arr[0],0, arr[arr.length-1], arr.length-1  ) ){
            System.out.println("Maximum Obtainable Value is " +
                    cutRod(arr, size));
        }

    }

    public static boolean validate( int i_angle, int i_time, int j_angle, int j_time ){
        boolean validation = Math.abs( j_angle - i_angle ) <= j_time - i_time;

        return validation;
    }

}
