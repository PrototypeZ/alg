package codingInterviews.kBiggest;

/**
 * 在数组中找第 n 大的数
 */
public class Solution {

    // 只处理 rightIndex > leftIndex 的情况
    private int findKSmallest(int leftIndex, int rightIndex, int kBiggest, int[] array) {
        if (rightIndex - leftIndex == 1) {
            if (kBiggest == 1) {
                return Math.min(array[leftIndex], array[rightIndex]);
            } else if (kBiggest == 2) {
                return Math.max(array[leftIndex], array[rightIndex]);
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
                return findKSmallest(i + 1, rightIndex, kBiggest - baseRank, array);
            } else {
                return findKSmallest(leftIndex, i - 1, kBiggest , array);
            }
        }
    }

    private int findKBiggest(int[] array, int k) {
        return findKSmallest(array, array.length - k + 1);
    }

    private int findKSmallest(int[] array, int k) {
        if (k > array.length || k <= 0) {
            return -1;
        } else {
            return findKSmallest(0, array.length - 1, k, array);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] array1 = new int[]{};
        System.out.println("array1:" + solution.findKSmallest(array1, 1)); // -1
        System.out.println("array1:" + solution.findKBiggest(array1, 1)); // -1

        int[] array2 = new int[]{3, 234, 2, 8, 3, 1, 8, 5};
        System.out.println("array2:" + solution.findKSmallest(array2, 2)); // 2
        System.out.println("array2:" + solution.findKBiggest(array2, array2.length - 2 + 1)); // 2

        int[] array3 = new int[]{2,6,3,5,9,1,5,3}; // 3
        System.out.println("array3:" + solution.findKSmallest(array3, 3));
        System.out.println("array3:" + solution.findKBiggest(array3, array3.length - 3 + 1));

        int[] array4 = new int[]{1};
        System.out.println("array4:" + solution.findKSmallest(array4, 1)); // 1
        System.out.println("array4:" + solution.findKBiggest(array4, array4.length - 1 + 1)); // 1

        int[] array5 = new int[]{1, 2};
        System.out.println("array5:" + solution.findKSmallest(array5, 1)); // 1
        System.out.println("array5:" + solution.findKBiggest(array5, array5.length - 1 + 1)); // 1

        int[] array6 = new int[]{2, 1};
        System.out.println("array6:" + solution.findKSmallest(array6, 1)); // 1
        System.out.println("array6:" + solution.findKBiggest(array6, array6.length - 1 + 1)); // 1


    }
}
