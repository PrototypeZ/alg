package leetcode.q8;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。

 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

 在任何情况下，若函数不能进行有效的转换时，请返回 0。

 说明：

 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。
 如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。

 示例 1:

 输入: "42"
 输出: 42
 示例 2:

 输入: "   -42"
 输出: -42
 解释: 第一个非空白字符为 '-', 它是一个负号。
      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 示例 3:

 输入: "4193 with words"
 输出: 4193
 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 示例 4:

 输入: "words and 987"
 输出: 0
 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 因此无法执行有效的转换。
 示例 5:

 输入: "-91283472332"
 输出: -2147483648
 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
      因此返回 INT_MIN (−231) 。


 */
public class Solution {

    public int myAtoi(String str) {
        char[] source = str.toCharArray();
        // 0: 初始状态， 1: 确定符号(遇到 0，+，-), 2: 遇到第一个非 0 数字
        // 0 -> 1 -> 2
        int state = 0;
        boolean negative = false;
        StringBuilder sb = new StringBuilder();
        for (char c : source) {
            if (c == '0') {
                if (state == 0) {
                    negative = false;
                    state = 1;
                } else if (state == 2){
                    sb.append(c);
                }
            } else if ('0' < c && c <= '9') {
                if (state == 0) {
                    negative = false;
                }
                state = 2;
                sb.append(c);
            } else if (' ' == c) {
                if (state != 0) {
                    break;
                }
            } else if (c == '+') {
                if (state == 0) {
                    negative = false;
                    state = 1;
                } else if (state == 1) {
                    return 0;
                } else {
                    break;
                }
            } else if (c == '-') {
                if (state == 0) {
                    state = 1;
                    negative = true;
                    sb.append(c);
                } else if (state == 1) {
                    return 0;
                } else {
                    break;
                }
            } else {
                if (state == 0) {
                    return 0;
                } else {
                    break;
                }
            }
        }
        if (sb.length() == 0) {
            return 0;
        }
        String maxString = String.valueOf(Integer.MAX_VALUE);
        String minString = String.valueOf(Integer.MIN_VALUE);
        String boundaryString;
        if (negative) {
            if (sb.length() == 1) {
                return 0;
            }
            boundaryString = minString;
        } else {
            boundaryString = maxString;
        }
        if (sb.length() > boundaryString.length()) {
            return (negative ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        } else if (sb.length() == boundaryString.length()) {
            if (sb.toString().compareTo(boundaryString) > 0) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            } else {
                return Integer.parseInt(sb.toString());
            }
        } else {
            return Integer.parseInt(sb.toString());
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.myAtoi("42"));
        System.out.println(solution.myAtoi("   -42"));
        System.out.println(solution.myAtoi("4193 with words"));
        System.out.println(solution.myAtoi("words and 987"));
        System.out.println(solution.myAtoi("-91283472332"));
        System.out.println(solution.myAtoi(""));
        System.out.println(solution.myAtoi("+1"));
        System.out.println(solution.myAtoi("0000000000012345678"));
        System.out.println(solution.myAtoi("+000000000000001"));
        System.out.println(solution.myAtoi("-000000000000001"));
        System.out.println(solution.myAtoi("+-2"));
        System.out.println(solution.myAtoi("+ab1"));
        System.out.println(solution.myAtoi("-ab1"));
        System.out.println(solution.myAtoi("   +0 123"));
        System.out.println(solution.myAtoi("-2147483647"));
    }
}
