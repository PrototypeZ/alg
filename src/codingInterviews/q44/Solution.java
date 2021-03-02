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



    private int numberCountsByDigits(int n){
        // 1 -> 10, 2 -> 90, 3-> 900, 4 -> 9000
        if (n == 1) {
            return 10;
        } else {
            return (int) (9 * Math.pow(10, n - 1)) * n;
        }
    }

    private int firstNumberByDigits(int n) {
        if (n == 1) {
            return 0;
        } else {
            return (int) Math.pow(10, n - 1);
        }
    }

    public int findNthDigit2(int n) {
        int testDigits = 1;
        int sum = 0 ;
        int lastSum;
        do {
            lastSum = sum;
            int counts = numberCountsByDigits(testDigits);
            if (counts <= Integer.MAX_VALUE - sum) {
                sum += counts;
                testDigits ++;
            } else {
                testDigits ++;
                break;
            }
        } while (sum < n + 1);

        testDigits --;

        if (sum == n + 1) {
            return 9;
        } else {
            int numbersBefore = (n + 1 - lastSum) / testDigits;
            int bit = (n + 1 - lastSum) % testDigits;
            int firstNumber = firstNumberByDigits(testDigits);
            if (bit == 0) {
                char[] chars = String.valueOf(firstNumber + numbersBefore - 1).toCharArray();
                return Integer.parseInt(Character.toString(chars[chars.length - 1]));
            } else {
                char[] chars = String.valueOf(firstNumber + numbersBefore).toCharArray();
                return Integer.parseInt(Character.toString(chars[bit - 1]));
            }

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

        System.out.println();

        System.out.println(solution.findNthDigit2(0)); // 0
        System.out.println(solution.findNthDigit2(1)); // 1
        System.out.println(solution.findNthDigit2(3)); // 3
        System.out.println(solution.findNthDigit2(10)); // 1
        System.out.println(solution.findNthDigit2(11)); // 0
        System.out.println(solution.findNthDigit2(12)); // 1
        System.out.println(solution.findNthDigit2(13)); // 1
        System.out.println(solution.findNthDigit2(100)); // 5
        System.out.println(solution.findNthDigit2(1000000000)); // 1
    }
}
