package codingInterviews.q15;

/**
 *
 * 剑指 Offer 15. 二进制中1的个数
 * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 *
 *
 *
 * 示例 1：
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 *
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 *
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *
 *
 * 提示：
 *
 * 输入必须是长度为 32 的 二进制串 。
 *
 *
 * 注意：本题与主站 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/
 *
 * 通过次数99,553提交次数135,965
 * 在真实的面试中遇到过这道题？
 * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。
 * 例如，把 9 表示成二进制是 1001， 有 2 位是 1。因此，如果输入9， 则该函数输出2
 *
 * https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 *
 *
 * 关键词：位运算
 * Created by Jason on 2020/10/18/0018.
 */
public class Solution {

    /**
     * 复习原码，反码，补码
     * 1. 最高位 0，为正， 1， 为负。无论原码反码，补码
     * 2. 补码，符号位不变，剩余位都求反，并加一
     * 4. 假设 8 位补码，正数最大 01111111 -> 127, 负数最大 10000000 -> -128
     * 5. 0 的补码 00000000, 1 的补码 0000001, -1 的补码 11111111
     * @param data
     * @return
     */
    public int calculate(int data) {
        int continueFlag = 1;
        int count = 0;
        while (continueFlag != 0) {
            if ((continueFlag & data) == continueFlag) {
                count ++;
            }
            continueFlag = continueFlag << 1;
        }
        return count;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calculate(9)); // 2
    }

}
