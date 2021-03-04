package codingInterviews.q17;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 * 通过次数88,365提交次数112,894
 * 在真实的面试中遇到过这道题？
 * 输入数字 n, 按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则依次打印 1, 2, 3, ..., 直到 999.
 *
 * https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 * Created by Jason on 2020/10/18/0018.
 */
public class Solution {

    /**
     * 最大的 n 位数（记为 end ）和位数 n 的关系：
     * 例如最大的 1 位数是 9 ，
     * 最大的 2 位数是 99 ，
     * 最大的 3 位数是 999 。则可推出公式：
     *
     *
     * n <= 9 的时候没问题，Integer.MAX_VALUE 大概21亿多，10位数
     *
     * 这种解法不考虑大数
     *
     * Math.pow(10, n) - 1
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        int end = (int)Math.pow(10, n) - 1;
        int[] res = new int[end];
        for(int i = 0; i < end; i++)
            res[i] = i + 1;
        return res;
    }

    /**
     * 考虑大数的版本，数字不再用 Integer 存储，改用 char[]
     * @param n
     */
    public void printNumbers2(int n) {
        char[] number = new char[n];

        printNumbersInternal(n, 0, number);
    }

    /**
     * 考虑大数，其实问题的本质是全排列
     *
     * 给定一个 char[] 代表的数字，给你指定一个最高位，这个 char[] 代表的数，比最高位高的所有位数都已经确定好了，
     * 你把从给定最高位到最低位全排列，枚举出所有数字，打印出来
     * @param n 一共几位
     * @param currentPosition 目前到第几位了，按高位开始算
     * @param number 代表的数，数组每个元素代表数字某一位的数
     */
    private void printNumbersInternal(int n, int currentPosition, char[] number) {
        if (currentPosition == n) {
            printNumberString(number);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            number[currentPosition] = (char) ('0' + i);
            printNumbersInternal(n, currentPosition + 1, number);
        }
    }

    private void printNumberString(char[] number) {
        int offset = 0;
        while (number[offset] == '0' && offset < number.length - 1) offset++;
        System.out.println(new String(number, offset, number.length - offset));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.printNumbers2(10);
    }
}
