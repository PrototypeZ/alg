package codingInterviews.q12;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

 [["a","b","c","e"],
 ["s","f","c","s"],
 ["a","d","e","e"]]

 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

 示例 1：

 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 输出：true
 示例 2：

 输入：board = [["a","b"],["c","d"]], word = "abcd"
 输出：false

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2020/10/17/0017.
 */
class Solution {
    // 回溯
    public boolean exist(char[][] board, String word) {
        boolean[][] path = new boolean[board.length][];
        for (int i = 0; i < board.length; i++) {
            path[i] = new boolean[board[i].length];
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean found = existsInternal(board, path, word, 0, i, j);
                if (found) return true;
            }
        }
        return false;
    }

    public boolean existsInternal(char[][] board, boolean[][] path, String word, int pointerInWord, int testX, int testY) {
        if (board[testX][testY] != word.charAt(pointerInWord)) {
            return false;
        } else {
            path[testX][testY] = true;
            if (pointerInWord == word.length() - 1) {
                return true;
            }

            if (testX + 1 < board.length && !path[testX + 1][testY]) {
                boolean found = existsInternal(board, path, word, pointerInWord + 1, testX + 1, testY);
                if (found) return true;
            }
            if (testX - 1 >= 0 && !path[testX - 1][testY]) {
                boolean found = existsInternal(board, path, word, pointerInWord + 1, testX - 1, testY);
                if (found) return true;
            }
            if (testY + 1 < board[testX].length && !path[testX][testY + 1]) {
                boolean found = existsInternal(board, path, word, pointerInWord + 1, testX, testY + 1);
                if (found) return true;
            }
            if (testY - 1 >= 0 && !path[testX][testY - 1]) {
                boolean found = existsInternal(board, path, word, pointerInWord + 1, testX, testY - 1);
                if (found) return true;
            }

            // back-tracking
            path[testX][testY] = false;
            return false;

        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.exist(
                new char[][]{
                        new char[]{'A', 'B', 'C', 'E'},
                        new char[]{'S', 'F', 'C', 'S'},
                        new char[]{'A', 'D', 'E', 'E'}
                },
                "ABCCED"
        ));
        System.out.println(solution.exist(
                new char[][]{
                        new char[]{'a', 'b'},
                        new char[]{'c', 'd'},
                },
                "ABCCED"
        ));

        System.out.println(solution.exist(
                new char[][]{
                        new char[]{'a', 'b'},
                },
                "ba"
        ));
    }
}
