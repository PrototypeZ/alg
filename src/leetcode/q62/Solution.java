package leetcode.q62;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 *
 *
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * 典型的动态规划问题
     */
    public int uniquePaths(int m, int n) {
        Integer[][] cache = new Integer[m][n];
        cache[0][0] = 1;
        return uniquePathsInternal(m - 1, n - 1, cache);
    }

    private int uniquePathsInternal(int m, int n, Integer[][] cache) {
        if (cache[m][n] == null) {
            int left = n > 0 ? uniquePathsInternal(m, n - 1, cache) : 0;
            int top = m > 0 ? uniquePathsInternal(m - 1, n, cache) : 0;
            cache[m][n] = left + top;
        }
        return cache[m][n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.uniquePaths(3, 2)); // 3
        System.out.println(solution.uniquePaths(7, 3)); // 28
        System.out.println(solution.uniquePaths(3, 7)); //
    }
}
