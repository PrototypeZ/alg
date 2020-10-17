package unclassified.checkOdd;

/**
 * Created by Jason on 2020/10/17/0017.
 */
public class Solution {

    public boolean isOdd(int i) {
        return i % 2 != 0;
//        return i >> 1 << 1 != i;
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
