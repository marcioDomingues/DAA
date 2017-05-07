package mdo.fc.ul;

import java.util.ArrayList;

/**
 * Created by mdo on 5/7/17.
 */
public class DAA_observe_events {

    public ArrayList<Integer> findMaxObservableEvents(int[] angles) {

        int[] LIS = new int[angles.length];

        for (int i = 0; i < angles.length; i++) {
            int max = 0;

            if ( Math.abs( angles[i] - angles[angles.length-1] ) <= (angles.length-1) - i && (Math.abs(angles[i] - 0) <=  i+1 ) ) {

                //System.out.println("in with i= " + i + " angle of " + angles[i]);

                for (int j = 0; j <= i; j++) {

                    //System.out.print("\tdiff angulos : " + ( Math.abs(angles[j] - angles[i])  )  );
                    //System.out.println(" __ diff index : " + (j - i) );

                    if ( Math.abs(angles[j] - angles[i]) <= i - j   ) {
                        // update the max from the previous entries
                        //System.out.println("\t\tin with i= "+i+ " Value of: " + angles[i] );

                        if ( max == 0 || max < LIS[j] + 1 ) {
                            //System.out.println("\t\trLIS[j]= "+ LIS[j] );
                            max = 1 + LIS[j];
                        }
                    }
                }
            }
            if (max == -1) {
                // means none of the previous element has smaller than arrA[i]
                max = 0;
            }
            LIS[i] = max;
        }

        // find the max in the LIS[]
        int result = -1;
        int index = -1;
        for (int i = 0; i < LIS.length; i++) {
            if (result < LIS[i]) {
                result = LIS[i];
                index = i;
            }
        }

        // Print the result
        // Start moving backwards from the end and
        String path = angles[index] + " ";

        ArrayList<Integer> resultArray = new ArrayList<Integer>() ;
        resultArray.add(index);

        int res = result - 1;
        for (int i = index - 1; i >= 0; i--) {
            //removing the initial position from result array
            if ( LIS[i] == res  ) {
                path = angles[i] + " " + path;
                resultArray.add(i);
                System.out.println("index: " + (i));
                res--;
            }
            //System.out.println("inter results: " + path);
        }

        System.out.println("Longest Increasing subsequence: " + result);
        System.out.println("Actual Elements: " + path);

        return resultArray;
    }





    public static void main(String[] args) {
        //INPUTS
        //int[] inputArray = { 1, 12, 7, 0, 23, 11, 52, 31, 61, 69, 70, 2 };
        //int[] inputArray = {3,2,-3,-2,-1,0};
        int[] inputArray = {1, -4, -1, 4, 5, -4, 6, 7, -2};

        //TODO add first telescope position
        //n+1
        //int[] inputArray = {0, 1, -4, -1, 4, 5, -4, 6, 7, -2};
        //int[] A = {0,3,2,-3,-2,-1,0};
        //int[] A = {-2};

        int[] angles = new int[inputArray.length+1];
        //include the initial position of the telescope
        //copy input values into new array
        angles[0]=0;
        for(int i=0;i < inputArray.length;i++) {
            angles[i+1] = inputArray[i];
        }

        DAA_observe_events i = new DAA_observe_events();

        ArrayList<Integer> res = i.findMaxObservableEvents(angles);

        System.out.println( res );


    }


}