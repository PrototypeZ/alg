package leetcode.tencent.autumn2018.q4;

import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class Solution {

    private final int[] EMPTY_ARRAY = new int[0];

//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//    }


//    private int findTheKSmallestInTwoArrays(int[] arrayA, int[] arrayB, int k) {
//        if (arrayA.length > arrayB.length) {
//            // 规定 A 数组的长度始终小于等于 B 数组，如果出现例外情况，则手动转化为规定的情况
//            return findTheKSmallestInTwoArrays(arrayB, arrayA, k);
//        }
//        if (arrayA.length == 0) {
//            // 两个数组中较短的 A 数组假如在切割过程中，被切割到长度为 0
//            return arrayB[k - 1];
//        }
//        if (k == 1) {
//            // 问题规模缩小的过程中，除了其中一个数组长度变为 0 以外，已有可能 k 下降到 1
//            return Math.min(arrayA[0], arrayB[0]);
//        }
//
//        int pa = arrayA.length / 2;
//        int pb = arrayB.length / 2;
//
//        if (arrayA[pa] <= arrayB[pb]) {
//            int[] newArrayA;
//            if (pa < arrayA.length - 1) {
//                newArrayA = Arrays.copyOfRange(arrayA, pa + 1, arrayA.length - 1);
//            } else {
//                newArrayA = EMPTY_ARRAY;
//            }
//            return findTheKSmallestInTwoArrays(newArrayA, arrayB, k - pa);
//        }
//
//        if (arrayA[pa] > arrayB[pb]) {
//            int[] newArrayB;
//            if (pb < arrayB.length - 1) {
//                newArrayB = Arrays.copyOfRange(arrayB, pb + 1, arrayB.length - 1);
//            } else {
//                newArrayB = EMPTY_ARRAY;
//            }
//            return findTheKSmallestInTwoArrays(arrayA, newArrayB, k - pb);
//        }
//    }


    public static void main(String[] args) {

    }
}
