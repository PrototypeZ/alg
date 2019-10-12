package leetcode.q1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
class Solution {

    private int[] test2(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException();
        }
        /**
         * 建立一个哈希表，key 为值， value 为索引
         * 比如我要在这个哈希表中找 9 个这个数字，那就以 9 为 key 从这个哈希表中查找，
         * 如果找到了， value 即为 9 对应的索引。
         */
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 查找根据当前遍历到的第 i 个元素
            // ，在缓存中能否找到一个数相加为 target
            int t = target - nums[i];
            Integer index = cache.get(t);
            if (index != null && index != i) {
                return new int[]{i, index};
            }
            // 在上面没找到的情况下，把当前第 i 个元素放进缓存
            // 注意：一定要先检查，后放缓存，不能先放缓存后检查
            // 因为好考虑 target 正好由数组中两个相等的元素相加得到这种情况
            cache.put(nums[i], i);
        }
        throw new IllegalArgumentException();
    }

    // 仅在数组有序的情况下
    public int[] test1(int[] nums, int target) {
        if (nums.length < 2) return null;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int testSum = nums[i] + nums[j];
            if (testSum > target) {
                j--;
            } else if (testSum < target) {
                i++;
            } else {
                return new int[]{i, j};
            }
        }
        return null;
    }

    public int[] twoSum(int[] nums, int target) {
        return test2(nums, target);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new Solution().twoSum(new int[]{2, 7, 11, 15}, 9)
        ));

        System.out.println(Arrays.toString(
                new Solution().twoSum(new int[]{3, 3}, 6)
        ));
    }
}
