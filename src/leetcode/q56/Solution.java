package leetcode.q56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class Solution {

    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        int[][] copy = Arrays.stream(intervals)
                .map(ints -> Arrays.copyOf(ints, ints.length)).toArray(int[][]::new);

        Arrays.sort(copy, Comparator.comparingInt(ints -> ints[0]));

        for (int i = 0; i < copy.length; i++) {
            if (result.size() == 0) {
                result.add(copy[i]);
            } else {
                int[] last = result.get(result.size() - 1);
                int[] current = copy[i];
                if (current[0] <= last[1] && current[1] > last[1]) {
                    last[1] = current[1];
                } else if (current[0] > last[1]) {
                    result.add(current);
                }
            }
        }
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.merge(new int[][]{
                new int[]{1,2}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}
        }))); // [[1,6],[8,10],[15,18]]

        System.out.println(Arrays.deepToString(solution.merge(new int[][]{
                new int[]{1,4}, new int[]{4, 5}
        }))); // [[1,5]]

        System.out.println(Arrays.deepToString(solution.merge(new int[][]{
                new int[]{1,4}, new int[]{1, 4}
        }))); // [[1,4]]

        System.out.println(Arrays.deepToString(solution.merge(new int[][]{
                new int[]{1,4}, new int[]{2, 3}
        }))); // [[1,4]]
    }
}
