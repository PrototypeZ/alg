package unclassified.checkOdd;

/**
 *
 * -2 % 2 // 0
 * -1 % 2 // -1
 * -3 % 2 // -1
 * -4 % 2 // 0
 *
 *
 *
 * Created by Jason on 2020/10/17/0017.
 */
public class Solution {

    public boolean isOdd(int i) {
        return i % 2 != 0;


        // 无论是正数还是负数，奇数最后一位都是 1
        // 如果一个奇数先右移一位再左移一位，得到的这个数，和移位前一样，说明这个数最右位是0，说明是一个偶数
//        return i >> 1 << 1 != i;


        // 无论是正数还是负数，奇数最后一位都是 1
//        return (i & 1) == 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.isOdd(1));
        System.out.println(solution.isOdd(-1));
        System.out.println(solution.isOdd(2));
        System.out.println(solution.isOdd(-2));
    }
}
