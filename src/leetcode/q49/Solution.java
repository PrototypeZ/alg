package leetcode.q49;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by Jason on 2019/10/21/0021.
 */
public class Solution {

    /**
     * 无法处理字符串中如果存在相同的字符
     */
//    public List<List<String>> groupAnagrams(String[] strs) {
//        Map<Integer, List<String>> map = new HashMap<>();
//        for (String str : strs) {
//            int value = 0;
//            for (char character : str.toCharArray()) {
//                int mask = 1 << character - 'a';
//                value = value | mask;
//            }
//            List<String> strings = map.get(value);
//            if (strings == null) {
//                strings = new ArrayList<>();
//                map.put(value, strings);
//            }
//            strings.add(str);
//        }
//        return new ArrayList<>(map.values());
//    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] chars = new int[26];
            for (char character : str.toCharArray()) {
                chars[character - 'a']++;
            }
            String key = Arrays.stream(chars)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(","));
            List<String> value = map.computeIfAbsent(key, k -> new ArrayList<>());
            value.add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
