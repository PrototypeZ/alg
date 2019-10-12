package leetcode.tencent.autumn2018.q3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * ����һ���ַ����������ҳ����в������ظ��ַ��� ��Ӵ� �ĳ��ȡ�

 ʾ�� 1:

 ����: "abcabcbb"
 ���: 3
 ����: ��Ϊ���ظ��ַ�����Ӵ��� "abc"�������䳤��Ϊ 3��
 ʾ�� 2:

 ����: "bbbbb"
 ���: 1
 ����: ��Ϊ���ظ��ַ�����Ӵ��� "b"�������䳤��Ϊ 1��
 ʾ�� 3:

 ����: "pwwkew"
 ���: 3
 ����: ��Ϊ���ظ��ַ�����Ӵ��� "wke"�������䳤��Ϊ 3��
 ��ע�⣬��Ĵ𰸱����� �Ӵ� �ĳ��ȣ�"pwke" ��һ�������У������Ӵ���
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
                length = length - (index - startIndex);
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
