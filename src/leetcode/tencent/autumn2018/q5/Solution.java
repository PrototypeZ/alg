package leetcode.tencent.autumn2018.q5;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class Solution {

    // 最长公共子串变种
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        } else if (s.length() == 1) {
            return s;
        }

        char[] charArray = s.toCharArray();
        char[] reversedCharArray = new StringBuilder(s).reverse().toString().toCharArray();

        int[] lastRow = new int[charArray.length];

        int longestPalindromeIndex = 0;
        int longestPalindromeLength = 1;

        for (int i = 0; i < charArray.length; i++) {
            int [] currentRow = new int[charArray.length];
            for (int j = 0; j < reversedCharArray.length; j++) {
                if (charArray[i] == reversedCharArray[j]) {
                    if (i == 0 || j == 0) {
                        currentRow[j] = 1;
                    } else {
                        currentRow[j] = lastRow[j-1] + 1;
                        if (i + j - (currentRow[j]) + 2 == charArray.length) {
                            // 这确实是一个回文串
                            if (currentRow[j] > longestPalindromeLength) {
                                longestPalindromeLength = currentRow[j];
                                longestPalindromeIndex = i - currentRow[j] + 1;
                            }
                        }
                    }
                }
            }
            lastRow = currentRow;
        }

        return s.substring(longestPalindromeIndex, longestPalindromeIndex + longestPalindromeLength);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String result = solution.longestPalindrome("caba");
        System.out.println("result = [" + result + "]");

        result = solution.longestPalindrome("abacdfgdcaba");
        System.out.println("result = [" + result + "]");
    }
}
