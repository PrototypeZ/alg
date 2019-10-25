package leetcode.q90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        return subsetsWithDupInternal(nums, 0);
    }

    private List<List<Integer>> subsetsWithDupInternal(int[] nums, int current) {
        if (current == nums.length) {
            return Collections.singletonList(Collections.emptyList());
        }
        // current 后面最近的那个和 current 不相等的数距离 current
        // 如果 current 的下一个元素与 current 不相等， next == 1
        // 如果 current 的下一个元素与 current 相等， 但是 current 的下下个元素与 current 不相等， next == 2
        // 也就是说 next 表示有几个和 current 元素相等的元素，包括 current
        int next = 1;
        while (current + next < nums.length && nums[current] == nums[current + next]) next++;
        List<List<Integer>> lo = subsetsWithDupInternal(nums, current + next);
        List<List<Integer>> l1 = new ArrayList<>(lo);

        // 含有相同元素前缀的范围为 1 ~ next
        for (int i = 1; i <= next; i++) {
            List<Integer> prefix = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                prefix.add(nums[current]);
            }

            for (List<Integer> list : lo) {
                List<Integer> newList = new ArrayList<>(list);
                newList.addAll(0, prefix);
                l1.add(newList);
            }
        }
        return l1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subsetsWithDup(new int[]{1, 2, 2}));
    }

}
