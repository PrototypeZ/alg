package leetcode.q91;

import java.util.HashMap;
import java.util.Map;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    private static Map<String, String> dic = new HashMap<String, String>() {{
        put("1", "a");
        put("2", "b");
        put("3", "c");
        put("4", "d");
        put("5", "e");
        put("6", "f");
        put("7", "g");
        put("8", "h");
        put("9", "i");
        put("10", "j");
        put("11", "k");
        put("12", "l");
        put("13", "m");
        put("14", "n");
        put("15", "o");
        put("16", "p");
        put("17", "q");
        put("18", "r");
        put("19", "s");
        put("20", "t");
        put("21", "u");
        put("22", "v");
        put("23", "w");
        put("24", "x");
        put("25", "y");
        put("26", "z");
    }};

    private Map<String, Integer> cache = new HashMap<>();


    /**
     * 这道题目不是回溯，是 dp，因为中间过程有很多子问题的结果可以复用
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 1;
        if (s.length() == 1) {
            return dic.get(s) == null ? 0 : 1;
        }

        Integer value = cache.get(s);
        if (value != null) return value;

        int result = 0;
//                = numDecodings(s.substring(0, 1)) * numDecodings(s.substring(1))
//                + (dic.get(s.substring(0, 2)) == null ? 0 : 1) * numDecodings(s.substring(2));

        boolean oneStepOk = numDecodings(s.substring(0, 1)) == 1;
        result += oneStepOk ? numDecodings(s.substring(1)) : 0;
        boolean twoStepOk = dic.get(s.substring(0, 2)) != null;
        result += twoStepOk ? numDecodings(s.substring(2)) : 0;


        cache.put(s, result);
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDecodings("12")); // 2
        System.out.println(solution.numDecodings("226")); // 3
    }
}
