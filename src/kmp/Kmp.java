import java.util.Arrays;

public class Kmp {


    /**
     * 前置知识点： 如果 next[j] = k，可知：
     * 1. next 数组的下标为 0 到 k-1 对应的 k 个元素分别与
     * 数组下标为 j-k 到 j-1 对应的 k 个元素相等。
     * <p>
     * 2. 也就是说对于 next 数组的子数组 0 到 j-1 这个数组，
     * 它的前 k 位与后 k 位对应元素都相等。
     * <p>
     * 3. next 数组下标为 j 和 k 对应的这两个数组元素都不在
     * 上面提到的相等的两个序列里，且都是那两个相等的序列的最后一个
     * 元素的后一位。
     * <p>
     * 4. next 数组已知 next[0] = -1, next[1] = 0。
     * <p>
     * 5. next[0] = -1, 其实本质上是一个标志位。我们在通过 next[j-1] 的值 k'
     * 推导 next[j] 的值 k 的过程中， 发现我们的 k 值在不断尝试的情况下，到达了
     * next 数组的第 0 位，而 next[0] = -1, 我们看到这个 -1 就知道已经尝试到
     * next[0] 了， 而且第 0 位对应的元素还是和待搜索串比较后还是不相等，
     * 所以我们应该停止尝试，把 next[j] 的值置为 0， 同时待搜索串的指针向后移动一位。
     * 这一点可以通过算法中的循环逻辑来体会
     * <p>
     * 6. 若已知： next[j] == j', next[j'] == j'', next[j''] == j''', ...以次类推，
     * 则可以得出结论，在 [0, j-1] 这个数组中，
     * <p>
     * 6.1. 选取长度为 j' 的前缀和后缀，它们每个元素都相等
     * 6.2. 选取长度为 j'' 的前缀和后缀，它们每个元素都相等
     * 6.3. 选取长度为 j''' 的前缀和后缀，它们每个元素都相等
     * ... 以此类推
     * <p>
     * <p>
     * <p>
     * 写法一：
     * <p>
     * 在已知 next 数组 next[j-1] = k, （j > 1） 的 情况下，去推导出 next[j] 的值,
     * 即从 j = 2 开始推导。
     */
    private int[] calculateNext(char[] pattern) {
        if (pattern == null) return null;
        int[] next = new int[pattern.length];
        next[0] = -1;
        next[1] = 0;
        // 初始化 j 的值，表示从这一位开始依次往后推算剩余 next 数组的值
        // k 也要初始化，初始化的依据是 j 的前一位 j-1 对应的 next[j-1] 的值
        // 因为 next 数组的每一位都是根据它的前一位推导而来。
        int j = 2;
        int k = next[j - 1];
        // 开始推导
        while (j < next.length) {
            if (pattern[j - 1] == pattern[k]) {
                // 见分析图
                next[j] = k + 1;
                // next[j] 已经推导出来了，所以接下来要推导下一位 next[j+1] 了
                // 把 k 置为此时的 j 对应的 next[j], 同时把 j 指针往后移动一位，
                // 参考循环开始的时候 j， k 表示的意义，每算出一个 j 对应的 k，
                // 都要保证 j， k 的意义和开始的时候一样。
                k = k + 1;
                j++;
            } else if (k == 0) {
                // 尝试 k 的可能值的过程已经到 k=0 这个位置了，停止尝试，
                // 把next[j] 的值置为 0
                next[j] = 0;
                // 此时 k 的值就是 next[j]，即 k == 0，所以 k 不动，j 向后移动一位
                j++;
            } else {
                // 见分析图
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 写法二：
     * From: https://www.zhihu.com/question/21923021
     */
    private int[] calculateNextZhihu(char[] pattern) {
        if (pattern == null) return null;
        int[] next = new int[pattern.length];
        next[0] = -1;

        int i = 0, j = -1;

        while (i < pattern.length - 1) {
            if (j == -1 || pattern[i] == pattern[j]) {
                ++i;
                ++j;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }


    private int kmpFind(String text, String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            return -1;
        }
        char[] textCharArr = text.toCharArray();
        char[] patternCharArr = pattern.toCharArray();

        int[] next =
//                calculateNextZhihu(pattern.toCharArray());
                calculateNext(pattern.toCharArray());

        System.out.println(Arrays.toString(next));

        int i = 0;
        int j = 0;
        while (i < textCharArr.length && j < patternCharArr.length) {
            if (textCharArr[i] == patternCharArr[j]) {
                i++;
                j++;
            } else {
                j = next[j];
                if (j == -1) {
                    i++;
                    j = 0;
                }
            }
        }

        if (j == patternCharArr.length) {
            // Found it!
            return i - j;
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        Kmp kmp = new Kmp();

        String text = "asdfzvkjhsdnfsdfnlsdjoiuernsmdfnlkja;";

        int index;
        index = kmp.kmpFind(text, "kja;");
        System.out.println("index is:" + index);

        index = kmp.kmpFind(text, "asdf");
        System.out.println("index is:" + index);

        index = kmp.kmpFind(text, "dnf");
        System.out.println("index is:" + index);

    }

}
