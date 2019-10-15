package leetcode.q13;

/**
 *
 * �������ְ������������ַ�:?I��?V��?X��?L��C��D?��?M��

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
 ����һ���������֣�����ת��������������ȷ���� 1?�� 3999 �ķ�Χ�ڡ�

 ʾ��?1:

 ����:?"III"
 ���: 3
 ʾ��?2:

 ����:?"IV"
 ���: 4
 ʾ��?3:

 ����:?"IX"
 ���: 9
 ʾ��?4:

 ����:?"LVIII"
 ���: 58
 ����: L = 50, V= 5, III = 3.
 ʾ��?5:

 ����:?"MCMXCIV"
 ���: 1994
 ����: M = 1000, CM = 900, XC = 90, IV = 4.


 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/roman-to-integer
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 * Created by Jason on 2019/10/15/0015.
 */
public class Solution {

    public int romanToInt(String s) {
        Extracted result = new Extracted(s, 0);
        int intValue = 0;
        result = extractValueAndPop(result.popped, "M", 1000);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "CM", 900);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "D", 500);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "CD", 400);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "C", 100);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "XC", 90);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "L", 50);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "XL", 40);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "X", 10);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "IX", 9);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "V", 5);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "IV", 4);
        intValue += result.value;
        result = extractValueAndPop(result.popped, "I", 1);
        intValue += result.value;

        return intValue;
    }

    public Extracted extractValueAndPop(String value, String unit, int base) {
        String extracted = value;
        int extractedValue = 0;
        while (extracted.startsWith(unit)) {
            extracted = extracted.substring(unit.length());
            extractedValue += base;
        }

        return new Extracted(extracted, extractedValue);
    }

    class Extracted {
        String popped;
        int value;

        public Extracted(String popped, int value) {
            this.popped = popped;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.romanToInt("III"));
        System.out.println(solution.romanToInt("IV"));
        System.out.println(solution.romanToInt("IX"));
        System.out.println(solution.romanToInt("LVIII"));
        System.out.println(solution.romanToInt("MCMXCIV"));
    }
}
