package leetcode.q9;

/**
 * �ж�һ�������Ƿ��ǻ���������������ָ���򣨴������ң��͵��򣨴������󣩶�����һ����������

 ʾ�� 1:

 ����: 121
 ���: true
 ʾ��?2:

 ����: -121
 ���: false
 ����: �������Ҷ�, Ϊ -121 �� ���������, Ϊ 121- �����������һ����������
 ʾ�� 3:

 ����: 10
 ���: false
 ����: ���������, Ϊ 01 �����������һ����������
 ����:

 ���ܲ�������תΪ�ַ�����������������

 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/palindrome-number
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 *
 *
 * Created by Jason on 2019/10/12/0012.
 */
public class Solution {

    /**
     *
     * ��֪������������
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
