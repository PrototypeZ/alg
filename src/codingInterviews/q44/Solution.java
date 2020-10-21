package codingInterviews.q44;

import java.util.HashMap;
import java.util.Map;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    // todo solution not ok
    public int findNthDigit(int n) {
        if (n == 0) return 0;
        if (9 >= n && n > 0) return n;
        int bit = 2;
        int accumulated = 10;
        while (n + 1 > accumulated) {
            accumulated += pow10(bit - 1) * 9 * bit;
            bit++;
        }
        bit = bit - 1;
        int lastAccumulated = accumulated - (pow10( bit - 1) * 9 * bit);
        int theNNumberOfBit = (n + 1 - lastAccumulated) / bit;
        int left = (n + 1 - lastAccumulated) % bit;
        int theStartNumOfBit = pow10(bit - 1);
        if (left == 0) {
            String s = String.valueOf(theNNumberOfBit + theStartNumOfBit - 1);
            return s.charAt(s.length() - 1) - '0';
        } else {
            return String.valueOf(theNNumberOfBit + theStartNumOfBit).charAt((n + 1 - lastAccumulated) % bit - 1) - '0';
        }
    }

    private Map<Integer, Integer> powCache = new HashMap<>();

    private int pow10(int e) {
        if (powCache.containsKey(e)) {
            return powCache.get(e);
        } else if (e == 0) {
            return 1;
        } else {
            int result =  pow10(e / 2) * pow10(e / 2) * (e % 2 == 0 ? 1 : 10);
            powCache.put(e, result);
            return result;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findNthDigit(0)); // 0
        System.out.println(solution.findNthDigit(1)); // 1
        System.out.println(solution.findNthDigit(3)); // 3
        System.out.println(solution.findNthDigit(10)); // 1
        System.out.println(solution.findNthDigit(11)); // 0
        System.out.println(solution.findNthDigit(12)); // 1
        System.out.println(solution.findNthDigit(13)); // 1
        System.out.println(solution.findNthDigit(1000000000));
    }
}
