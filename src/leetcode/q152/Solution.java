package leetcode.q152;

public class Solution {

    public int maxProduct(int[] nums) {
        /*
         * dpMax 中的第 i 个元素（dpMax[i]）代表的意思:
         * 原数组中，以 nums[i] 为结尾的所有子数组
         * （例如 nums[0]~nums[i], nums[1]~nums[i], nums[2]~nums[i], ..., nums[i-1]~nums[i]）中,
         * 这样一共有 i + 1 个子数组，对每个可能的子数组求这个子数组内所有元素的积，这样能求出来 i + 1 个积，
         * dpMax[i], 表示这些积里面最大的那个值。
         *
         * 所以，dpMax 这个数组内，最大的那个值，就是 nums 所有可能子串里，子串乘积最大的值
         * 因为 dpMax 求解的过程中就已经把所有可能的子串都已经遍历过一遍了
         *
         * 如果我们已经算出了 dpMax[i], 那么计算 dpMax[i+1] 将会更容易：
         * 1. 如果 nums[i+1] > 0
         *   1.1 且 dpMax[i] <= 0, 那么 dpMax[i+1] = nums[i+1]
         *   1.2 且 dpMax[i] > 0, 那么 dpMax[i+1] = dpMax[i] * nums[i+1]
         *   综合上面两种情况： dpMax[i+1] = Math.max(nums[i+1], dpMax[i] * nums[i+1])
         *
         * 2. 如果 nums[i+1] == 0, 那么 dpMax[i+1] = 0
         *   这种情况下，也可以套用情况 1 的结论： dpMax[i+1] = Math.max(nums[i+1], dpMax[i] * nums[i+1])
         *
         * 3. 如果 nums[i+1] < 0
         *   3.1 且 dpMax[i] < 0, 那么 dpMax[i+1] = dpMax[i] * nums[i+1]
         *   3.2 且 dpMax[i] >= 0, 那么此时情况有点复杂，虽然 dpMax[i] >= 0，但是
         *   保不齐在所有以 nums[i] 为最后一个元素的子数组中，存在数组所有元素乘积小于 0 的情况，
         *   一旦存在这样的子数组，我们要找出乘积最小的那个（负数，且绝对值最大），这个乘积
         *   与 nums[i+1]相乘，才是我们要找的 dpMax[i+1]
         */
        int[] dpMax = new int[nums.length];

        /*
         * 为了解决 3.2 遇到的问题， 我们引入了 dpMin， dpMin 中的每个元素代表的数字和 dpMax 类似，
         * 不同的是 dpMin 是所有积里面最小且为负的那个值
         *
         * *
         * 虽然上面分析的很复杂，其实一句话可以概括：
         * 已知 dpMax[i], nums[i+1]， 要知道 dpMax[i+1]的话，只要求 nums[i+1], nums[i+1]*dpMax[i], nums[i+1]*dpMin[i]
         * 这三个数中最大的那个，就是我们要求的 dpMax[i+1]
         *
         * 同样的这三个数中最小的那个，就是我们要求的 dpMin[i+1]
         */
        int[] dpMin = new int[nums.length];

        int currentMax = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                dpMax[0] = nums[0];
                dpMin[0] = nums[0];
                currentMax = dpMax[0];
            } else {
                dpMax[i] = Math.max(nums[i], Math.max(dpMin[i - 1] * nums[i], dpMax[i - 1] * nums[i]));
                dpMin[i] = Math.min(nums[i], Math.min(dpMin[i - 1] * nums[i], dpMax[i - 1] * nums[i]));
                if (dpMax[i] > currentMax) {
                    currentMax = dpMax[i];
                }
            }
        }
        return currentMax;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(solution.maxProduct(new int[]{-2, 0, -1}));
    }
}
