package leetcode.q10;

/**
 *
 * ����һ���ַ���?s?��һ���ַ�����?p��������ʵ��һ��֧�� '.'?��?'*'?��������ʽƥ�䡣

 '.' ƥ�����ⵥ���ַ�
 '*' ƥ���������ǰ�����һ��Ԫ��
 ��νƥ�䣬��Ҫ����?����?�ַ���?s�ģ������ǲ����ַ�����

 ˵��:

 s?����Ϊ�գ���ֻ������?a-z?��Сд��ĸ��
 p?����Ϊ�գ���ֻ������?a-z?��Сд��ĸ���Լ��ַ�?.?��?*��
 ʾ�� 1:

 ����:
 s = "aa"
 p = "a"
 ���: false
 ����: "a" �޷�ƥ�� "aa" �����ַ�����
 ʾ�� 2:

 ����:
 s = "aa"
 p = "a*"
 ���: true
 ����:?��Ϊ '*' �������ƥ���������ǰ�����һ��Ԫ��, ������ǰ���Ԫ�ؾ��� 'a'����ˣ��ַ��� "aa" �ɱ���Ϊ 'a' �ظ���һ�Ρ�
 ʾ��?3:

 ����:
 s = "ab"
 p = ".*"
 ���: true
 ����:?".*" ��ʾ��ƥ�����������'*'�������ַ���'.'����
 ʾ�� 4:

 ����:
 s = "aab"
 p = "c*a*b"
 ���: true
 ����:?��Ϊ '*' ��ʾ������������� 'c' Ϊ 0 ��, 'a' ���ظ�һ�Ρ���˿���ƥ���ַ��� "aab"��
 ʾ�� 5:

 ����:
 s = "mississippi"
 p = "mis*is*p*."
 ���: false


 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/regular-expression-matching
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 * Created by Jason on 2019/10/12/0012.
 */
public class Solution {

    // region start: dp topDown
    public boolean isMatchDpTopDown(String s, String p) {
        // memo[0][0] ������Ҫ���Ŀ��ֵ
        // memo[s.length()][p.length()] ����Ϊ true�� ��ʱ���Ƚϵ� text �� pattern ��Ϊ��
        // memo[s.length()][{value < p.length}] ��ȷ��������Ϊ true��Ҳ����Ϊ false
        // memo[{value < s.length()}][p.length] ��ȻΪ false
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return isMatchDpTopDownInternal(memo, 0, 0, s, p);
    }

    private boolean isMatchDpTopDownInternal(Boolean[][] memo, int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        boolean result;
        /*
        �����жϲ��ǲ��Եģ�������  text -> '', pattern -> '.*'
        <code>
        if (i == text.length()) {
            return j == pattern.length();
        }
        </code>
        */
        if (j == pattern.length()) {
            return i == text.length();
        }

        boolean firstMatch = (text.length() - i > 0) &&
                (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.');

        if (pattern.length() - j >= 2 && pattern.charAt(j+1) == '*') {
            result = isMatchDpTopDownInternal(memo, i, j + 2, text, pattern)
                    || (firstMatch && isMatchDpTopDownInternal(memo, i+1, j, text, pattern));
        } else {
            result = firstMatch && isMatchDpTopDownInternal(memo, i+1, j+1, text, pattern);
        }

        memo[i][j] = result;
        return result;
    }

    // endregion

    // region match recall
    public boolean isMatchRecall(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        // s �� p �ӵ�һ���ַ���ʼ�� match��
        // �������ֿ��ܣ�
        // 1. p �ĵ�һ���ַ��� '.'�� �ǿ϶���������
        // 2. p �ĵ�һ���ַ���ĺ� s �ĵ�һ���ַ�һ��
        boolean first_match = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*'){
            /**
             * �ߵ������ʾ p ���ڵ��������ַ����� p �ڶ����ַ��� '*'
             * ���Ƶ� p ���� 'a*'�� ˵�� '*'ǰ�Ĳ��ֿ����� s ��ͷ���� 0 ~ ����Ρ�
             *
             * ���� 0 �ε�����£���Ӧ���ж� s �� p �Ƿ���ȵı��ʽΪ isMatch(s, p.substring(2)
             * ���ֶ�ε�����£� ��� p �� s match�� ��ô��
             * 1. first_match ��Ϊ true
             * 2. s ��ʹ�ص���ͷ��һ���ַ���ʣ�µĲ���Ӧ�û��Ǻ� p match
             *
             */
            return (isMatchRecall(s, p.substring(2)) ||
                    (first_match && isMatchRecall(s.substring(1), p)));
        } else {
            /**
             * �ߵ������ʾ p С�������ַ���p �϶�ֻ��һ���ַ�����
             * ���� p ���ڵ��������ַ������� p �ڶ����ַ����� '*'
             *
             * ע�⣺ p length 1 ��ʱ�� p.substring(1) Ϊ ""
             * ���� return ���ʽ�൱�� first_match && isMatch(s.substring(1), "")
             */
            return first_match && isMatchRecall(s.substring(1), p.substring(1));
        }
    }
    // endregion

    // region dp bottom up
    public boolean isMatchDpBottomUp(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;

        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean firstMatch = (s.length() - i >= 1) &&
                        (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

                if (p.length() - j >= 2 && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j+2] || (firstMatch && dp[i+1][j]);
                } else {
                    dp[i][j] = firstMatch && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    // endregion

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isMatchDpBottomUp("aa", "a")); // false
        System.out.println(solution.isMatchDpBottomUp("aa", "a*")); // true
        System.out.println(solution.isMatchDpBottomUp("ab", ".*")); // true
        System.out.println(solution.isMatchDpBottomUp("aab", "c*a*b*"));  // true
        System.out.println(solution.isMatchDpBottomUp("mississippi", "mis*is*p*."));  // false
        System.out.println();

        System.out.println(solution.isMatchDpTopDown("aa", "a")); // false
        System.out.println(solution.isMatchDpTopDown("aa", "a*")); // true
        System.out.println(solution.isMatchDpTopDown("ab", ".*")); // true
        System.out.println(solution.isMatchDpTopDown("aab", "c*a*b*"));  // true
        System.out.println(solution.isMatchDpTopDown("mississippi", "mis*is*p*."));  // false
        System.out.println();

        System.out.println(solution.isMatchRecall("aa", "a")); // false
        System.out.println(solution.isMatchRecall("aa", "a*")); // true
        System.out.println(solution.isMatchRecall("ab", ".*")); // true
        System.out.println(solution.isMatchRecall("aab", "c*a*b*"));  // true
        System.out.println(solution.isMatchRecall("mississippi", "mis*is*p*."));  // false
        System.out.println();
    }
}
