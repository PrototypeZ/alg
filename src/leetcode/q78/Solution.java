package leetcode.q78;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        result.add(Collections.emptyList());
//        if (nums.length >= 1) {
//            result.addAll(subsetsInternal(nums, 0));
//        }
//        return result;
        return subsetsInternal(nums, 0);
    }
    // 典型的动态规划
    private List<List<Integer>> subsetsInternal(int[] nums, int start) {
        if (start == nums.length - 1) {
            return Arrays.asList(Collections.singletonList(nums[nums.length - 1]), Collections.emptyList());
        }
        List<List<Integer>> subSets = subsetsInternal(nums, start + 1);
        List<List<Integer>> result = new ArrayList<>(subSets);
        result.add(Collections.singletonList(nums[start]));
        for (List<Integer> set : subSets) {
            List<Integer> newSet = new ArrayList<>(set);
            newSet.add(0, nums[start]);
            result.add(newSet);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subsets(new int[]{1, 2, 3}));
    }
}
