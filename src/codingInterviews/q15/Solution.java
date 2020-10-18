package codingInterviews.q15;

/**
 * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。
 * 例如，把 9 表示成二进制是 1001， 有 2 位是 1。因此，如果输入9， 则该函数输出2
 * Created by Jason on 2020/10/18/0018.
 */
public class Solution {

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
