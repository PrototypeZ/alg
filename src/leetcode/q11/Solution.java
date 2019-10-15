package leetcode.q11;

/**
 * ���� n ���Ǹ����� a1��a2��...��an��ÿ�������������е�һ����?(i,?ai) ���������ڻ� n ����ֱ�ߣ���ֱ�� i?�������˵�ֱ�Ϊ?(i,?ai) �� (i, 0)���ҳ����е������ߣ�ʹ��������?x?�Ṳͬ���ɵ�����������������ˮ��

 ˵�����㲻����б��������?n?��ֵ����Ϊ 2��

 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/container-with-most-water
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������

 ʾ��:

 ����: [1,8,6,2,5,4,8,3,7]
 ���: 49


 * Created by Jason on 2019/10/14/0014.
 */
public class Solution {

    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int size;
            if (height[left] > height[right]) {
                size = (right - left) * height[right];
                right--;
            } else {
                size = (right - left) * height[left];
                left++;
            }
            maxArea = Math.max(size, maxArea);
        }
        return maxArea;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
