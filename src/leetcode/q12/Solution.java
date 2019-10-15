package leetcode.q12;

/**
 *
 * �������ְ������������ַ���?I��?V��?X��?L��C��D?��?M��

 �ַ�          ��ֵ
 I             1
 V             5
 X             10
 L             50
 C             100
 D             500
 M             1000
 ���磬 �������� 2 д��?II?����Ϊ�������е� 1��12 д��?XII?����Ϊ?X?+?II?�� 27 д��??XXVII, ��Ϊ?XX?+?V?+?II?��

 ͨ������£�����������С�������ڴ�����ֵ��ұߡ���Ҳ�������������� 4 ��д��?IIII������?IV������ 1 ������ 5 ����ߣ�����ʾ�������ڴ��� 5 ��С�� 1 �õ�����ֵ 4 ��ͬ���أ����� 9 ��ʾΪ?IX���������Ĺ���ֻ�������������������

 I?���Է���?V?(5) ��?X?(10) ����ߣ�����ʾ 4 �� 9��
 X?���Է���?L?(50) ��?C?(100) ����ߣ�����ʾ 40 ��?90��?
 C?���Է���?D?(500) ��?M?(1000) ����ߣ�����ʾ?400 ��?900��
 ����һ������������תΪ�������֡�����ȷ���� 1?�� 3999 �ķ�Χ�ڡ�

 ʾ��?1:

 ����:?3
 ���: "III"
 ʾ��?2:

 ����:?4
 ���: "IV"
 ʾ��?3:

 ����:?9
 ���: "IX"
 ʾ��?4:

 ����:?58
 ���: "LVIII"
 ����: L = 50, V = 5, III = 3.
 ʾ��?5:

 ����:?1994
 ���: "MCMXCIV"
 ����: M = 1000, CM = 900, XC = 90, IV = 4.


 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/integer-to-roman
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 * Created by Jason on 2019/10/15/0015.
 */
public class Solution {

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int value = num;

        value = testAppend(value, 1000, "M", sb);
        value = testAppend(value, 900, "CM", sb);
        value = testAppend(value, 500, "D", sb);
        value = testAppend(value, 400, "CD", sb);
        value = testAppend(value, 100, "C", sb);
        value = testAppend(value, 90, "XC", sb);
        value = testAppend(value, 50, "L", sb);
        value = testAppend(value, 40, "XL", sb);
        value = testAppend(value, 10, "X", sb);
        value = testAppend(value, 9, "IX", sb);
        value = testAppend(value, 5, "V", sb);
        value = testAppend(value, 4, "IV", sb);
        testAppend(value, 1, "I", sb);

        return sb.toString();
    }

    private int testAppend(int value, int base, String unit, StringBuilder sb) {
        int num = value / base;
        if (num > 0) {
            appendWithTimes(sb, unit,num);
        }
        return value - num * base;
    }

    private void appendWithTimes(StringBuilder sb, String string, int times) {
        for (int i = 0; i < times; i++) {
            sb.append(string);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.intToRoman(3));
        System.out.println(solution.intToRoman(4));
        System.out.println(solution.intToRoman(9));
        System.out.println(solution.intToRoman(58));
        System.out.println(solution.intToRoman(1994));

    }
}
