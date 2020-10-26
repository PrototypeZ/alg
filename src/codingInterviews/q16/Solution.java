package codingInterviews.q16;

/**
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {


    public double myPow(double x, int n) {

        if (x == 1) {
            return x;
        } else if (x == -1) {
            return n % 2 == 0 ? 1 : -1;
        } else if (x == 0) {
            if (n < 0) {
                throw new IllegalArgumentException();
            } else {
                // 0 的任何次方无意义，认为 0 吧
                return 0;
            }
        }

        // x != 0 x != 1 x != -1
        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                return 1 / myPow(x, Integer.MAX_VALUE) * (1d / x);
            } else {
                return 1 / myPow(x, -n);
            }
        } else if (n == 0) {
            return 1;
        } else {
            // n > 0
            if (n == 1) {
                return x;
            }
            double v = myPow(x, n / 2);

            if (n % 2 == 1) {
                return v * v * x;
            } else {
                return v * v;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.myPow(2.00000, 10)); // 1024.00000
        System.out.println(solution.myPow(2.10000, 3)); // 9.26100
        System.out.println(solution.myPow(2.00000, -2)); // 0.25000
        System.out.println(solution.myPow(1.00000, -2147483648)); // 1.0000
        System.out.println(solution.myPow(2.00000, -2147483648)); // 0.0 因为太小了
    }
}
