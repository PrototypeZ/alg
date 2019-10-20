package leetcode.q40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

 candidates 中的每个数字在每个组合中只能使用一次。

 说明：

 所有数字（包括目标数）都是正整数。
 解集不能包含重复的组合。 
 示例 1:

 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 所求解集为:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 示例 2:

 输入: candidates = [2,5,2,1,2], target = 5,
 所求解集为:
 [
   [1,2,2],
   [5]
 ]


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/combination-sum-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2019/10/20/0020.
 */
public class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSum2Internal(candidates, target, new ArrayList<>(),  result);
        return result;
    }

    private void combinationSum2Internal(int[] candidates, int target, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            // found one
            result.add(path.stream().map(idx -> candidates[idx]).collect(Collectors.toList()));
            return;
        }
        if (target < 0) {
            // ignore it
            return;
        }
        int pathSize = path.size();
        int start = pathSize == 0 ? 0 : path.get(pathSize - 1) + 1;
        for (int i = start; i < candidates.length; i++) {
            // 这一行是剪枝的重要步骤，在同一层次，如果“平级”的前一个路径和当前路径走的值是一样的，直接跳过
            // 注意 i > start 而不是 i > 0
            if (i > start && candidates[i] == candidates[i-1]) continue;

            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(i);
            combinationSum2Internal(candidates, target - candidates[i], newPath, result);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
        System.out.println(solution.combinationSum2(new int[]{2,5,2,1,2}, 5));

    }
}
