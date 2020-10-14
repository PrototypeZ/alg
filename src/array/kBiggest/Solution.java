package array.kBiggest;

import java.util.Arrays;

/**
 * 在数组中找第 n 大的数
 */
public class Solution {

    // 只处理 rightIndex > leftIndex 的情况
    private int findKBiggest(int leftIndex, int rightIndex, int kBiggest, int[] array) {
        if (rightIndex - leftIndex == 1) {
            if (kBiggest == 1) {
                return array[leftIndex];
            } else if (kBiggest == 2) {
                return array[rightIndex];
            } else {
                return -1;
            }
        } else {
            int base = array[leftIndex];
            int i = leftIndex;
            int j = rightIndex;

            while (i < j) {
                while (array[j] >= base && i < j) j--;
                array[i] = array[j];
                while (array[i] <= base && i < j) i++;
                array[j] = array[i];
            }

            array[i] = base;

            int baseRank = i - leftIndex + 1;
            if (baseRank == kBiggest) {
                return base;
            } else if (baseRank < kBiggest) {
                return findKBiggest(i + 1, rightIndex, kBiggest - baseRank, array);
            } else {
                return findKBiggest(leftIndex, i - 1, kBiggest , array);
            }
        }
    }

    private int findKBiggest(int[] array, int k) {
        if (k > array.length) {
            return -1;
        } else {
            return findKBiggest(0, array.length - 1, k, array);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] array1 = new int[]{};
        System.out.println(solution.findKBiggest(array1, 1));

        int[] array2 = new int[]{3, 234, 2, 8, 3, 1, 8, 5};
        System.out.println(solution.findKBiggest(array2, 2));

        int[] array3 = new int[]{2,6,3,5,9,1,5,3};
        System.out.println(solution.findKBiggest(array3, 3));

        int[] array4 = new int[]{1};
        System.out.println(solution.findKBiggest(array4, 1));
    }
}
