package leetcode.q47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    // curSize 表示当前的路径 path 里面有多少个元素
    // visited , curSize 这两个变量其实可以用 List 来代替，但是 List 如果想知道某个元素在里面是否出现需要遍历，时间复杂度高，所以这里属于空间换时间
    private void generatePermution(int[] nums, boolean[] visited,  Stack<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            // 此时 path 已经保存了 nums 中的所有数字，已经成为了一个排列
            res.add(new ArrayList<>(path));
            return;
        }
        Integer lastUsed = null;
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            // start: added for unique
            if (lastUsed != null && nums[i] == lastUsed) continue;
            lastUsed = nums[i];
            // end: added for unique

            path.push(nums[i]);
            visited[i] = true;
            generatePermution(nums, visited, path, res);
            // 刚开始接触回溯算法的时候常常会忽略状态重置
            // 回溯的时候，一定要记得状态重置
            path.pop();
            visited[i] = false;
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length > 0) {

            // start: added for unique result
            Arrays.sort(nums);
            // end: added for unique result

            generatePermution(nums, new boolean[nums.length], new Stack<>(), res);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.permuteUnique(new int[]{1, 2, 3}));
        System.out.println(solution.permuteUnique(new int[]{1, 1, 2})); // [[1,1,2], [1,2,1], [2,1,1]]
    }

}
