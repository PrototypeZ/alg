package dp.problem1024;

import java.util.*;

/**
 * 从 100 到 200 共 101 个 数字中找顺序取 10 个数，每个数可以重复取，使 10 个数的总和为 1024，求总共有多少种选择。
 *
 *
 * 参考答案：38567100
 */
public class Solution {


    public int calculate(int end, int depth, List<Integer> path, int current, int target, Map<String, Integer> cache) {
        Integer value = cache.get(current + "#" +  depth + "#" + target);
        if (value != null) {
            System.out.println(path + ", current:" + current + ", depth:" + depth + ", target:" + target + ", value:" + value);
            return value;
        }
        if (depth == 0) {
            if (target == 0) {
                System.out.println(path);
                return 1;
            } else {
//                System.out.println("failed:" + path);
                return 0;
            }
        } else {
            // depth > 0
            // divideResult 表示剩下 target 由 depth 个数组成的话，这些数平均大小是多少，
            // 如果 current 比这个平均大小都要大的话，就不需要再测试了
            int divideResult = target / depth;
            int v = 0;
            for (int i = current; i <= end && i <= divideResult; i++) {
                List<Integer> newList = new ArrayList<>(path);
                newList.add(i);
                v += calculate(end, depth - 1, newList, i, target - i, cache);
            }
            cache.put(current + "#" + depth + "#" + target, v);
            return v;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Map<String, Integer> cache = new HashMap<>();
        System.out.println(solution.calculate(124, 10, new ArrayList<>(), 100, 1024, cache));
    }
}
