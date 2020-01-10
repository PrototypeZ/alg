package leetcode.q152;

public class Solution {
    public int maxProduct(int[] nums) {

        int i = 0;
        int j = 0;
        int currentMaxValue = nums[0];
        int currentAbsValue = Math.abs(currentMaxValue);

        while (i <= j && j < nums.length - 1) {
            int newValue = nums[j + 1] * currentAbsValue;
            int newAbsValue = Math.abs(newValue);
            if (newAbsValue > currentAbsValue) {
                currentAbsValue = newAbsValue;
                if (newValue > currentMaxValue) {
                    currentMaxValue = newValue;
                }
                j++;
            } else {
                int testValue;
                do {
                    testValue = currentMaxValue / nums[++i];
                } while (testValue <= currentMaxValue && i <= j);
                if (testValue > currentMaxValue) {
                    currentMaxValue = testValue;
                }
                currentAbsValue = Math.abs(testValue);
                if (i > j) {
                    i = j;
                }
            }
        }
        return currentMaxValue;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(solution.maxProduct(new int[]{-2, 0, -1}));
    }
}
