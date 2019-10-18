package leetcode.q29;

/**
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 0) return 0;
        boolean negative = false;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            negative = true;
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor > 0) {
                return divide(dividend + divisor, divisor) - 1;
            } else {
                int a = divide(dividend - divisor, divisor);
                if (a == Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else {
                    return a + 1;
                }
            }
        } else if (divisor == Integer.MIN_VALUE) {
            return 0;
        } else {
            return (negative ? -1 : 1) * divideInternal(Math.abs(dividend), Math.abs(divisor));
        }

    }

    /**
     * 除法本质就是说被除数是由多少个除数构成的，这个数目就是商。
     * 所以商的本质就是除数的个数，我们的思路时尝试这个个数，
     * 2 个, 4个, 8 个, 16个 ... 这样去试，
     * 加入试到 16 个 时，比被除数要大了， 就在 8 ~ 16 中间去继续按这种方法去找
     */
    private int divideInternal(int dividend, int divisor) {

        if (dividend < divisor) return 0;
        if (dividend == divisor) return 1;

        int testMinusNum = divisor;
        int testQuotient = 1;

        boolean isAboutToLeak = false;
        while (dividend - testMinusNum > 0) {
            if (testMinusNum < Integer.MAX_VALUE / 2 && testQuotient < Integer.MAX_VALUE / 2) {
                testMinusNum += testMinusNum;
                testQuotient += testQuotient;
            } else {
                isAboutToLeak = true;
                break;
            }
        }

        if (isAboutToLeak) {
            return testQuotient + divideInternal(dividend - testMinusNum, divisor);
        } else if (dividend  == testMinusNum) {
            return testQuotient;
        } else {
            return testQuotient / 2 + divideInternal(dividend - testMinusNum / 2, divisor);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

//        System.out.println(solution.divide(10, 3));
//        System.out.println(solution.divide(7, -3));
//        System.out.println(solution.divide(0, -3));
//        System.out.println(solution.divide(9, -3));
        System.out.println(solution.divide(Integer.MIN_VALUE, 1));
        System.out.println(solution.divide(Integer.MIN_VALUE, -1));
        System.out.println(solution.divide(Integer.MIN_VALUE, 2));
    }
}
