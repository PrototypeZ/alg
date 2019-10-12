package leetcode.tencent.autumn2018.q7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2019/2/8/0008.
 */
public class Solution {
    public int reverse2(int x) {
        List<Integer> storage = new ArrayList<>();

        int currentValue = Math.abs(x);
        int operator = 10;
        int modValue = 1;
        do {
            modValue = currentValue % operator / (operator / 10);
            currentValue = currentValue - modValue;
            operator *= 10;
            storage.add(modValue);
        } while (currentValue > 0);

        String maxValueInString = Integer.toString(Integer.MAX_VALUE);
        StringBuilder sb = new StringBuilder();
        storage.stream().forEach(sb::append);

        int value;
        if (storage.size() < maxValueInString.length()) {
            value = Integer.parseInt(sb.toString());
        } else if (storage.size() == maxValueInString.length()) {
            if (sb.toString().compareTo(maxValueInString) > 0) {
                value = 0;
            } else {
                value = Integer.parseInt(sb.toString());
            }
        } else {
            value = 0;
        }
        return x > 0 ? value : - value;
    }

    /**
     *
     * 已知两个基本操作
     * //pop operation:
     * pop = x % 10;
     * x /= 10;
     * <p>
     * //push operation:
     * temp = rev * 10 + pop;
     * rev = temp;
     *
     * Integer.MAX_VALUE: 2147483647
     *
     *
     *
     */
    public int reverse(int x) {
        int copyX = Math.abs(x);
        int pop;
        int value = 0;

        while (copyX > 0) {
            pop = copyX % 10;
            copyX /= 10;
            if (value < Integer.MAX_VALUE / 10) {
                value = value * 10 + pop;
            } else if (value == Integer.MAX_VALUE / 10) {
                if (pop > Integer.MAX_VALUE % 10) {
                    value = 0;
                } else {
                    value = value * 10 + pop;
                }
            } else {
                value = 0;
            }
        }

        return x > 0 ? value : -value;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("123 = [" + solution.reverse(123) + "]");
        System.out.println("-123 = [" + solution.reverse(-123) + "]");
        System.out.println("120 = [" + solution.reverse(120) + "]");

        System.out.println("123 = [" + solution.reverse2(123) + "]");
        System.out.println("-123 = [" + solution.reverse2(-123) + "]");
        System.out.println("120 = [" + solution.reverse2(120) + "]");
    }
}
