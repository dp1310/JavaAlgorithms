package Code.Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * This class is to solve Harshvardhan's problems (programming only ;) )
 *
 * @author: Ashok Rajpurohit (ashok1113@gmail.com)
 */
public class Harsh {
    private static Output out;
    private static InputReader in;

    public static void main(String[] args) throws IOException {
        in = new InputReader(System.in);
        out = new Output(System.out);

        Harsh a = new Harsh();
        a.solve();
        out.close();
    }

    private void solve() throws IOException {
        String s = in.read();
        int q = in.readInt();
        int mid = ('a' + 'z') >>> 1;
        long[] hash = new long[256];
        for (int i = 0; i < s.length(); ) {
            char ch = s.charAt(i);
            long count = 0;
            i++;
            while (i < s.length() && s.charAt(i) >= '0' &&
                   s.charAt(i) <= '9') {
                count = (count << 3) + (count << 1) + s.charAt(i) - '0';
                i++;
            }
            hash[ch] += count;
        }

        for (int i = 'b'; i <= 'z'; i++)
            hash[i] += hash[i - 1];

        StringBuilder sb = new StringBuilder(q << 1);
        for (int i = 0; i < q; i++) {
            long k = in.readLong();
            char ch = 'a';

            if (k <= hash[mid]) {
                int j = 'a';
                while (hash[j] < k)
                    j++;

                sb.append((char)j).append('\n');
            } else if (k > hash['z'])
                sb.append("-1\n");
            else {
                int j = mid;
                while (hash[j] < k)
                    j++;

                sb.append((char)j).append('\n');
            }
        }

        out.print(sb);
    }

    private static void functionOne(int n) {
        for (int i = 0; i < n << 1; i++)
            System.out.print((i > n ? (n << 1) - i : i) + " ");

        System.out.println();
    }

    private static void functionTwo(int n) {
        for (int i = 0; i < n << 1; i++)
            System.out.print((n - (int)Math.sqrt((n - i) * (n - i))) + " ");

        System.out.println();
    }

    final static class solveHarsh {
        int maxValue = 0, maxLength = 0;
        int[] ar;

        public solveHarsh(int[] ar) {
            this.ar = ar;
        }

        public void calculate() {
            maxLength = 1;
            for (int i = 0; i < ar.length; i++) {
                maxValue = Math.max(maxValue, ar[i]);
                solve(ar[i], ar[i], i + 1, 1);
            }
        }

        private void solve(int xorSoFar, int andSoFar, int index, int count) {
            if (index == ar.length)
                return;

            int tand = andSoFar, txor = xorSoFar;

            for (int i = index; i < ar.length; i++) {
                tand = andSoFar & ar[i];
                txor = xorSoFar ^ ar[i];

                int temp = tand | txor;
                if (temp > maxValue) {
                    maxValue = temp;
                    maxLength = count + 1;
                } else if (temp == maxValue && count >= maxLength) {
                    maxLength = count + 1;
                }

                solve(txor, tand, i + 1, count + 1);
            }
        }
    }
}
