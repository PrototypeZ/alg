package leetcode.q14;

/**
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串?""。

 示例?1:

 输入: ["flower","flow","flight"]
 输出: "fl"
 示例?2:

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。
 说明:

 所有输入只包含小写字母?a-z?。



 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-common-prefix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2019/10/15/0015.
 */
public class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        for (int i = 0; i < strs[0].length(); i++) {
            char charToCompare = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() < i + 1 || strs[j].charAt(i) != charToCompare) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
//        int i = 0;

//        while (true) {
//            char c = strs[0].charAt(i);
//            boolean exit = false;
//            for (int t = 1; t < strs.length; t++) {
//                if (strs[t].charAt(i) != c) {
//                    exit = true;
//                    break;
//                }
//            }
//            if (exit) break;
//            i++;
//        }

//        return strs[0].substring(0, i);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(solution.longestCommonPrefix(new String[]{"dog","racecar","car"}));
        System.out.println(solution.longestCommonPrefix(new String[]{"",""}));
    }
}
