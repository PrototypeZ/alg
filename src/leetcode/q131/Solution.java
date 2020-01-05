package leetcode.q131;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

 返回 s 所有可能的分割方案。

 示例:

 输入: "aab"
 输出:
 [
 ["aa","b"],
 ["a","a","b"]
 ]


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2019/11/5/0005.
 */
public class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s != null && s.length() > 0) {
            partitionInternal(new ArrayList<>(), s, result);
        }
        return result;
    }

    /**
     * 简单的动态规划
     * 1. 对于一个 s，我想知道它可以被分成的回文子串的种数，可以先尝试性割一刀，分为前后两部分
     * 2-1. 如果前面部分不是回文，那后半部分可以不用看了
     * 2-2. 如果前面部分是回文，那后面部分可以用 1 的方法继续判断
     *
     * @param progress: 当前尝试的分法已经分好的子串
     * @param result: 所有分法
     */
    private void partitionInternal(List<String> progress, String s, List<List<String>> result) {
        if (s.length() == 1) {
            List<String> record = new ArrayList<>(progress);
            record.add(s);
            result.add(record);
        } else if (s.length() == 0) {
            result.add(progress);
        } else {
            // i 的取值范围 [1, s.length()] 是因为可以对 s 做尝试性分割，也可以不分割（假如 s 本身已经是回文串了）
            for (int i = 1; i <= s.length(); i++) {
                String front = s.substring(0, i);
                String behind = s.substring(i);
                char[] frontReversed = reverse(front.toCharArray());
                if (front.equals(new String(frontReversed))) {
                    List<String> newProgress = new ArrayList<>(progress);
                    newProgress.add(front);
                    partitionInternal(newProgress, behind, result);
                }
            }
        }
    }

    /**
     *
     * @param input
     * @return
     */
    private char[] reverse(char[] input) {
        char[] result = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = input[input.length - 1 - i];
        }
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partition("aab"));
        System.out.println(solution.partition("bb"));
    }
}
