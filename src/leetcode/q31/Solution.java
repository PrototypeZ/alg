package leetcode.q31;

import java.util.Arrays;

public class Solution {

    public void nextPermutation(int[] nums) {
        int j = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                j = i;
            }
        }
        if (j == -1) {
            // j not found
            Arrays.sort(nums);
            return;
        }
        // j found
        int k = j + 1;
        for (int i = j + 2; i < nums.length; i++) {
            if (nums[i] > nums[j]) {
                k = i;
            }
        }
        // exchange j and k
        int tmp = nums[j];
        nums[j] = nums[k];
        nums[k] = tmp;

        // reverse & exchange between j+1 ~ n (inclusive)
        // j+1 .......i........nums.length - 1
        // i  <--->  nums.length - 1 - (i-j-1)
        // i  <--->  nums.length - i + j)
        for (int i = j + 1; i <= (j + 1 + nums.length - 1) / 2; i++) {
            int t = nums[i];
            nums[i] = nums[nums.length - 1 - i + j + 1];
            nums[nums.length - i + j] = t;
        }
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


        int[] test4 = new int[]{1, 3, 2};
        solution.nextPermutation(test4);
        System.out.println(Arrays.toString(test4));

        int[] test5 = new int[]{2, 3, 1};
        solution.nextPermutation(test5);
        System.out.println(Arrays.toString(test5));
    }
}
