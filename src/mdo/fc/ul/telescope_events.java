package mdo.fc.ul;

/**
 * Created by mdo on 5/7/17.
 */

public class telescope_events {

    public void findSubsequence(int[] arrA) {
        int[] LIS = new int[arrA.length];
        for (int i = 0; i < arrA.length; i++) {
            int max = -1;
            for (int j = 0; j < i; j++) {
                // check if previous elements > current element
                if (arrA[i] > arrA[j]) {
                    // update the max from the previous entries
                    if (max == -1 || max < LIS[j] + 1) {
                        max = 1 + LIS[j];
                    }
                }
            }
            if (max == -1) {
                // means none of the previous element has smaller than arrA[i]
                max = 1;
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
        String path =  arrA[index] + " ";
        int res = result-1;
        for (int i = index-1; i >= 0; i--) {
            if(LIS[i]==res){
                path =  arrA[i] + " " + path;
                res--;
            }
        }
        System.out.println("Longest Increasing subsequence: " + result);
        System.out.println("Actual Elements: " + path);
    }

    public static void main(String[] args) {
        //INPUTS
        //int[] A = { 1, 12, 7, 0, 23, 11, 52, 31, 61, 69, 70, 2 };
        //int[] A = {0,3,2,-3,-2,-1,0};
        int[] A = {1,-4,-1,4,5,-4,6,7,-2};


        telescope_events i = new telescope_events();
        i.findSubsequence(A);
    }

}