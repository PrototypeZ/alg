package codingInterviews.q17;

/**
 * 输入数字 n, 按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则依次打印 1, 2, 3, ..., 直到 999.
 * Created by Jason on 2020/10/18/0018.
 */
public class Solution {


    public void printNumbers(int n) {
        char[] number = new char[n];

        printNumbersInternal(n, 0, number);
    }

    /**
     *
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
        solution.printNumbers(3);
    }
}
