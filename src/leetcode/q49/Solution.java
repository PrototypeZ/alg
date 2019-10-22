package leetcode.q49;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2019/10/21/0021.
 */
public class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int value = 0;
            for (char character : str.toCharArray()) {
                int mask = 1 << character - 'a';
                value = value | mask;
            }
            List<String> strings = map.get(value);
            if (strings == null) {
                strings = new ArrayList<>();
                map.put(value, strings);
            }
            strings.add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
