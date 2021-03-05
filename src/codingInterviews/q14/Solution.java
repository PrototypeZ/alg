package codingInterviews.q14;

import java.util.HashMap;

/**
 *
 * 剑指 Offer 14- I. 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 * 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
 *
 * 通过次数86,752提交次数154,678
 * 在真实的面试中遇到过这道题？
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成 m 段（m,n 都是整数，n > 1 并且 m > 1）, 每段绳子的长度记为 k[0], k[1], ... k[m]
 * 请问 k[0] * k[1] * ... * k[m] 可能的最大乘积是多少？例如，当绳子长度是 8 时，我们把它剪成长度分别为 2， 3， 3 的三段，
 * 此时得到的最大乘积是 18.
 * Created by Jason on 2020/10/18/0018.
 */
public class Solution {

    HashMap<Integer, Integer> cache = new HashMap<>();


    public int checkInternal(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2){
            return 2;
        } else {
            Integer val = cache.get(n);
            if (val != null) {
                return val;
            } else {
                int max = 1;
                for (int i = 1; i <= n - 1; i++) {
                    // 虽然测试的数值对是(1, n-1), (2, n-2), ... (n-2, 2),(n-1, 1)
                    // 但是，两端的两对并不等价，即 k * check(n-k) 和 (n-k) * check(k) 不一定等价，所以不能用下面的循环
//                for (int i = 1; i <= n / 2; i++) {
                    int test = i * checkInternal(n - i);
                    if (test > max) {
                        max = test;
                    }
                }
                cache.put(n, max);
                return max;
            }
        }
    }

    public int check(int n) {
        if (n == 2) {
            return 1;
        } else {
            return checkInternal(n);
        }
    }

    /**
     * 以下数学推导总体分为两步：
     * ① 当所有绳段长度相等时，乘积最大。
     * ② 最优的绳段长度为 3 。
     *
     * 为什么 4 不行？ 4 可以分解为 2 * 2 = 4
     * 为什么 5 不行？ 5 可以分解为 2 * 3 = 6
     * 为什么 6 不行？ 6 可以分解为 3 * 3 = 9
     * 为什么 7 不行？ 7 可以分解为 3 * 2 * 2 = 12
     * …………
     * 更大的数就更不行了
     *
     * 所以尽可能把线段以 3 为长度进行切割，
     * 切割到最后剩 1，那么最后一段 3 不要切割，和剩下的 1 组成 4 切割为 2 * 2
     * 切割到最后剩 2，那最好，那么结果就是由一堆 3 和剩下的这个 2 组成
     * 最后正好切割完，那很完美
     *
     *
     * @param n
     * @return
     */
    public int checkGreedy(int n) {
        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        } else if (n == 4) {
            return 4;
        } else {
            int timesOf3 = n / 3;
            if (n - timesOf3 * 3 == 1) {
                timesOf3 --;
                return (int) (Math.pow(3, timesOf3) * 4);
            } else if (n - timesOf3 * 3 == 2) {
                return (int) (Math.pow(3, timesOf3) * 2);
            } else {
                return (int) Math.pow(3, timesOf3);
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.check(8)); //18
        System.out.println(solution.checkGreedy(8)); //18
    }
}
