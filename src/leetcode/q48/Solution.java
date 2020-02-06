package leetcode.q48;

import java.util.Arrays;

/**
 *
 * 给定一个 n × n 的二维矩阵表示一个图像。

 将图像顺时针旋转 90 度。

 说明：

 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

 示例 1:

 给定 matrix =
 [
 [1,2,3],
 [4,5,6],
 [7,8,9]
 ],

 原地旋转输入矩阵，使其变为:
 [
 [7,4,1],
 [8,5,2],
 [9,6,3]
 ]
 示例 2:

 给定 matrix =
 [
 [ 5, 1, 9,11],
 [ 2, 4, 8,10],
 [13, 3, 6, 7],
 [15,14,12,16]
 ],

 原地旋转输入矩阵，使其变为:
 [
 [15,13, 2, 5],
 [14, 3, 4, 1],
 [12, 6, 8, 9],
 [16, 7,10,11]
 ]


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/rotate-image
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2019/10/21/0021.
 */
public class Solution {

    /**
     * 对于一个 n*n 的矩阵， 顺时针旋转 90 度相当于：
     * 1. 先按 “左下 - 右上” 对称轴进行翻转
     * 2. 按水平正中腰线进行翻转
     *
     * 扩展:
     * 顺时针旋转 90 度相当于逆时针旋转 270 度
     *
     * 顺时针旋转 180 度（相当于逆时针旋转 180 度）
     * 1. 按水平正中腰线进行翻转
     * 2. 按垂直正中腰线进行翻转
     *
     * 顺时针旋转 270 度（逆时针旋转 90度）
     * 1. 按 “左上 - 右下” 对称轴进行翻转
     * 2. 按水平正中腰线进行翻转
     */
    public void rotate(int[][] matrix) {
        // 先按 “左下-右上” 对称轴进行翻转
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix.length - 1 - i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - j][matrix.length - 1 - i];
                matrix[matrix.length - 1 - j][matrix.length - 1 - i] = tmp;
            }
        }
        // 再按矩阵自身水平正中腰线进行翻转
        for (int i = 0; i <= (matrix.length - 1) / 2; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - i][j];
                matrix[matrix.length - 1 - i][j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] test = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9},
        };
        solution.rotate(test);
        System.out.println(Arrays.deepToString(test));

        test = new int[][]{
                new int[]{5, 1, 9, 11},
                new int[]{2, 4, 8, 10},
                new int[]{13, 3, 6, 7},
                new int[]{15, 14, 12, 16},
        };
        solution.rotate(test);
        System.out.println(Arrays.deepToString(test));

        test = new int[][]{

        };
        solution.rotate(test);
        System.out.println(Arrays.deepToString(test));

        test = new int[][]{
            new int[]{1}
        };
        solution.rotate(test);
        System.out.println(Arrays.deepToString(test));
    }
}
