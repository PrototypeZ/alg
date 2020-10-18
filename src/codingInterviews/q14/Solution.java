package codingInterviews.q14;

import java.util.HashMap;

/**
 *
 * ����һ������Ϊ n �����ӣ�������Ӽ��� m �Σ�m,n ����������n > 1 ���� m > 1��, ÿ�����ӵĳ��ȼ�Ϊ k[0], k[1], ... k[m]
 * ���� k[0] * k[1] * ... * k[m] ���ܵ����˻��Ƕ��٣����磬�����ӳ����� 8 ʱ�����ǰ������ɳ��ȷֱ�Ϊ 2�� 3�� 3 �����Σ�
 * ��ʱ�õ������˻��� 18.
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
                    // ��Ȼ���Ե���ֵ����(1, n-1), (2, n-2), ... (n-2, 2),(n-1, 1)
                    // ���ǣ����˵����Բ����ȼۣ��� k * check(n-k) �� (n-k) * check(k) ��һ���ȼۣ����Բ����������ѭ��
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
