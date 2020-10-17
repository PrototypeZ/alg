package codingInterviews.q12;

/**
 * �����һ�������������ж���һ���������Ƿ����һ������ĳ�ַ��������ַ���·����·�����ԴӾ����е�����һ��ʼ��ÿһ�������ھ����������ҡ��ϡ����ƶ�һ�����һ��·�������˾����ĳһ����ô��·�������ٴν���ø��ӡ����磬�������3��4�ľ����а���һ���ַ�����bfce����·����·���е���ĸ�üӴֱ������

 [["a","b","c","e"],
 ["s","f","c","s"],
 ["a","d","e","e"]]

 �������в������ַ�����abfb����·������Ϊ�ַ����ĵ�һ���ַ�bռ���˾����еĵ�һ�еڶ�������֮��·�������ٴν���������ӡ�

 ʾ�� 1��

 ���룺board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 �����true
 ʾ�� 2��

 ���룺board = [["a","b"],["c","d"]], word = "abcd"
 �����false

 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 * Created by Jason on 2020/10/17/0017.
 */
class Solution {
    // ����
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
