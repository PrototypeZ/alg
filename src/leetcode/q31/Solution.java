package leetcode.q31;

import java.util.Arrays;

public class Solution {

    /**
     * 求全排列字典序的一下个排列是把当前排列
     * (P1P2…Pj-1)   (Pj)    (Pj+1…Pk-1)   (Pk)   (Pk+1…Pn)
     * 变成
     * (P1P2…Pj-1)   (Pk)   (Pn…Pk+1)     (Pj)    (Pk-1…Pj+1)
     * 即可。
     *
     * 其中 j 为原排列中求一个最大的下标，使 nums[j] < nums[j+]
     * k 为在原排列中求一个最大的下标，使 nums[k] > nums[j]。
     * 通过上面的描述可以知道，如果找到了这个 j , 那么 k 至少是 j + 1，
     * 如果找不到 j，那么 k 也无从谈起，这种情况下，所求的下一个排列为全排列字典序的第一个排列
     * 即升序排列
     *
     *
     * 实际求这个下一个排列的算法中，我们方便的做法是先互换 Pk Pj，然后把 j+1 ~ n 整个序列反转即可。
     */
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
