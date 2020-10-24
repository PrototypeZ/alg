package unclassified.findN;

import java.util.Scanner;

/**
 * Created by Jason on 2020/10/21/0021.
 */
public class Solution {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] parts = line.split(",");
        int sum = (parts.length) * (parts.length + 1) / 2;
        for (int i = 0; i < parts.length; i++) {
            sum -= Integer.parseInt(parts[i]);
        }
        System.out.println(sum);
    }

    protected interface A {

    }

    interface B extends A {

    }
}
