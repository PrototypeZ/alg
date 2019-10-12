package leetcode.q8;

/**
 * ������ʵ��һ��?atoi?������ʹ���ܽ��ַ���ת����������

 ���ȣ��ú����������Ҫ�������õĿ�ͷ�ո��ַ���ֱ��Ѱ�ҵ���һ���ǿո���ַ�Ϊֹ��

 ������Ѱ�ҵ��ĵ�һ���ǿ��ַ�Ϊ�����߸���ʱ���򽫸÷�����֮���澡���ܶ���������������������Ϊ�������������ţ������һ���ǿ��ַ������֣���ֱ�ӽ�����֮�������������ַ�����������γ�������

 ���ַ���������Ч����������֮��Ҳ���ܻ���ڶ�����ַ�����Щ�ַ����Ա����ԣ����Ƕ��ں�����Ӧ�����Ӱ�졣

 ע�⣺������ַ����еĵ�һ���ǿո��ַ�����һ����Ч�����ַ����ַ���Ϊ�ջ��ַ����������հ��ַ�ʱ������ĺ�������Ҫ����ת����

 ���κ�����£����������ܽ�����Ч��ת��ʱ���뷵�� 0��

 ˵����

 �������ǵĻ���ֻ�ܴ洢 32 λ��С���з�����������ô����ֵ��ΧΪ?[?231,? 231?? 1]�������ֵ���������Χ���뷵�� ?INT_MAX (231?? 1) ��?INT_MIN (?231) ��

 ʾ��?1:

 ����: "42"
 ���: 42
 ʾ��?2:

 ����: "   -42"
 ���: -42
 ����: ��һ���ǿհ��ַ�Ϊ '-', ����һ�����š�
 ?    ���Ǿ����ܽ���������������������ֵ�����������������õ� -42 ��
 ʾ��?3:

 ����: "4193 with words"
 ���: 4193
 ����: ת����ֹ������ '3' ����Ϊ������һ���ַ���Ϊ���֡�
 ʾ��?4:

 ����: "words and 987"
 ���: 0
 ����: ��һ���ǿ��ַ��� 'w', �����������ֻ��������š�
 ����޷�ִ����Ч��ת����
 ʾ��?5:

 ����: "-91283472332"
 ���: -2147483648
 ����: ���� "-91283472332" ���� 32 λ�з���������Χ��
 ?    ��˷��� INT_MIN (?231) ��


 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/string-to-integer-atoi
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 * Created by Jason on 2019/10/11/0011.
 */
public class Solution {

    public int myAtoi(String str) {
        char[] source = str.toCharArray();
        // 0: ��ʼ״̬�� 1: ȷ������(���� 0��+��-), 2: ������һ���� 0 ����
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
