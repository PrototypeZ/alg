package leetcode.tencent.autumn2018.q4;

import java.util.Arrays;

/**
 * Created by Jason on 2019/1/12/0012.
 */
class Solution {

    public static void main(String[] args) {
//        double result = new Solution().findMedianSortedArrays(
//                new int[]{1, 3},
//                new int[]{2}
//        );

        double result = new Solution().findMedianSortedArrays(
                new int[]{1, 2},
                new int[]{3, 4}
        );

        System.out.println(result);
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        double mid1 = findKSmallestInArrays(nums1, nums2, total / 2 + 1);
        if (total % 2 == 0) {
            double mid2 = findKSmallestInArrays(nums1, nums2, total / 2);
            return (mid1 + mid2) / 2;
        } else {
            return mid1;
        }
    }

    /**
     * 1. ����������������Ѱ����λ����������Ϊ�������������һ����ÿ�����鱻�и�Ϊ�����µ������顣
     * �������� 4 ���µ������飬�����ߵ���������������ֵС�ڵ����ұ��������������Сֵ����ô
     * ���ǿ�����Ϊ�µ�������λ�ã�����λ����λ��������أ����ܺ������µ���λ�ö��й�ϵ��
     * Ҳ����ֻ������һ����λ���й�ϵ����
     * <p>
     * 2. �����������ĳ��Ⱥ�Ϊż������ô�������������ĳ��Ⱥ� �� �ұ�����������ĳ��Ⱥ� ��ȣ�
     * Ϊ�ܳ��Ⱥ͵�һ�롣�����������ĳ��Ⱥ�Ϊ��������ô�������������ĳ��Ⱥ� �� �ұ������������
     * ���Ⱥ���� 1�� ��һ��˭�� 1��
     * <p>
     * 3. �����������ĳ��ȷֱ�Ϊ m��n�� ���������鰴���������ų�һ���µ�����Ϊ p ����ô��� m+n Ϊż����
     * ��λ������ (p[(m+n)/2-1] + p[m+n/2])/2�� ��� m+n Ϊ��������ô��λ������ p[(m+n)/2]��
     * <p>
     * 4. ��ô����������������Ѱ����λ�����������ת��Ϊ�����������������ڲ��ҵ� k ���������⣬Ҳ����˵
     * ���������������ڲ��ҵ� k С�������⡣
     * <p>
     * 5. Ҳ����˵�������תΪ���������� p �ڣ���� m+n Ϊż�����ҵ��� (m+n)/2-1 ��͵� (m+n)/2+1 С����������
     * ��� m+n Ϊ�������ҵ��� (m+n)/2+1 С������
     * <p>
     * ������������Ķ�������������������ҵ��� k С��������Ȼ k >= 1
     * <p>
     * 6. ����1�� ���������������飬A �� B����Ϊһ���������飬�����е� k С��Ԫ�ء�
     * <p>
     * ����2�� ���������������飬A �� B�������� A �ĵ� a СԪ�غ����� B �ĵ� k -a СԪ�أ����бȽ�,��a < k����
     * 6.1 �� A[a - 1] < B [k - a - 1] , �� A ����������� A[a ~ end] �� B ���飬
     * ����������ϲ�Ϊһ���µ���������� �� k - a С��Ԫ�ء�
     * 6.2 �� A[a - 1] > B [k - a - 1], �� B ����������� B [k - 1 ~ end] �� A ���飬
     * ����������ϲ�Ϊһ���µ���������󣬵� a С��Ԫ��
     * 6.3 �� A[a -1] == B[k - a - 1], ��������ͬʱΪ A �� B �ϲ�Ϊһ���µ���������󣬵� k С��Ԫ�ء�
     * ������ϳɺ��������� A[a - 1], �� B[k - a - 1] ����������˭ǰ˭������ν��
     * <p>
     * <p>
     * ����һ �� �������������һ�����⣬������������������һ����ָ��һ�� a ������£���С�������ģ��
     * �Ѵ�����ת��Ϊ�˱���һ����С���⣬���ڵݹ顣
     */
    public double findKSmallestInArrays(int[] arrayA, int[] arrayB, int k) {
        if (arrayA.length > arrayB.length) {
            return findKSmallestInArrays(arrayB, arrayA, k);
        }
        // �ߵ��������ǿ�����Ϊ arrayA ʼ�ձ� arrayB ���ˣ�С�ڵ��ڣ�
        if (arrayA.length == 0) {
            // �ڰ���������и�Ĺ����У�����һ�����鱻�и�Ϊ�� 0
            return arrayB[k - 1];
        }
        if (k == 1) {
            return Math.min(arrayA[0], arrayB[0]);
        }

        /**
         * ���� pa ���ݣ����ȱ�����ڵ��� 1����Ϊ��� pa ��
         * ���ñ� k С������ k ���ߴ��� k ��pb ��û��������
         */
        int pa = Math.min(Math.max(arrayA.length / 2, 1), k - 1);
//        int pa = Math.max(Math.min(arrayA.length / 2, k - 1), 1);
        int pb = k - pa;

        if (arrayA[pa - 1] > arrayB[pb - 1]) {
            int[] newArrayA = Arrays.copyOfRange(arrayA, 0, pa);
            int[] newArrayB = Arrays.copyOfRange(arrayB, pb, arrayB.length);
            // ��̭�� B ������ǰ pb ������ ������ A �ڴӵ� pa ������ʼ�����ڵ��� A[pa - 1]
            // ����������Ĳ��ҹ�������
            return findKSmallestInArrays(newArrayA, newArrayB, pa);
        } else if (arrayA[pa - 1] < arrayB[pb - 1]) {
            int[] newArrayA = Arrays.copyOfRange(arrayA, pa, arrayA.length);
            int[] newArrayB = Arrays.copyOfRange(arrayB, 0, pb);
            // ��̭�� A ������ǰ pa ������ ������ A �ڴӵ� pa ������ʼ�����ڵ��� A[pa - 1]
            // ����������Ĳ��ҹ�������
            return findKSmallestInArrays(newArrayA, newArrayB, pb);
        } else {
            return arrayA[pa - 1];
        }
    }
}