package leetcode.q31;

import java.util.Arrays;

public class Solution {

    public void nextPermutation(int[] nums) {


    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] test1 = new int[]{1, 2, 3};
        solution.nextPermutation(test1);
        System.out.println(Arrays.toString(test1));

        int[] test2 = new int[]{3, 2, 1};
        solution.nextPermutation(test2);
        System.out.println(Arrays.toString(test2));

        int[] test3 = new int[]{1, 1, 5};
        solution.nextPermutation(test3);
        System.out.println(Arrays.toString(test3));
    }
}
