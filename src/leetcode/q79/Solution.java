package leetcode.q79;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public boolean exist(char[][] board, String word) {
        boolean[][] path = new boolean[board.length][board[0].length];
        return existInternal(board, word, 0, -1, -1, path);
    }

    // 回溯思想
    private boolean existInternal(char[][] board, String word, int charLookedFor, int lastX, int lastY, boolean[][] path) {
        if (charLookedFor == 0) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == word.charAt(charLookedFor)) {
                        path[i][j] = true;
                        boolean result = existInternal(board, word, 1, i, j, path);
                        if (result) {
                            return true;
                        } else {
                            // reset for next loop
                            path[i][j] = false;
                        }
                    }
                }
            }
            return false;
        } else if (charLookedFor == word.length()) {
            return true;
        } else {
            if (lastX > 0) {
                // check for top
                boolean result = test(board, word, charLookedFor, lastX - 1, lastY, path);
                if (result) return true;
            }
            if (lastX < board.length - 1) {
                // check for bottom
                boolean result = test(board, word, charLookedFor, lastX + 1, lastY, path);
                if (result) return true;
            }
            if (lastY > 0) {
                // check for left
                boolean result = test(board, word, charLookedFor, lastX, lastY - 1, path);
                if (result) return true;
            }
            if (lastY < board[0].length - 1) {
                // check for left
                boolean result = test(board, word, charLookedFor, lastX, lastY + 1, path);
                if (result) return true;
            }
            return false;
        }
    }

    private boolean test(char[][] board, String word, int charLookedFor, int x, int y , boolean[][] path) {
        if (!path[x][y] && board[x][y] == word.charAt(charLookedFor)) {
            path[x][y] = true;
            boolean result = existInternal(board, word, charLookedFor + 1, x, y, path);
            if (result) {
                return true;
            } else {
                path[x][y] = false;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.exist(new char[][]{
            new char[]{'A', 'B', 'C', 'E'},
            new char[]{'S', 'F', 'C', 'S'},
            new char[]{'A', 'D', 'E', 'E'},
        }, "ABCCED"));
    }
}
