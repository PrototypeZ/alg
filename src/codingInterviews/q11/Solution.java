package codingInterviews.q11;

/**
 *
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * 通过次数151,370提交次数306,534
 * 在真实的面试中遇到过这道题？
 *
 *
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 * 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如:
 * 数组 {3, 4, 5, 1, 2} 为 {1, 2, 3, 4, 5} 的一个旋转， 该数组的最小值为 1。
 *
 * Created by Jason on 2020/10/17/0017.
 */
public class Solution {

    /**
     * 本来用二分查找其实很容易理解，但是数组中元素可能存在相等元素，这会影响二分查找
     * 假如二分查找的过程中如果取到一个 middle 值，middle 值如果和左边或者右边相等，
     * 很难判断， 此时 左边 >= 右边。
     * @param array
     * @param left
     * @param right
     * @return
     */
    public int findItInternal(int[] array, int left, int right) {
        if (left == right) {
            return array[left];
        } else if (left + 1 == right) {
            return Math.min(array[left], array[right]);
        } else {
            if (array[left] < array[right]) {
                System.out.println("come on");
                return array[left];
            } else  {
                // left < right - 1, left 和 right 中间至少隔着一个元素
                int middle = (left + right) / 2;
                if (array[left] == array[middle] && array[middle] == array[right]) {
                    // 最极端的情况
                    return findItInternal(array, left + 1, right);
                }
                if (array[middle] > array[right]) {
                    return findItInternal(array, middle, right);
                } else {
                    // array[middle] <= array[right];
                    return findItInternal(array, left, middle);
                }
            }
        }
    }

    public int minArray(int[] array) {
        return findItInternal(array, 0, array.length - 1);
    }

    /**
     * 更简单的方法，只考虑指针夹逼的过程，不考虑 left，right 实际的值大小比较，
     * 上面复杂的方法，有两个维度，即考虑指针夹逼的过程，又考虑夹逼过程中，left，right 实际的值大小比较
     */
    public int minArrayGaoShou(int[] numbers) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (numbers[m] > numbers[j]) {
                i = m + 1;
            } else if (numbers[m] < numbers[j]) {
                j = m;
            } else {
                // numbers[m] == numbers[j];
                j--;
            }
        }
        return numbers[i];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minArray(new int[]{3, 4, 5, 1, 2})); // 1
        System.out.println(solution.minArray(new int[]{1, 0, 1, 1, 1})); // 0
        System.out.println(solution.minArray(new int[]{1, 1, 1, 0, 1})); // 0
        System.out.println(solution.minArray(new int[]{1})); // 1

        System.out.println();

//        System.out.println(solution.findIt2(new int[]{3, 4, 5, 1, 2}));
//        System.out.println(solution.findIt2(new int[]{1, 0, 1, 1, 1}));
//        System.out.println(solution.findIt2(new int[]{1, 1, 1, 0, 1}));
    }
}
