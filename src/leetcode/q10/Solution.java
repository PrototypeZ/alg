package leetcode.q10;

/**
 *
 * 给你一个字符串?s?和一个字符规律?p，请你来实现一个支持 '.'?和?'*'?的正则表达式匹配。

 '.' 匹配任意单个字符
 '*' 匹配零个或多个前面的那一个元素
 所谓匹配，是要涵盖?整个?字符串?s的，而不是部分字符串。

 说明:

 s?可能为空，且只包含从?a-z?的小写字母。
 p?可能为空，且只包含从?a-z?的小写字母，以及字符?.?和?*。
 示例 1:

 输入:
 s = "aa"
 p = "a"
 输出: false
 解释: "a" 无法匹配 "aa" 整个字符串。
 示例 2:

 输入:
 s = "aa"
 p = "a*"
 输出: true
 解释:?因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 示例?3:

 输入:
 s = "ab"
 p = ".*"
 输出: true
 解释:?".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 示例 4:

 输入:
 s = "aab"
 p = "c*a*b"
 输出: true
 解释:?因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 示例 5:

 输入:
 s = "mississippi"
 p = "mis*is*p*."
 输出: false


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/regular-expression-matching
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2019/10/12/0012.
 */
public class Solution {

    // region start: dp topDown
    public boolean isMatchDpTopDown(String s, String p) {
        // memo[0][0] 是我们要求的目标值
        // memo[s.length()][p.length()] 定义为 true， 此时带比较的 text 和 pattern 都为空
        // memo[s.length()][{value < p.length}] 不确定，可能为 true，也可能为 false
        // memo[{value < s.length()}][p.length] 显然为 false
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return isMatchDpTopDownInternal(memo, 0, 0, s, p);
    }

    private boolean isMatchDpTopDownInternal(Boolean[][] memo, int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        boolean result;
        /*
        这样判断不是不对的，反例：  text -> '', pattern -> '.*'
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
        // s 和 p 从第一个字符开始就 match。
        // 存在两种可能：
        // 1. p 的第一个字符是 '.'， 那肯定满足条件
        // 2. p 的第一个字符真的和 s 的第一个字符一样
        boolean first_match = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*'){
            /**
             * 走到这里，表示 p 大于等于两个字符，且 p 第二个字符是 '*'
             * 类似的 p 例如 'a*'， 说明 '*'前的部分可以在 s 开头出现 0 ~ 任意次。
             *
             * 出现 0 次的情况下，对应的判断 s 和 p 是否相等的表达式为 isMatch(s, p.substring(2)
             * 出现多次的情况下， 如果 p 和 s match， 那么：
             * 1. first_match 避为 true
             * 2. s 即使截掉开头第一个字符，剩下的部分应该还是和 p match
             *
             */
            return (isMatchRecall(s, p.substring(2)) ||
                    (first_match && isMatchRecall(s.substring(1), p)));
        } else {
            /**
             * 走到这里，表示 p 小于两个字符（p 肯定只有一个字符），
             * 或者 p 大于等于两个字符，但是 p 第二个字符不是 '*'
             *
             * 注意： p length 1 的时候， p.substring(1) 为 ""
             * 所以 return 表达式相当于 first_match && isMatch(s.substring(1), "")
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
