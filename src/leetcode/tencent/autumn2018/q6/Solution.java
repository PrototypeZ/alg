package leetcode.tencent.autumn2018.q6;

/**
 *
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * ��һ�������ַ������ݸ������������Դ������¡������ҽ��� Z �������С�
 * <p>
 * ���������ַ���Ϊ "LEETCODEISHIRING" ����Ϊ 3 ʱ���������£�
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * ֮����������Ҫ�����������ж�ȡ��������һ���µ��ַ��������磺"LCIRETOESIIGEDHN"��
 * <p>
 * ����ʵ��������ַ�������ָ�������任�ĺ�����
 * <p>
 * string convert(string s, int numRows);
 * ʾ�� 1:
 * <p>
 * ����: s = "LEETCODEISHIRING", numRows = 3
 * ���: "LCIRETOESIIGEDHN"
 * ʾ�� 2:
 * <p>
 * ����: s = "LEETCODEISHIRING", numRows = 4
 * ���: "LDREOEIIECIHNTSG"
 * ����:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * <p>
 * Created by Jason on 2019/2/7/0007.
 */
class Solution {

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 0) {
            return "";
        }
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] result = new StringBuilder[numRows];
        char[] input = s.toCharArray();
        for (int i = 0; i < input.length; i++) {
            int position = i % (numRows + numRows - 2);
            if (0 <= position && position <= numRows - 1) {
                if (result[position] == null) {
                    result[position] = new StringBuilder();
                }
                result[position].append(input[i]);
            } else {
                int idx = numRows - 1 - ((position + 1) % numRows);
                result[idx].append(input[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (StringBuilder aResult : result) {
            sb.append(aResult == null ? "" : aResult);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.convert("LEETCODEISHIRING", 3));
        System.out.println(solution.convert("LEETCODEISHIRING", 4));
        System.out.println(solution.convert("A", 2));
    }
}