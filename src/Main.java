public class Main {

    /**
     * 数组的几何意义：
     * a 为数组， i,j 为数组中两个数的下标，i < j
     *
     * i - j 的意义： i 和 j 之间有几个数（包含 i 但是不包含 j 或者包含 j 但是不包含 i）
     *
     * (i + j) / 2 的意义： i 和 j 中间的那个数的下标， 例如下标为 0，3 中间的数下标为 1, 即第二个数
     * 2, 4 中间的数的下标为 3 ，即第四个数。
     *
     *
     * i - j + 1 的意义： i 和 j 之间有几个数，包括 i 和 j 本身
     *
     *
     * 如果 i <= k <= j ， 如果要翻转 i ~ j 中间的所有的数，k 翻转后的位置在 j - (k - i) = j + i - k
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
