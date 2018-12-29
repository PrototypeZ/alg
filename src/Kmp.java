import java.util.Arrays;

public class Kmp {




    private int[] calculateNext(char[] pattern) {
        if (pattern == null) return null;
        int[] next = new int[pattern.length];
        next[0] = -1;
        next[1] = 0;
        int j = 2;
        int k;
        while (j < next.length) {
            k = next[j - 1];
            while (true) {
                if (k == -1 || pattern[j - 1] == pattern[k]) {
                    next[j] = k + 1;
                    break;
                } else {
                    k = next[k];
                }
            }
            j++;
        }
        return next;
    }


    public static void main(String[] args) {
        Kmp kmp = new Kmp();

        int[] next = kmp.calculateNext("abababca".toCharArray());

        System.out.println(Arrays.toString(next));
    }

}
