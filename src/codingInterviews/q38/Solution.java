package codingInterviews.q38;

import java.util.*;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。

 ?

 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

 ?

 示例:

 输入：s = "abc"
 输出：["abc","acb","bac","bca","cab","cba"]
 ?

 限制：

 1 <= s 的长度 <= 8

 通过次数48,408提交次数88,729
 在真实的面试中遇到过这道题？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by Jason on 2020/10/25/0025.
 */
public class Solution {


    public String[] permutation(String s) {
        if (s == null || s.length() == 0) return new String[0];
        char[] charArray = s.toCharArray();
        List<Character> restChars = new ArrayList<>();
        for (char c : charArray) {
            restChars.add(c);
        }
        List<String> result = new ArrayList<>();
        permutationInternal(restChars, new ArrayList<>(), result);
        return result.toArray(new String[result.size()]);
    }


    public void permutationInternal(List<Character> restChars, List<Character> currentPath, List<String> result) {
        if (restChars.size() == 1) {
            currentPath.add(restChars.get(0));
            StringBuilder sb = new StringBuilder();
            for (Character c : currentPath) {
                sb.append(c);
            }
            result.add(sb.toString());
        } else {
            Set<Character> used = new HashSet<>();
            for (char c : restChars) {
                if (!used.contains(c)) {
                    List<Character> newRestChars = new ArrayList<>(restChars);
                    newRestChars.remove((Character)c);
                    List<Character> newCurrentPath = new ArrayList<>(currentPath);
                    newCurrentPath.add(c);
                    permutationInternal(newRestChars, newCurrentPath, result);
                    used.add(c);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.permutation("abc")));
        System.out.println(Arrays.toString(solution.permutation("abb")));
        System.out.println(Arrays.toString(solution.permutation("a")));
    }
}
