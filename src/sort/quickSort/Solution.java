package sort.quickSort;

import java.util.Arrays;

public class Solution {

    private void quickSort(int[] array) {
        if (array.length == 0) return;
        quickSortInternal(array, 0, array.length - 1);
    }

    // 只 handle left < right 的情况， left == right 的情况通过外层方法，以及内部发起递归前检查过滤掉
    private void quickSortInternal(int[] array, int left, int right) {
        if (right - left == 1) {
            if (array[left] > array[right]) {
                int tmp = array[right];
                array[right] = array[left];
                array[left] = tmp;
            }
        } else {
            // right - left > 1
            // 这里用到了挖坑填数的思想， 在一个数组中，如果有赋值操作 a[m] = a[n]，那么 n 位置上的值
            // 已经被 m 位置保存， n 这个位置上虽然我没有清理它的值，但是这个位置其实已经空出来了，我可以
            // 填其它的数

            // base 是基准，最后用要做到 base 的左边都比 base 小， base 的右边都比 base 大
            int base = array[left];
            // 一开始，先把 array[left] 这个数挖走， 存在 base 里， 这样 array[left] 就空出来了
            int i = left;
            int j = right;

            while (i < j) {
                // 因为最后希望 left right 之间的数组呈现出 base 左边都比 base 小， base 右边都比 base 大
                // 所以先通过循环找出 base 右边比 base 小的，挪到左边去（注意点是循环中也要维持 j > i 这个条件）
                while (array[j] >= base && j > i) j--;
                array[i] = array[j];

                // 再通过循环找出 base 左边比 base 大的，挪到右边去（注意点是循环中也要维持 j > i 这个条件）
                while (array[i] <= base && j > i) i++;
                array[j] = array[i];

                // 如此循环，直到 i == j
            }
            // 再把这个位置填上 base，完美
            array[i] = base;
            if (i - 1 > left) {
                quickSortInternal(array, left, i - 1);
            }
            if (i + 1 < right) {
                quickSortInternal(array, i + 1, right);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] array1 = new int[]{};
        solution.quickSort(array1);
        System.out.println(Arrays.toString(array1));

        int[] array2 = new int[]{3, 234, 2, 8, 3, 1, 8, 5};
        solution.quickSort(array2);
        System.out.println(Arrays.toString(array2));

        int[] array3 = new int[]{2,6,3,5,9,1,5,3};
        solution.quickSort(array3);
        System.out.println(Arrays.toString(array3));

        int[] array4 = new int[]{1};
        solution.quickSort(array4);
        System.out.println(Arrays.toString(array4));
    }
}
