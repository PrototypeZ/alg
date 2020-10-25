package codingInterviews.q13;

/**
 * ������һ��m��n�еķ��񣬴����� [0,0] ������ [m-1,n-1] ��һ�������˴����� [0, 0] �ĸ��ӿ�ʼ�ƶ���
 * ��ÿ�ο��������ҡ��ϡ����ƶ�һ�񣨲����ƶ��������⣩��Ҳ���ܽ�������������������λ֮�ʹ���k�ĸ��ӡ�
 * ���磬��kΪ18ʱ���������ܹ����뷽�� [35, 37] ����Ϊ3+5+3+7=18���������ܽ��뷽�� [35, 38]����Ϊ3+5+3+8=19��
 * ���ʸû������ܹ�������ٸ����ӣ�

 ?

 ʾ�� 1��

 ���룺m = 2, n = 3, k = 1
 �����3
 ʾ�� 2��

 ���룺m = 3, n = 1, k = 0
 �����1
 ��ʾ��

 1 <= n,m <= 100
 0 <= k?<= 20

 ��Դ�����ۣ�LeetCode��
 ���ӣ�https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
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
