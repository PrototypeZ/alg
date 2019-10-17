package leetcode.tencent.autumn2018.q6;

/**
 *
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
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