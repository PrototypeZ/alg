package leetcode.q75;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * 我们规定，p0 以前的所有元素都是 0，p2 以后的所有元素都是 2
     * p0 一开始位于 0 , p2 一开始位于 nums.length - 1;
     * current 初始化为 0,
     * 如果 current 指向元素为 0 ,就和 p0 对应元素交换 p0 ++, current ++
     * 如果 current 指向元素为 2 ,就和 p2 对应元素交换 p2 --
     * 如果 current 指向元素为 1 , current ++
     *
     * 按照上面两条规则，我们可以知道一些性质:
     * 1. current >= p0 , 从上面容易推出
     * 2. 如果 current > p0, 那么 [p0, current) 这个半开半闭区间里的元素必然全为 1
     * 3. 由 2 可知，如果 current > p0， 必然 nums[p0] == 1
     * 4. 循环结束的时候 current == p2 + 1, 鸡 current == p2 的时候，是最后一次判断
     * 5. 由 2 克制，如果 current == p0 ，那么 nums[p0] == nums[current],
     * 它们指向的值不确定，都有可能
     *
     * 由上面的分析可知，current 的几何意义就是，[p0, current)这个区间内元素都是 1
     */
    public void sortColors(int[] nums) {
        int p0 = 0;
        int p2 = nums.length - 1;
        int current = 0;

        while (current <= p2) {
            if (nums[current] == 0) {
                int tmp = nums[p0];
                nums[p0] = nums[current];
                nums[current] = tmp;
                current++;
                p0++;
            } else if (nums[current] == 2) {
                int tmp = nums[p2];
                nums[p2] = nums[current];
                nums[current] = tmp;
                p2--;
            } else {
                current++;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] test = new int[]{2, 0, 2, 1, 1, 0};
        solution.sortColors(test);
        System.out.println(Arrays.toString(test));
    }
}
