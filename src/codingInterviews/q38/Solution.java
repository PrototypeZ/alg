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
        // init restChars
        char[] charArray = s.toCharArray();
        List<Character> restChars = new ArrayList<>();
        for (char c : charArray) {
            restChars.add(c);
        }
        // init result
        List<String> result = new ArrayList<>();
        // do calculate
        permutationInternal(restChars, new ArrayList<>(), result);
        // array to List
        return result.toArray(new String[result.size()]);
    }

    /**
     * 求一个字符串的全排列，相当于先从里面选一个字符，再从剩下选一个字符，依次类推，直到最后一个字符也被选中，
     * 所以全排列的数量的计算公式是 n * (n-1) * (n-2) * ...... * 1，
     * 在这个过程中， currentPath.length + restChars.length == 原字符串的长度
     *
     * 但是上面的这个说法存在一个前提条件，就是字符串中没有重复的元素，一旦出现重复的元素，那按照上面的方法
     * 得出的全排列是有重复元素的。需要在全排列的时候进行剪枝。
     *
     *
     * 具体的全排列算法，新建一个 restChars 数组，初始化为全部的字符串元素，
     * 初始化为一个空的 currentPath 数组。
     *
     * permutationInternal 为计算全排列算法
     * 只要 restChars 中还有元素，从中任选 1 个元素，对剩下的元素进行全排列，
     * 假如 resetChars 有 n 个元素， 那么可以得到 n * permutationInternal(n - 1) 个全排列
     * 这是没考虑剪枝的情况，如果考虑剪枝：
     * “从 restChars 中任选 1 个元素” 这个流程会进行 n 次，我们建立一个 used 的 Set，每选 1 个，就加入到 used
     * 在选择的过程中，加入要选的元素在 used 列表里，就跳过。那么本来会进行 n 次的选择就会因为存在重复元素小于 n，
     * 这样就达到了剪枝的目的
     *
     * @param restChars 剩下的可以选的字符
     * @param currentPath 已经选择的字符
     * @param result 所有结果的集合
     */
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
                    // deep copy restChars
                    List<Character> newRestChars = new ArrayList<>(restChars);
                    newRestChars.remove((Character)c);
                    // deep copy currentPath
                    List<Character> newCurrentPath = new ArrayList<>(currentPath);
                    newCurrentPath.add(c);
                    // calculate permutationInternal of the rest
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
