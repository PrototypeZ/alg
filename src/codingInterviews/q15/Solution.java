package codingInterviews.q15;

/**
 * ��ʵ��һ������������һ��������������������Ʊ�ʾ�� 1 �ĸ�����
 * ���磬�� 9 ��ʾ�ɶ������� 1001�� �� 2 λ�� 1����ˣ��������9�� ��ú������2
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
