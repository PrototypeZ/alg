package codingInterviews.q11;

/**
 * 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如:
 * 数组 {3, 4, 5, 1, 2} 为 {1, 2, 3, 4, 5} 的一个旋转， 该数组的最小值为 1。
 *
 * Created by Jason on 2020/10/17/0017.
 */
public class Solution {

    public int findItInternalWithBug(int[] array, int left, int right) {
        if (array[left] < array[right]) {
            return array[left];
        }
        if (left == right) {
            return left;
        } else if (left + 1 == right) {
            return Math.min(array[left], array[right]);
        } else {
            // left < right - 1
            int middle = (left + right) / 2;
            if (array[middle] >= array[right]) {
                return findItInternal(array, middle, right);
            } else {
                return findItInternal(array, left, middle);
            }
        }
    }

    public int findItInternal(int[] array, int left, int right) {
        if (left == right) {
            return left;
        } else if (left + 1 == right) {
            return Math.min(array[left], array[right]);
        } else {
            if (array[left] < array[right]) {
                return array[left];
            } else if (array[left] > array[right]) {
                // left < right - 1
                int middle = (left + right) / 2;
                if (array[middle] > array[right]) {
                    return findItInternal(array, middle, right);
                } else if (array[middle] < array[right]){
                    return findItInternal(array, left, middle);
                } else {
                    // array[left] > array[middle] == array[right]
                    return findItInternal(array, middle, right);
                }
            } else {
                // array[left] == array[right]
                int middle = (left + right) / 2;
                if (array[middle] > array[right]) {
                    return findItInternal(array, middle, right);
                } else if (array[middle] < array[right]){
                    return findItInternal(array, left, middle);
                } else {
                    // array[left] == array[middle] == array[right]
                    return findItInternal(array, left + 1, right);
                }
            }
        }
    }

    public int findIt(int[] array) {
        return findItInternal(array, 0, array.length - 1);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findIt(new int[]{3, 4, 5, 1, 2}));
        System.out.println(solution.findIt(new int[]{1, 0, 1, 1, 1}));
        System.out.println(solution.findIt(new int[]{1, 1, 1, 0, 1}));
    }
}
