package leetcode.q56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {


    /**
     * 首先，我们要先得到一个性质, 如果按这些区间的左端点，把这些区间从小到大排序以后，
     * 如果要是存在两个区间时可以合并的，那么这两个区间必然时排序后相邻的两个区间。
     * 证明方法为反证法，假设存在两个能合并，又步相邻的区间，最后发现和我们排完序后的的状态时矛盾的，即可证明上面的性质
     *
     * 知道这个性质以后， 我们就可以找区间了
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        int[][] copy = Arrays.stream(intervals)
                .map(ints -> Arrays.copyOf(ints, ints.length)).toArray(int[][]::new);

        Arrays.sort(copy, Comparator.comparingInt(ints -> ints[0]));

        for (int i = 0; i < copy.length; i++) {
            if (result.size() == 0) {
                result.add(copy[i]);
            } else {
                int[] last = result.get(result.size() - 1);
                int[] current = copy[i];
                if (current[0] <= last[1] && current[1] > last[1]) {
                    last[1] = current[1];
                } else if (current[0] > last[1]) {
                    result.add(current);
                }
            }
        }
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.merge(new int[][]{
                new int[]{1,2}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}
        }))); // [[1,6],[8,10],[15,18]]

        System.out.println(Arrays.deepToString(solution.merge(new int[][]{
                new int[]{1,4}, new int[]{4, 5}
        }))); // [[1,5]]

        System.out.println(Arrays.deepToString(solution.merge(new int[][]{
                new int[]{1,4}, new int[]{1, 4}
        }))); // [[1,4]]

        System.out.println(Arrays.deepToString(solution.merge(new int[][]{
                new int[]{1,4}, new int[]{2, 3}
        }))); // [[1,4]]
    }
}
