package dp.lcs;

/**
 * 参考资料： https://blog.csdn.net/u010397369/article/details/38979077
 *
 * 扩展, 最长公共子序列： https://www.kancloud.cn/digest/pieces-algorithm/163624
 */
public class LongestCommonSubString {

    private int calculateLcs(char[] s1, char[] s2) {
        if (s1 == null || s1.length == 0 || s2 == null || s2.length == 0) {
            return 0;
        }

        // s1 为列， s2 为行
        int[][] resultMatrix = new int[s1.length][s2.length];

        int currentLongestSubStringLength = 0;
        for (int i = 0; i < s1.length; i++) {
            for (int j = 0; j < s2.length; j++) {
                // 相等的情况才需要考虑，不相等的地方矩阵对应元素维持 0
                if (s1[i] == s2[j]) {
                    if (i == 0 || j == 0) {
                        resultMatrix[i][j] = 1;
                    } else {
                        resultMatrix[i][j] = resultMatrix[i-1][j-1] + 1;
                        if (resultMatrix[i][j] > currentLongestSubStringLength) {
                            currentLongestSubStringLength = resultMatrix[i][j];
                        }
                    }
                }
            }
        }
        return currentLongestSubStringLength;
    }


    /**
     * 比原先的方法节省空间的一种做法。
     *
     * 原先方法中存在下面这一行代码：
     * resultMatrix[i][j] = resultMatrix[i-1][j-1] + 1;
     *
     * 也就是说针对最外层 i 对应的 for 循环的的 s1.length 次迭代进行过程中，
     * 对于每个 i ，这次的迭代只可能关心第 i - 1 次的结果（i >= 2），
     * 但是二位数组 resultMatrix 存储了前面每一次迭代的结果，这里存在空间的浪费，
     * 是可以优化的点。
     *
     */
    private int calculateLcs2(char[] s1, char[] s2) {
        if (s1 == null || s1.length == 0 || s2 == null || s2.length == 0) {
            return 0;
        }

        // 确保 s1 始终是比较长的那一行
        if (s1.length < s2.length) {
            return calculateLcs2(s2, s1);
        }
        // s1 为列， s2 为行，只保存矩阵中比较时上一行的结果
        int[] lastRow = new int[s2.length];

        int currentLongestSubStringLength = 0;
        for (int i = 0; i < s1.length; i++) {
            int [] currentRow = new int[s2.length];
            for (int j = 0; j < s2.length; j++) {
                // 相等的情况才需要考虑，不相等的地方矩阵对应元素维持 0
                if (s1[i] == s2[j]) {
                    if (i == 0 || j == 0) {
                        currentRow[j] = 1;
                    } else {
                        currentRow[j] = lastRow[j-1] + 1;
                        if (currentRow[j] > currentLongestSubStringLength) {
                            currentLongestSubStringLength = currentRow[j];
                        }
                    }
                }
            }
            lastRow = currentRow;
        }
        return currentLongestSubStringLength;
    }


    public static void main(String[] args) {
        LongestCommonSubString solution = new LongestCommonSubString();
        int length = solution.calculateLcs(
                "acbcbcef".toCharArray(),
                "abcbced".toCharArray()
        );
        System.out.println("length = [" + length + "]");

        length = solution.calculateLcs2(
                "acbcbcef".toCharArray(),
                "abcbced".toCharArray()
        );
        System.out.println("length = [" + length + "]");
    }
}
