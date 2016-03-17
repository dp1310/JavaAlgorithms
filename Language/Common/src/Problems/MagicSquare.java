package Problems;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Problem: Magic Squares
 * Standard Problem
 *
 * Given a set of numbers, find how many ways there
 * are to build the Magic Square using these numbers
 *
 * @author: Ashok Rajpurohit (ashok1113@gmail.com)
 */

public class MagicSquare {
    private static int[] matrix;
    private static Integer[] numbers, freq;
    private static int sum, count;
    private static int n, len;

    public static int solve(int[] ar) {
        len = ar.length;
        n = (int)Math.sqrt(ar.length);
        if (n * n != ar.length) {
            System.out.println("Failed here");
            return 0;
        }

        int total = 0;
        for (int i = 0; i < ar.length; i++)
            total += ar[i];

        if (total % n != 0) {
            System.out.println("Failed there");
            return 0;
        }

        sum = total / n;

        populate(ar);

        return process();
    }

    private static int process() {
        count = 0;
        for (int i = 0; i < numbers.length; i++) {
            matrix[0] = numbers[i];
            freq[i]--;
            permute(1);
            freq[i]++;
        }

        return count;
    }

    private static void permute(int index) {
        if (!checkRow(index))
            return;

        if (index == len) {
            if (check()) {
                count++;
                System.out.println(print());
            }

            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (freq[i] > 0) {
                matrix[index] = numbers[i];
                freq[i]--;
                permute(index + 1);
                freq[i]++;
            }
        }
    }

    private static void populate(int[] ar) {
        Arrays.sort(ar);
        LinkedList<Integer> uniks = new LinkedList<Integer>(), fr =
            new LinkedList<Integer>();

        uniks.add(ar[0]);
        int temp = ar[0];
        for (int i = 0; i < n; ) {
            int count = 0;
            while (i < n && ar[i] == temp) {
                i++;
                count++;
            }

            fr.add(count);
            if (i < n) {
                temp = ar[i];
                uniks.add(temp);
            }
        }

        numbers = new Integer[uniks.size()];
        freq = new Integer[uniks.size()];

        matrix = new int[ar.length];
        numbers = uniks.toArray(numbers);
        freq = fr.toArray(freq);
    }

    private static boolean checkRow(int index) {
        if (index % n != 0)
            return true;

        long local = 0;
        index -= n;
        for (int i = 0; i < n; i++, index++)
            local += matrix[index];

        return local == sum;
    }

    private static boolean check() {
        for (int i = 0; i < n; i++)
            if (!checkCol(i))
                return false;

        int temp = 0;
        for (int i = 0; i < matrix.length; i += n + 1)
            temp += matrix[i];

        if (temp != sum)
            return false;

        temp = 0;
        for (int i = n - 1; i < matrix.length; i += n - 1)
            temp += matrix[i];

        return temp == sum;
    }

    private static boolean checkCol(int col) {
        int temp = 0;

        for (int i = col; i < matrix.length; i += n)
            temp += matrix[i];

        return temp == sum;
    }

    private static String print() {
        StringBuilder sb = new StringBuilder(matrix.length << 2);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(matrix[n * i + j]).append(' ');

            sb.append('\n');
        }

        return sb.toString();
    }
}
