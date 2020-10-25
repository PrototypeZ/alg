package codingInterviews.q13;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？

 ?

 示例 1：

 输入：m = 2, n = 3, k = 1
 输出：3
 示例 2：

 输入：m = 3, n = 1, k = 0
 输出：1
 提示：

 1 <= n,m <= 100
 0 <= k?<= 20

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by Jason on 2020/10/24/0024.
 */
public class Solution {
    public int movingCount(int m, int n, int k) {
        if (m <=0 || n <= 0) return 0;
        if (k < 0) return 0;
        boolean[][] path = new boolean[m][n];
        return testMove(0, 0, k, path);
    }

    private int testMove(int testM, int testN, int k, boolean[][] path) {
        path[testM][testN] = true;
        int sum = 1;
        if (testM + 1 < path.length && !path[testM + 1][testN]) {
            if (calculateAllBits(testM + 1) + calculateAllBits(testN) > k) {
                path[testM + 1][testN] = true;
            } else {
                sum += testMove(testM + 1, testN, k, path);
            }
        }
        if (testM - 1 >= 0 && !path[testM - 1][testN]) {
            if (calculateAllBits(testM - 1) + calculateAllBits(testN) > k) {
                path[testM - 1][testN] = true;
            } else {
                sum += testMove(testM - 1, testN, k, path);
            }
        }
        if (testN + 1 < path[testM].length && !path[testM][testN + 1]) {
            if (calculateAllBits(testM) + calculateAllBits(testN + 1) > k) {
                path[testM][testN + 1] = true;
            } else {
                sum += testMove(testM, testN + 1, k, path);
            }
        }

        if (testN - 1 >= 0 && !path[testM][testN - 1]) {
            if (calculateAllBits(testM) + calculateAllBits(testN - 1) > k) {
                path[testM][testN - 1] = true;
            } else {
                sum += testMove(testM, testN - 1, k, path);
            }
        }

        return sum;
    }

    private int calculateAllBits(int nums) {
        int data = nums;
        int sum = 0;
        while(data > 0) {
            sum += data % 10;
            data = data / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.calculateAllBits(3601));
        System.out.println(solution.movingCount(16, 8, 4)); // 15
    }
}
