package leetcode.q46;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2019/10/20/0020.
 */
public class Solution {

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        } else if (nums.length == 1) {
            return Collections.singletonList(
                    Collections.singletonList(nums[0])
            );
        } else if (nums.length == 2) {
            List<List<Integer>> result = new ArrayList<>();
            return Arrays.asList(
                    Arrays.asList(nums[0], nums[1]),
                    Arrays.asList(nums[1], nums[0])
            );
        } else {
            List<List<Integer>> result = new ArrayList<>();
            List<List<Integer>> sub = permute(Arrays.copyOfRange(nums, 1, nums.length));
            for (List<Integer> p : sub) {
                for (int i = 0; i <= p.size(); i++) {
                    List<Integer> copyOfP = new ArrayList<>(p);
                    copyOfP.add(i, nums[0]);
                    result.add(copyOfP);
                }
            }
            return result;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permute(new int[]{1, 2, 3}));
    }
}
