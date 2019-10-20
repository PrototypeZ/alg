package leetcode.q39;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

 candidates 中的数字可以无限制重复被选取。

 说明：

 所有数字（包括 target）都是正整数。
 解集不能包含重复的组合。 
 示例 1:

 输入: candidates = [2,3,6,7], target = 7,
 所求解集为:
 [
 [7],
 [2,2,3]
 ]
 示例 2:

 输入: candidates = [2,3,5], target = 8,
 所求解集为:
 [
   [2,2,2,2],
   [2,3,3],
   [3,5]
 ]


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/combination-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2019/10/20/0020.
 */
public class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 先对输入数组进行排序，为后面查找过程中的去重做准备
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationInternal(candidates, target, new ArrayList<>(), result);
        return result;
    }

    /**
     *
     * @param candidates 排完序后的数组
     * @param target 要组成的值
     * @param path 查找路径，由 candidates 中的数的下标组成，path 中的下标 i, 规定 path[i+1] >= path[i]，
     *             这样就可以保证找出来的 path 没有重复的
     * @param result 合格的 path 中，把下标对应的值找出来即组成了 result
     */
    private void combinationInternal(int[] candidates, int target, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            // found!
            result.add(path.stream().map(index -> candidates[index]).collect(Collectors.toList()));
            return;
        } else if (target < 0) {
            // ignore
            return;
        }
        for (int i = path.size() == 0 ? 0 : path.get(path.size() - 1); i < candidates.length; i++) {
            int step = candidates[i];
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(i);
            combinationInternal(candidates, target - step, newPath, result);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.combinationSum(new int[]{2, 3, 6, 7}, 7)); // [[7], [2,2,3]]
        System.out.println(solution.combinationSum(new int[]{2, 3, 5}, 8)); // [[2,2,2,2], [2,3,3], [3,5]]
        System.out.println(solution.combinationSum(new int[]{4, 5, 2}, 16)); // [[2,2,2,2,2,2,2,2],[2,2,2,2,2,2,4],[2,2,2,2,4,4],[2,2,2,5,5],[2,2,4,4,4],[2,4,5,5],[4,4,4,4]]
    }
}
