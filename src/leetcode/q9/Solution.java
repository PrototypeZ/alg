package leetcode.q9;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

 示例 1:

 输入: 121
 输出: true
 示例?2:

 输入: -121
 输出: false
 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 示例 3:

 输入: 10
 输出: false
 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 进阶:

 你能不将整数转为字符串来解决这个问题吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/palindrome-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * Created by Jason on 2019/10/12/0012.
 */
public class Solution {

    /**
     *
     * 已知两个基本操作
     * //pop operation:
     * pop = x % 10;
     * x /= 10;
     * <p>
     * //push operation:
     * temp = rev * 10 + pop;
     * rev = temp;
     *
     * Integer.MAX_VALUE: 2147483647
     *
     *
     *
     */

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int copyX = x;
        int result = 0;
        while (copyX > 0) {
            int popped = copyX % 10;
            copyX /= 10;
            result = result * 10 + popped;
        }
        return x == result;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(121));
        System.out.println(solution.isPalindrome(-121));
        System.out.println(solution.isPalindrome(10));
    }
}
