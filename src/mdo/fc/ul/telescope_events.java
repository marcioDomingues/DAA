package mdo.fc.ul;

import sun.jvm.hotspot.debugger.posix.elf.ELFSectionHeader;

import java.util.ArrayList;

/**
 * Created by mdo on 5/7/17.
 */

public class telescope_events {

    public void findSubsequence(int[] angles) {


        int[] LIS = new int[angles.length];

        for (int i = 0; i < angles.length; i++) {
            int max = 0;
            //int max = 1;
            //System.out.println( "pos: " + i + " angle: " + angles[i] + " valide to 0 >> " + (Math.abs(angles[i] - 0)) + " " + (i+1) + " " + (Math.abs(angles[i] - 0) <= i+1 ));
            //System.out.println( (Math.abs(angles[i] - angles[angles.length-1]) <= (angles.length-1) - i ) );
            if ( Math.abs( angles[i] - angles[angles.length-1] ) <= (angles.length-1) - i && (Math.abs(angles[i] - 0) <=  i+1 ) ) {
                System.out.println("in with i= " + i + " angle of " + angles[i]);

                for (int j = 0; j <= i; j++) {
                    // check if previous elements > current element
                    //if (arrA[i] > arrA[j]) {
                    // |dj - di| <= j-i
                    //System.out.println("TESTE i:" + i + " " + j + " (" + (i - j) + ") " + angles[i] + " " + angles[j] + " (" + (Math.abs(angles[i] - angles[j])) + ") LOGO " + (Math.abs(angles[i] - angles[angles.length-1]) <= (angles.length-1) - i && (Math.abs(angles[i] - 0) <=  i+1 )  ));
                    //if (Math.abs(angles[i] - angles[j]) <= i - j) {
                    System.out.print("\tdiff angulos : " + ( Math.abs(angles[j] - angles[i])  )  );
                    System.out.println(" __ diff index : " + (j - i) );

                    if ( Math.abs(angles[j] - angles[i]) <= i - j   ) {
                        // update the max from the previous entries
                        System.out.println("\t\tin with i= "+i+ " Value of: " + angles[i] );

                        if ( max == 0 || max < LIS[j] + 1 ) {
                            System.out.println("\t\trLIS[j]= "+ LIS[j] );
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
            //System.out.println( "LISTA: " + LIS[i] );
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
        System.out.println();
        // Print the result
        // Start moving backwards from the end and
        String path = angles[index] + " ";
        int res = result - 1;
        for (int i = index - 1; i >= 0; i--) {
            if ( LIS[i] == res ) {
                path = angles[i] + " " + path;
                System.out.println("index: " + (i));
                res--;
            }

            System.out.println("inter results: " + path);
        }

        System.out.println("Longest Increasing subsequence: " + result);
        System.out.println("Actual Elements: " + path);
    }


    //make array with possible events e that satisfy
    // | ang(e) - ang(0)| <= e - 0
    // and being n the last event
    // | ang(n) - ang(e)| <= n - e
    public void onlyPossibleEvents(int[] angles) {
        ArrayList<Integer> PEvents = new ArrayList<Integer>();
        // telescope inicial position == 0
        int tPos = 0;
        // final event position and time
        int nTime = angles.length - 1;
        System.out.println(angles[nTime]);
        int nPos = angles[nTime];

        for (int i = 0; i < angles.length; i++) {

            boolean bool1 = (Math.abs(angles[i] - 0) <= i + 1);
            boolean bool2 = (Math.abs(angles[i] - nPos) <= nTime - i);


            System.out.println(bool1 + " " + bool2 + ">>" + (i + 1) + "," + angles[i]);


            if (bool1 && bool2) {
                PEvents.add(angles[i]);
            } else {
                PEvents.add(0);
            }
        }

        System.out.print("POSIBLE angles: {");
        for (int e : PEvents) {
            System.out.print(e + ",");
        }
        System.out.print("}\n");
    }


    ////////////////////////////////////////
    //n is the array size with the initial telescope position
    public void findMaxNodes(int[] angles, int n) {

        //System.out.println(angles.length);
        //int n= angles.length;
        //base case
        if (n == 1) {
            //TODO replace sysout by returns
            System.out.println("BASE CASE >> index: " + n + " angle: " + angles[n - 1]);
        }

        // Create an array to store solutions of subproblems.  table[i]
        // stores the profit for jobs till arr[i] (including arr[i])
        //int *table = new int[n];
        //table[0] = arr[0].profit;
        //
        int []table = new int[n];
        table[0] = 1;


        // Fill entries in M[] using recursive property
        for (int i = 1; i < n; i++) {
            // Find profit including the current job
            int inclProf = 1;

            int l = latestNonConflict(angles, i);

            if (l != -1) {
                inclProf += 1;
                System.out.println("inclProf: " + inclProf);
                for (int e : table) {
                    System.out.print(e + ",");
                }

            }
            // Store maximum of including and excluding
            //table[i] = max(inclProf, table[i - 1]);
        }

        // Store result and free dynamic memory allocated for table[]
        //int result = table[n - 1];
        //delete[] table;

        //return result;

        //System.out.println("Longest Increasing subsequence: " + result);
        //System.out.println("Actual Elements: " + path);

    }

    ////////////////////////////////////////
    // Find the latest job (in sorted array) that doesn't
    // conflict with the job[i]. If there is no compatible job,
    // then it returns -1.
    int latestNonConflict(int arr[], int n) {
        // telescope inicial position == 0
        int tPos = 0;
        // final event position and time
        int nTime = arr.length - 1;
        //System.out.println(arr[nTime]);
        int nPos = arr[nTime];

        for (int j = n - 1; j >= 0; j--) {
            boolean bool1 = (Math.abs(arr[j] - 0) <= j + 1);
            boolean bool2 = (Math.abs(arr[j] - nPos) <= nTime - j);

            if (bool1 && bool2) {
                System.out.println("index: " + j + " angle: " + arr[j]);
                return j;
            }
        }
        return -1;
    }
    ////////////////////////////////////////

    public static void main(String[] args) {
        //INPUTS
        //int[] A = { 1, 12, 7, 0, 23, 11, 52, 31, 61, 69, 70, 2 };
        //int[] A = {3,2,-3,-2,-1,0};
        //int[] A = {1, -4, -1, 4, 5, -4, 6, 7, -2};
        //TODO add first telescope position
        //n+1
        int[] A = {1, -4, -1, 4, 5, -4, 6, 7, -2};
        //int[] A = {0,3,2,-3,-2,-1,0};
        //int[] A = {-2};

        telescope_events i = new telescope_events();

        i.findSubsequence(A);

        System.out.println("\n FINAL \n");

        //TODO its garrantied that we can go from angle 0 to last angle DO THE CHECK
        //i.findMaxNodes(A, A.length);
    }


}