package codingInterviews.q12;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    /**
     * 这种解法不会尽快结束查找，会找出所有种可能，所以效率比第一种低，数据量大的时候容易超出时间限制
     * @param board
     * @param word
     * @return
     */
    public boolean exist2(char[][] board, String word) {
        if (word == null || word.equals("")) {
            return false;
        }
        if (board == null) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][];
        for (int i = 0; i < board.length; i++) {
            visited[i] = new boolean[board[i].length];
        }
        ArrayList<Stack<int[]>> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 可以改为效率高的形式，一旦 found 返回 true，立刻返回 true
                boolean found = existInternal2(board, word, 0, i, j, visited,
                        new Stack<>(), result);
            }
        }
        return result.size() > 0;
    }

    /**
     * if (bottomFind) return true;
     * 这些语句去掉就是效率低，但是可以找出所有情况的解法，
     * 现在这种解法是只要找到一条路径就立刻返回成功
     */
    public boolean existInternal2(char[][] board, String word, int pointer, int testX, int testY,
                                  boolean[][] visited, Stack<int[]> path, List<Stack<int[]>> results) {

        if (testX < 0 || testX >= board.length || testY < 0 || testY >= board[testX].length) {
            return false;
        }
        if (visited[testX][testY]) {
            return false;
        }

        char target = word.charAt(pointer);
        if (target != board[testX][testY]) return false;
        if (pointer == word.length() - 1) {
            // lastTest
            results.add(path);
            return true;
        }

        // pointer < word.length() - 1

        visited[testX][testY] = true;
        path.push(new int[]{testX, testY});

        boolean leftFind;
        boolean topFind;
        boolean rightFind;
        boolean bottomFind;
        // top

        topFind = existInternal2(board, word, pointer + 1,
                testX - 1, testY,
                visited, path, results);
        if (topFind) return true;

        // left

        leftFind = existInternal2(board, word, pointer + 1,
                testX, testY - 1,
                visited, path, results);
        if (leftFind) return true;

        // right

        rightFind = existInternal2(board, word, pointer + 1,
                testX, testY + 1,
                visited, path, results);
        if (rightFind) return true;

        // bottom
        bottomFind = existInternal2(board, word, pointer + 1,
                testX + 1, testY,
                visited, path, results);
        if (bottomFind) return true;

        path.pop();
        visited[testX][testY] = false;

        return false;
    }


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
        if (board[testX][testY] == word.charAt(pointerInWord)) {
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

        }
        return false;
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

        System.out.println(solution.exist2(
                new char[][]{
                        new char[]{'A', 'B', 'C', 'E'},
                        new char[]{'S', 'F', 'C', 'S'},
                        new char[]{'A', 'D', 'E', 'E'}
                },
                "ABCCED"
        ));
        System.out.println(solution.exist2(
                new char[][]{
                        new char[]{'a', 'b'},
                        new char[]{'c', 'd'},
                },
                "ABCCED"
        ));

        System.out.println(solution.exist2(
                new char[][]{
                        new char[]{'a', 'b'},
                },
                "ba"
        ));
    }
}
