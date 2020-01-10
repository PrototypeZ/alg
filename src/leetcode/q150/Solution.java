package leetcode.q150;

import java.util.Arrays;

/**
 * 根据逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 *
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 *
 *
 *
 */
class Solution {


    /**
     * 这种方法比较直接，但是效率不太好，其实用栈的结构来解此题更好
     */
    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) {
            return Integer.valueOf(tokens[0]);
        }
        String[] newArray = null;
        for (int i = 0; i < tokens.length; i++) {
            Integer value = null;
            switch (tokens[i]) {
                case "+":
                    value = Integer.valueOf(tokens[i - 2]) + Integer.valueOf(tokens [i - 1]);
                    break;
                case "-":
                    value = Integer.valueOf(tokens[i - 2]) - Integer.valueOf(tokens [i - 1]);
                    break;
                case "*":
                    value = Integer.valueOf(tokens[i - 2]) * Integer.valueOf(tokens [i - 1]);
                    break;
                case "/":
                    value = Integer.valueOf(tokens[i - 2]) / Integer.valueOf(tokens [i - 1]);
                    break;
            }
            if (value != null) {
                if (i == tokens.length - 1) {
                    return value;
                }
                String[] front = Arrays.copyOfRange(tokens, 0, i - 2);
                String[] behind = Arrays.copyOfRange(tokens, i, tokens.length);
                behind[0] = String.valueOf(value);
                newArray = new String[front.length + behind.length];
                System.arraycopy(front, 0, newArray, 0, front.length);
                System.arraycopy(behind, 0, newArray, front.length, behind.length);
                break;
            }
        }
        return evalRPN(newArray);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(solution.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        System.out.println(solution.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));

        // error cases
        System.out.println(solution.evalRPN(new String[]{"18"}));
    }
}