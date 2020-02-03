package leetcode.q201;

/**
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。

 示例 1: 

 输入: [5,7]
 输出: 4
 示例 2:

 输入: [0,1]
 输出: 0


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2020/2/3/0003.
 */
public class Solution {

    /**
     * 例子一
     * m:2   010
     *  :3   011
     *  :4   100
     *  :5   101
     *  :6   110
     * n:7   111
     * 向右移动一位
     * m:1   001
     *  :2   010
     * n:3   011
     * 向右移动一位
     * m:0   000
     * n:1   001
     * 向右移动一位
     * m:0   000
     * n:0   000
     * 最后结果为：0
     *
     * 例子二
     * m:4   100
     *  :5   101
     *  :6   110
     * n:7   111
     * 向右移动一位
     * m:1   010
     * n:3   011
     * 向右移动一位
     * m:0   001
     * n:1   001
     * 最后结果为：100
     *
     * 在移位的过程中，移位的结果的最后一位，只要 m < n, 最后一位的按位与结果都为 0
     *
     */
    public int rangeBitwiseAnd(int m, int n) {
        int zeros = 0;
        while (n > m) {
            zeros++;
            m >>= 1;
            n >>= 1;
        }
        //将 0 的个数空出来
        return m << zeros;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rangeBitwiseAnd(5, 7)); // 4
        System.out.println(solution.rangeBitwiseAnd(0, 1)); // 0
    }
}
