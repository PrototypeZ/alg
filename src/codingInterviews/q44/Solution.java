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

    private int numberCountsByDigits(int n){
        // 1 -> 10, 2 -> 90*2, 3-> 900*3, 4 -> 9000*4
        if (n == 1) {
            return 10;
        } else if (n > 8) {
            return Integer.MAX_VALUE;
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


    /**
     * 解法 2：
     *
     * 由于 n 从 0 开始算，那么第 n 个数，其实是第 n + 1 个数。
     * 假设第 n + 1 个数落在 1 位数的范围内，那么 n + 1 <= 所有 1 位数的位数总和
     * 假设第 n + 1 个数落在 2 位数的范围内，那么 n + 1 <= 所有 1 位数的位数总和 + 所有 2 位数的位数总和
     * 假设第 n + 1 个数落在 3 位数的范围内，那么 n + 1 <= 所有 1 位数的位数总和 + 所有 2 位数的位数总和 + 所有 3 位数的位数总和
     *
     * @param n 显然 n 的取值范围是 [0, Integer.MAX_VALUE]，所以要特别注意 n 的溢出
     * @return
     */
    public int findNthDigit2(int n) {
        // 因为 n 可能取值到 Integer.MAX_VALUE, 所以 n + 1 可能溢出，所以用 nCopy 代替 count
        int nCopy = n;
//        int count = n + 1;
        int digits = 1;
//        while (count >= 0) {
        while (nCopy >= -1) {
//            count -= numberCountsByDigits(digits);
            nCopy -= numberCountsByDigits(digits);
//            if (count == 0) return 9;
            if (nCopy == -1) return 9;
            digits++;
        }
        // below count < 0
        digits--;
        // 在 digits 位数里，第一个数字。例如两位数的第一个数是10，三位数的第一个数是 100
        int firstNumInDigits = firstNumberByDigits(digits);
        // 要求的第 count 位数，在 digits 位数，所组合而成的数字流中，是第几个数
//        int positionInCurrentDigitsNumber = numberCountsByDigits(digits) + count;
        int positionInCurrentDigitsNumber = numberCountsByDigits(digits) + nCopy + 1;

        int bits = positionInCurrentDigitsNumber % digits;
        int number;
        if (bits == 0) {
            number = firstNumInDigits + positionInCurrentDigitsNumber / digits - 1;
            return Integer.parseInt(Character.toString(String.valueOf(number).toCharArray()[digits - 1]));
        } else {
            number = firstNumInDigits + positionInCurrentDigitsNumber / digits;
            return Integer.parseInt(Character.toString(String.valueOf(number).toCharArray()[bits - 1]));
        }
    }

    /**
     * 解法 1：
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        boolean nIsMaxValue = false;
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        } else if (n == Integer.MAX_VALUE) {
            n = n - 1;
            nIsMaxValue = true;
        }


        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(1, numberCountsByDigits(1));
        int i = 2;
        int value;
        int valueMaxThreshold;
        int sum = cache.get(1);
        do {
            value = numberCountsByDigits(i);
            // 通过 numberCountsByDigits 这个方法计算出的数组，从 2 开始后面的元素与前面的元素都是有规律的，1 特殊一点
            // 也就是说从 2 开始，已知前一个，可以推出后一个，假如我已知前一个，而且这个数还没溢出，我就可以判断下一个数有没有溢出
            // 方法就是我假设下一个是 Integer.MAX_VALUE，以此推出当前最大不能超过多少，然后实际看一下是否超出，
            // 超出的话，说明下一个肯定要溢出了，没超出说明下一个不会溢出
            valueMaxThreshold = (int)(Integer.MAX_VALUE / (10.0d * (i + 1)) * i);
            // 除了判断下一个算出的数有没有可能溢出，
            boolean nextValueWillOverflow = false;
            if (value > valueMaxThreshold ) {
                nextValueWillOverflow = true;
            }

            // 还要判断，当前算出的结果与之前算出的所有结果累加是否也溢出
            if (value <= Integer.MAX_VALUE - sum) {
                sum += value;
                cache.put(i, sum);
                if (nextValueWillOverflow) {
                    // 虽然当前的 sum 没有溢出，但是下个 value 要溢出，所以下一轮循环没必要进行了
                    break;
                } else {
                    // 当前 sum 没溢出，下个 value 也不会溢出，可以进行下一轮测试
                    i++;
                }
            } else {
                // 当前的 sum 已经要溢出了，所以不算了，退出循环
                break;
            }
        } while (true);

        int testDigits = 1;

        while (cache.get(testDigits) != null && cache.get(testDigits) <= n + 1) {
            if (cache.get(testDigits) == n + 1) {
                return 9;
            }
            testDigits++;
        }

        int lastSum = testDigits > 1 ? cache.get(testDigits - 1) : 0;

        int numbersBefore = (n + 1 - lastSum) / testDigits;
        int bit = (n + 1 - lastSum) % testDigits;
        int firstNumber = firstNumberByDigits(testDigits);
        if (bit == 0) {
            if (nIsMaxValue) {
                char[] chars = String.valueOf(firstNumber + numbersBefore).toCharArray();
                return Integer.parseInt(Character.toString(chars[0]));
            } else {
                char[] chars = String.valueOf(firstNumber + numbersBefore - 1).toCharArray();
                return Integer.parseInt(Character.toString(chars[chars.length - 1]));
            }
        } else {
            if (nIsMaxValue) {
                char[] chars = String.valueOf(firstNumber + numbersBefore).toCharArray();
                return Integer.parseInt(Character.toString(chars[bit]));
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
        System.out.println(solution.findNthDigit(100)); // 5
        System.out.println(solution.findNthDigit(1000000000)); // 1
        System.out.println(solution.findNthDigit(2147483647)); // 2

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
        System.out.println(solution.findNthDigit2(2147483647)); // 2
    }
}
