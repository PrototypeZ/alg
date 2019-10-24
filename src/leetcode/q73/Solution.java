package leetcode.q73;

import java.util.Arrays;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 *
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * 进阶:
 *
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    /**
     * 这题本身没什么难度，但是有细节需要注意，就是赋 0 的操作一定要在遍历完整个矩阵以后再做，
     * 因为如果一遍赋 0 一边遍历，会影响后续的遍历结果，下面这这种解法的思路是我先填充
     * (1,1)~(m-1, n-1) 这个区域的值，整个矩阵的第一行和第一列先不动
     * （虽然不动，但是我们会在遍历时记录最终应该如何处理这两个特例），我们把这个第一行和第一列用来做标志位，
     * 在遍历的过程中来记录 (1,1)~(m-1, n-1) 这个区域内哪些行和列需要被填充为 0，
     * 最后再处理第一行和第一列
     */
    public void setZeroes(int[][] matrix) {
        boolean firstRowShouldBeZero = false;
        boolean firstColumnShouldBeZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        firstRowShouldBeZero = true;
                    }
                    if (j == 0) {
                        firstColumnShouldBeZero = true;
                    }
                    if (i > 0 && j > 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        // check rows && columns
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowShouldBeZero) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (firstColumnShouldBeZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] test1 = new int[][] {
                new int[]{1, 1, 1},
                new int[]{1, 0, 1},
                new int[]{1, 1, 1},
        };
        solution.setZeroes(test1);
        System.out.println(Arrays.deepToString(test1));

        int[][] test2 = new int[][]{
                new int[]{0, 1, 2, 0},
                new int[]{3, 4, 5, 2},
                new int[]{1, 3, 1, 5},
        };
        solution.setZeroes(test2);
        System.out.println(Arrays.deepToString(test2));
    }
}
