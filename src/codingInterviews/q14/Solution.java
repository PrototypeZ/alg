package codingInterviews.q14;

import java.util.HashMap;

/**
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
