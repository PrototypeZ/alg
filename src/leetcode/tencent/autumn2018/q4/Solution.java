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
     * 1. 在两个有序数组里寻找中位数。可以认为在两个数组各切一刀，每个数组被切割为两个新的子数组。
     * 这样产生 4 个新的子数组，如果左边的两个子数组的最大值小于等于右边两个子数组的最小值，那么
     * 我们可以认为下刀的两个位置，和中位数的位置密切相关（可能和两个下刀的位置都有关系，
     * 也可能只和其中一刀的位置有关系）。
     * <p>
     * 2. 如果两个数组的长度和为偶数，那么左边两个子数组的长度和 与 右边两个子数组的长度和 相等，
     * 为总长度和的一半。如果两个数组的长度和为奇数，那么左边两个子数组的长度和 与 右边两个子数组的
     * 长度和相差 1， 不一定谁大 1。
     * <p>
     * 3. 如果两个数组的长度分别为 m，n； 这两个数组按升序重新排成一个新的数组为 p 。那么如果 m+n 为偶数，
     * 中位数就是 (p[(m+n)/2-1] + p[m+n/2])/2。 如果 m+n 为奇数，那么中位数就是 p[(m+n)/2]。
     * <p>
     * 4. 那么在两个升序数组中寻找中位数的问题可以转换为，在两个升序数组内查找第 k 大数的问题，也可以说
     * 在两个升序数组内查找第 k 小数的问题。
     * <p>
     * 5. 也就是说问题可以转为，在新数组 p 内，如果 m+n 为偶数，找到第 (m+n)/2-1 大和第 (m+n)/2+1 小的两个数；
     * 如果 m+n 为奇数，找到第 (m+n)/2+1 小的数。
     * <p>
     * 所以这个函数的定义就是在两个数组内找到第 k 小的数，显然 k >= 1
     * <p>
     * 6. 问题1： 对于两个升序数组，A 和 B，合为一个升序数组，找其中第 k 小的元素。
     * <p>
     * 问题2： 对于两个升序数组，A 和 B，把数组 A 的第 a 小元素和数组 B 的第 k -a 小元素，进行比较,（a < k）。
     * 6.1 若 A[a - 1] < B [k - a - 1] , 求 A 数组的子序列 A[a ~ end] 和 B 数组，
     * 这两个数组合并为一个新的升序数组后， 第 k - a 小的元素。
     * 6.2 若 A[a - 1] > B [k - a - 1], 求 B 数组的子序列 B [k - 1 ~ end] 和 A 数组，
     * 这两个数组合并为一个新的升序数组后，第 a 小的元素
     * 6.3 若 A[a -1] == B[k - a - 1], 这两个数同时为 A 和 B 合并为一个新的升序数组后，第 k 小的元素。
     * 在这个合成后的新数组里， A[a - 1], 和 B[k - a - 1] 这两个数，谁前谁后无所谓。
     * <p>
     * <p>
     * 问题一 和 问题二本质上是一个问题，但是问题二相对于问题一，在指定一个 a 的情况下，缩小了问题规模，
     * 把大问题转化为了本质一样的小问题，便于递归。
     */
    public double findKSmallestInArrays(int[] arrayA, int[] arrayB, int k) {
        if (arrayA.length > arrayB.length) {
            return findKSmallestInArrays(arrayB, arrayA, k);
        }
        // 走到这里我们可以认为 arrayA 始终比 arrayB 短了（小于等于）
        if (arrayA.length == 0) {
            // 在把问题进行切割的过程中，其中一个数组被切割为了 0
            return arrayB[k - 1];
        }
        if (k == 1) {
            return Math.min(arrayA[0], arrayB[0]);
        }

        /**
         * 划分 pa 依据，首先必须大于等于 1（因为求第 pa 大）
         * 还得比 k 小，等于 k 或者大于 k ，pb 就没法划分了
         */
        int pa = Math.min(Math.max(arrayA.length / 2, 1), k - 1);
//        int pa = Math.max(Math.min(arrayA.length / 2, k - 1), 1);
        int pb = k - pa;

        if (arrayA[pa - 1] > arrayB[pb - 1]) {
            int[] newArrayA = Arrays.copyOfRange(arrayA, 0, pa);
            int[] newArrayB = Arrays.copyOfRange(arrayB, pb, arrayB.length);
            // 淘汰了 B 数组里前 pb 个数， 在数组 A 内从第 pa 个数开始都大于等于 A[pa - 1]
            // 所以新数组的查找规律如下
            return findKSmallestInArrays(newArrayA, newArrayB, pa);
        } else if (arrayA[pa - 1] < arrayB[pb - 1]) {
            int[] newArrayA = Arrays.copyOfRange(arrayA, pa, arrayA.length);
            int[] newArrayB = Arrays.copyOfRange(arrayB, 0, pb);
            // 淘汰了 A 数组里前 pa 个数， 在数组 A 内从第 pa 个数开始都大于等于 A[pa - 1]
            // 所以新数组的查找规律如下
            return findKSmallestInArrays(newArrayA, newArrayB, pb);
        } else {
            return arrayA[pa - 1];
        }
    }
}