package codingInterviews.q11;

/**
 * ��ת�������С����
 * ��һ�������ʼ�����ɸ�Ԫ�ذᵽ�����ĩβ�����ǳ�֮Ϊ�������ת��
 * ����һ����������������һ����ת�������ת�������СԪ�ء�����:
 * ���� {3, 4, 5, 1, 2} Ϊ {1, 2, 3, 4, 5} ��һ����ת�� ���������СֵΪ 1��
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
