package codingInterviews.q17;

/**
 * �������� n, ��˳���ӡ���� 1 ������ n λʮ���������������� 3�������δ�ӡ 1, 2, 3, ..., ֱ�� 999.
 * Created by Jason on 2020/10/18/0018.
 */
public class Solution {


    public void printNumbers(int n) {
        char[] number = new char[n];

        printNumbersInternal(n, 0, number);
    }

    /**
     *
     * @param n һ����λ
     * @param currentPosition Ŀǰ���ڼ�λ�ˣ�����λ��ʼ��
     * @param number �������������ÿ��Ԫ�ش�������ĳһλ����
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
