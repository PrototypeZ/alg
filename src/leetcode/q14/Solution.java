package leetcode.q14;

/**
 *
 * ��дһ�������������ַ��������е������ǰ׺��

 ��������ڹ���ǰ׺�����ؿ��ַ���?""��

 ʾ��?1:

 ����: ["flower","flow","flight"]
 ���: "fl"
 ʾ��?2:

 ����: ["dog","racecar","car"]
 ���: ""
 ����: ���벻���ڹ���ǰ׺��
 ˵��:

 ��������ֻ����Сд��ĸ?a-z?��



 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/longest-common-prefix
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
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
