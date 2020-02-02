package leetcode.q200;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * 输出: 3
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int numIslands(char[][] grid) {
        return 0;
    }

    class Point {
        int col;
        int row;

        public Point(int row, int col) {
            this.col = col;
            this.row = row;
        }
    }

    public int numIslandsBfs(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        boolean[][] traveled = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !traveled[i][j]) {
                    count++;
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(i, j));
                    traveled[i][j] = true;
                    while (!queue.isEmpty()) {
                        Point point = queue.poll();
                        int r = point.row;
                        int c = point.col;
                        System.out.println("current point, row:" + r + ", col:" + c);
                        // top
                        if (r > 0 && grid[r - 1][c] == '1' && !traveled[r - 1][c]) {
                            queue.add(new Point(r - 1, c));
                            traveled[r - 1][c] = true;
                        }
                        // bottom
                        if (r < grid.length - 1 && grid[r + 1][c] == '1' && !traveled[r + 1][c]) {
                            queue.add(new Point(r + 1, c));
                            traveled[r + 1][c] = true;
                        }
                        // left
                        if (c > 0 && grid[r][c - 1] == '1' && !traveled[r][c - 1]) {
                            queue.add(new Point(r, c - 1));
                            traveled[r][c - 1] = true;
                        }
                        // right
                        if (c < grid[i].length - 1 && grid[r][c + 1] == '1' && !traveled[r][c + 1]) {
                            queue.add(new Point(r, c + 1));
                            traveled[r][c + 1] = true;
                        }
                    }
                }
            }
        }
        return count;
    }

    public int numIslandsDfs(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        boolean[][] traveled = new boolean[grid.length][grid[0].length];
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!traveled[i][j] && grid[i][j] == '1') {
                    count++;
                    Stack<DfsStackItem> stack = new Stack<>();
                    stack.push(new DfsStackItem(i, j, DfsStackItem.LEFT));
                    traveled[i][j] = true;
                    while (!stack.isEmpty()) {
                        DfsStackItem stackItem = stack.pop();
                        switch (stackItem.index) {
                            case DfsStackItem.TOP:

                                break;
                            case DfsStackItem.RIGHT:

                                break;
                            case DfsStackItem.BOTTOM:

                                break;
                            case DfsStackItem.LEFT:
                            default:
                                stack.push(new DfsStackItem(i, j,  ));
                                break;
                        }
                    }
                }
            }
        }
        return count;
    }

    private class DfsStackItem {
        public static final int TOP = 0;
        public static final int RIGHT = 1;
        public static final int BOTTOM = 2;
        public static final int LEFT = 3;
        int row;
        int col;
        int index;

        public DfsStackItem(int row, int col, int index) {
            this.index = index;
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = new char[][]{
                new char[]{'1', '1', '1', '1', '0'},
                new char[]{'1', '1', '0', '1', '0'},
                new char[]{'1', '1', '0', '0', '0'},
                new char[]{'0', '0', '0', '0', '0'}
        };
        System.out.println(solution.numIslandsBfs(grid));
        System.out.println(solution.numIslandsDfs(grid));


        // error cases

        char[][] grid2 = new char[][]{
                new char[]{'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1'},
                new char[]{'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
                new char[]{'1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                new char[]{'1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                new char[]{'1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1'},
                new char[]{'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1'},
                new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                new char[]{'0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1'},
                new char[]{'1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0'},
                new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0'},
                new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
        };

        System.out.println(solution.numIslandsBfs(grid2));
        System.out.println(solution.numIslandsDfs(grid2));
    }
}
