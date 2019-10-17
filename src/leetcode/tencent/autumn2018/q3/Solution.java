package leetcode.tencent.autumn2018.q3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

 示例 1:

 输入: "abcabcbb"
 输出: 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 示例 2:

 输入: "bbbbb"
 输出: 1
 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 示例 3:

 输入: "pwwkew"
 输出: 3
 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * Created by Jason on 2019/2/5/0005.
 */
public class Solution {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int startIndex = 0;
        int length = 0;
        int longest = 1;
        Map<Character, Integer> cache = new HashMap<>();
        char[] characters = s.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            Integer index = cache.get(characters[i]);
            if (index == null) {
                length++;
            } else {
                // index 表示出现此时出现重复字符在已经记录的当前最长串中的下标
                // index - startIndex 表示 index 和 startIndex 之间的长度（不包含 index）
                // length - (index - startIndex) 表示把当前串中位置 index 以前的元素都移除（不包含 index），剩余的串的长度
                length = length - (index - startIndex);

                // 把当前最长串中，index以前的元素都移除（包含index）
                // 为什么前面计算 length 的时候算法是不移除 index 的，而真正移除元素的时候又是算上 index 的呢？
                // 因为当遍历到重复元素的时候，除了把当前记录的最长串的重复元素移出之外，还要把此时遍历上的元素再加进去，
                // 所以对于计算新的最长串长度而言，两者是等价的。上面的计算 length 表达式等价于 length - (index - startIndex) - 1 + 1
                for (int j = startIndex; j <= index; j++) {
                    cache.remove(characters[j]);
                }
                startIndex = index + 1;
            }
            cache.put(characters[i], i);

            if (length > longest) {
                longest = length;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

//        System.out.println("abcabcbb = [" + solution.lengthOfLongestSubstring("abcabcbb") + "]");
//        System.out.println("bbbbb = [" + solution.lengthOfLongestSubstring("bbbbb") + "]");
//        System.out.println("pwwkew = [" + solution.lengthOfLongestSubstring("pwwkew") + "]");
        System.out.println("abba = [" + solution.lengthOfLongestSubstring("abba") + "]");
    }
}
