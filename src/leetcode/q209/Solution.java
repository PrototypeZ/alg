package leetcode.q209;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。

 示例: 

 输入: s = 7, nums = [2,3,1,2,4,3]
 输出: 2
 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 进阶:

 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。

 在真实的面试中遇到过这道题？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2020/2/4/0004.
 */
public class Solution {

    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        /**
         * sums[i] 代表 nums 数组中 [0,i] 元素的和
         */
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        int result = Integer.MAX_VALUE;
        // 用这个found变量是有讲究的，如果不用这个变量，最后只能通过 result 是否等于 Integer.MAX_VALUE 来判断是否
        // 找到我们所需的子数组，但是理论上存在一种情况，s 就是由 Integer.MAX_VALUE 长的子数组构成，所以为了区分这种情况
        // 我们使用了 found 变量
        boolean found = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length && j - i + 1 < result; j++) {
                // 要计算 nums 数组 [i,j] 元素的和，只要求 sums[j] - sums[i-1] 即可
                int tmpSum = sums[j] - (i == 0 ? 0 : sums[i - 1]);
                if (tmpSum >= s && j - i + 1 <= result) {
                    result = j - i + 1;
                    found = true;
                    if (result == 1) return 1;
                    break;
                }
            }
        }
        return found ? result : 0;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        /**
         * sums[i] 代表 nums 数组中 [0,i] 元素的和
         */
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        int result = Integer.MAX_VALUE;
        // 用这个found变量是有讲究的，如果不用这个变量，最后只能通过 result 是否等于 Integer.MAX_VALUE 来判断是否
        // 找到我们所需的子数组，但是理论上存在一种情况，s 就是由 Integer.MAX_VALUE 长的子数组构成，所以为了区分这种情况
        // 我们使用了 found 变量
        boolean found = false;
        for (int i = 0; i < nums.length; i++) {
            // 和上一种思路相比，使用了二分查找的思想
            // 这里的思想是，在所有以 i 为区间左界的子数组中，查找最短的子数组，使子数组的和大于等于 s
            int idx = binarySearch(sums, i, 0, nums.length - 1, s);
            if (idx != -1 && idx - i + 1 <= result) {
                result = idx - i + 1;
                found = true;
            }
        }
        return found ? result : 0;
    }



    /**
     * nums 为原数组
     * sums 数组中每个元素为原数组 [0, i] 的元素的和（i为 nums, sums 数组中元素的下标）
     *
     * 虚构一个数组 arr，arr 的长度范围为 [offset, nums.length - 1] , i 为这个数组下标，
     * 这个数组中的每个元素的意义是 nums 数组中 [offset, i] 所有元素的和，即
     * arr[i] = sums[i] - (offset == 0 ? 0 : sums[offset - 1])
     * 显而易见， arr 数组是一个升序数组，如果在 arr 数组中查找一个数，可以使用二分查找
     *
     * @param sums
     * @param offset
     * @param start
     * @param end
     * @param s
     * @return
     */
    private int binarySearch(int[] sums, int offset, int start, int end, int s) {
        int sumsOffset = offset == 0 ? 0 : sums[offset - 1];
        if (start == end) {
            return (sums[start] - sumsOffset >= s) ? start : -1;
        } else if (start < end) {
            int middle = (start + end) / 2;
            // arr[middle]
            if (sums[middle] - sumsOffset >= s) {
                int result = binarySearch(sums, offset, start, middle - 1, s);
                return result == -1 ? middle : result;
            } else {
                return binarySearch(sums, offset, middle + 1, end, s);
            }
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(solution.minSubArrayLen2(7, new int[]{2, 3, 1, 2, 4, 3}));
        // error cases
        System.out.println(solution.minSubArrayLen(3, new int[]{1, 1}));
        System.out.println(solution.minSubArrayLen2(3, new int[]{1, 1}));
    }
}
