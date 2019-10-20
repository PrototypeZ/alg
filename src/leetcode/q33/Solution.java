package leetcode.q33;

import java.util.Arrays;

/**
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

 你可以假设数组中不存在重复的元素。

 你的算法时间复杂度必须是 O(log n) 级别。

 示例 1:

 输入: nums = [4,5,6,7,0,1,2], target = 0
 输出: 4
 示例 2:

 输入: nums = [4,5,6,7,0,1,2], target = 3
 输出: -1


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2019/10/19/0019.
 */
public class Solution {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int pivot = searchForPivot(nums, 0, nums.length - 1);
        if (target == nums[pivot]) {
            return pivot;
        } else if (target > nums[pivot]) {
            return -1;
        } else {
            // condition: target < nums[pivot]，虽然满足这个条件，但是 pivot 可能左右区域都是空的
            // 不一定 pivot 左右两边边一定各存在一个升序数组, 即原数组可能给我们本来就是一个正常的升序数组
            if (pivot == nums.length - 1) {
                // 0 ~ nums.length - 1 已经是一个升序数组了
                return binarySearch(nums, 0, nums.length - 1, target);
            }

            // 此时，pivot 左（包含 pivot）右(不包含 pivot)两边一定有两个升序数组
            if (target > nums[nums.length - 1]) {
                // confition: nums[nums.length-1] < target < nums[pivot]
                return binarySearch(nums, 0, pivot, target);
            } else {
                // condition: target < nums[pivot] && target <= nums[nums.length-1]
                return binarySearch(nums, pivot + 1, nums.length - 1, target);
            }
        }
    }

    /**
     * 返回值 pivot 定义，左边那个升序数组的最大值的下标
     */
    private int searchForPivot(int[] nums, int start, int end) {
        // 不停往下划分子问题的时候，最小集可能划分的只有 1 个数
        if (start == end) {
            return start;
        }
        // 走到这里，子问题一定大于等于 2， 但是可能此时已经是一个标准升序数组
        if (nums[start] < nums[end]) {
            // 已经是升序数组了，所以最后一个是 pivot
            return end;
        }
        int k  = (start + end) / 2;

        if (nums[k+1] < nums[k]) {
            // 一次找到
            return k;
        }

        // 划分子问题的时候，一定要缩小范围，这就是下面 k+1, k-1 的含义， 因为 k 可能和 start 相等
        if (nums[k] > nums[end]) {
            // k 在左边区域， 走到这里一定有 end > start，所以 k+1 不会越界
            return searchForPivot(nums, k + 1, end);
        } else {
            // k 在右边区域
            // 两个下标取中点，中点可能等于 start ，所以 k - 1 需要防止越界
            return searchForPivot(nums, start, Math.max(k - 1, start));
        }
    }

    /**
     *
     * @param start inclusive
     * @param end inclusive
     */
    private int binarySearch(int[] nums, int start, int end, int target) {
        int r = Arrays.binarySearch(nums, start, end + 1, target);
        return r < 0 ? -1 : r;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // -1
        System.out.println(solution.search(new int[]{3, 1}, 0 )); // -1
        System.out.println(solution.search(new int[]{1, 3}, 3 )); // 1
        System.out.println(solution.search(new int[]{3, 1}, 3)); // 0
        System.out.println(solution.search(new int[]{5, 1, 3}, 1)); // 1
        System.out.println(solution.search(new int[]{1, 3}, 1)); // 0
        System.out.println(solution.search(new int[]{3, 1}, 1)); // 1

    }
}
