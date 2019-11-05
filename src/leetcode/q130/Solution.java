package leetcode.q130;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

 示例:

 X X X X
 X O O X
 X X O X
 X O X X
 运行你的函数后，矩阵变为：

 X X X X
 X X X X
 X X X X
 X O X X
 解释:

 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。



 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/surrounded-regions
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2019/11/4/0004.
 */
public class Solution {

    // 思路是 BFS
    //题目中解释说被包围的区间不会存在于边界上，所以我们会想到边界上的 OO 要特殊处理，只要把边界上的 OO 特殊处理了，那么剩下的 OO 替换成 XX 就可以了。问题转化为，如何寻找和边界联通的 OO
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        // 与边缘上的 O 联通的 O，包括边缘上的 O
        boolean[][] invalid = new boolean[board.length][board[0].length];
        // BFS 中待遍历的某一层
        List<int[]> currentInvalidCoordinates = new ArrayList<>();
        // 先初始化第一层
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; ) {
                if (board[i][j] == 'O') {
                    invalid[i][j] = true;
                    currentInvalidCoordinates.add(new int[]{i, j});
                }
                if (i == 0 || i == board.length - 1) {
                    j++;
                } else {
                    if (j == 0) {
                        j = board[i].length - 1;
                    } else {
                        // j == board[i].length - 1
                        j++;
                    }
                }
            }
        }
        // 广度优先搜索每一层，直到某一层找不到新的 invalid 点之后，遍历结束
        while (currentInvalidCoordinates.size() != 0) {
            List<int[]> newInvalidCoordinates = new ArrayList<>();
            for (int[] coordinates : currentInvalidCoordinates) {
                int i = coordinates[0];
                int j = coordinates[1];
                // left
                checkAround(i, j - 1, board, invalid, newInvalidCoordinates);
                // top
                checkAround(i - 1, j, board, invalid, newInvalidCoordinates);
                // right
                checkAround(i, j + 1, board, invalid, newInvalidCoordinates);
                // bottom
                checkAround(i + 1, j, board, invalid, newInvalidCoordinates);
            }
            currentInvalidCoordinates = newInvalidCoordinates;
        }

        // 重新遍历整个矩阵，进行替换
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O' && !invalid[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void checkAround(int i, int j, char[][] board, boolean[][] invalid, List<int[]> coordinators) {
        if (i > 0 && i < board.length && j > 0 && j < board[i].length) {
            if (!invalid[i][j] && board[i][j] == 'O') {
                invalid[i][j] = true;
                coordinators.add(new int[]{i, j});
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] test = new char[][]{
                new char[]{'X', 'X', 'X', 'X'},
                new char[]{'X', 'O', 'O', 'X'},
                new char[]{'X', 'X', 'O', 'X'},
                new char[]{'X', 'O', 'X', 'X'},
        };
        solution.solve(test);
        System.out.println(Arrays.deepToString(test));

        char[][] test2 = new char[][]{};
        solution.solve(test2);
        System.out.println(Arrays.deepToString(test2));
    }
}
