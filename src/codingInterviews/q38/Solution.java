package codingInterviews.q38;

import java.util.*;

/**
 * ����һ���ַ�������ӡ�����ַ������ַ����������С�

 ?

 �����������˳�򷵻�����ַ������飬�����治�����ظ�Ԫ�ء�

 ?

 ʾ��:

 ���룺s = "abc"
 �����["abc","acb","bac","bca","cab","cba"]
 ?

 ���ƣ�

 1 <= s �ĳ��� <= 8

 ͨ������48,408�ύ����88,729
 ����ʵ������������������⣿

 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
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
