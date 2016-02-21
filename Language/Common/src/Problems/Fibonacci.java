package Problems;

import Code.Algorithms.Matrix;

import Code.DSA.Main;

import Code.Main.InputReader;
import Code.Main.Output;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Problem Name: Fibonacci Series
 * Link: Standard Problem
 *
 * @author: Ashok Rajpurohit (ashok1113@gmail.com)
 */
public class Fibonacci {
    private static Output out;
    private static InputReader in;
    private static long[][] matrix = new long[2][];
    private static Matrix base;
    private final static int iterLimit = 67, zero = 1;
    private final static String line =
        "---------------------------------------------------------";

    static {
        matrix[0] = new long[] { 1, 1 };
        matrix[1] = new long[] { 1, 0 };
        base = new Matrix(matrix);
    }

    private static void main(String[] args) throws IOException {
        in = new InputReader(System.in);
        out = new Output(System.out);

        solve();
        out.close();
    }

    private static void solve() throws IOException {
        int mod = 1000000007;
        int sum = 0;
        for (int i = 1; i < 21; i++) {
            sum += termIter(i);
            out.print(sum + " ");
        }
        out.println();
        out.flush();

        while (true) {
            int n = in.readInt(), size = in.readInt();
            int[] ar = Main.gen_rand(n, size);

            long[] iter = new long[n];
            for (int i = 0; i < n; i++)
                iter[i] = termIter(ar[i], mod);

            long[] mat = new long[n];
            for (int i = 0; i < n; i++)
                mat[i] = termMatrix(ar[i], mod);

            out.println(check(iter, mat));
            out.println(line);

            out.flush();
        }
    }

    private static boolean check(long[] a, long[] b) {
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i])
                return false;

        return true;
    }

    public static long term(long n) {
        if (n == 0)
            return zero;

        if (n < iterLimit)
            return termIter(n);

        return termMatrix(n);
    }

    public static long term(long n, long mod) {
        if (n == 0)
            return zero;

        if (n < iterLimit)
            return termIter(n, mod);

        return termMatrix(n, mod);
    }

    private static long termMatrix(long n) {
        Matrix res = Matrix.pow(base, n - 1);
        return res.get(0, 0);
    }

    private static long termMatrix(long n, long mod) {
        Matrix res = Matrix.pow(base, n - 1, mod);
        return res.get(0, 0);
    }

    private static long termIter(long n) {
        long a = 1, b = 1;

        while (n > 2) {
            n--;
            long c = a;
            a = a + b;
            b = c;
        }

        return a;
    }

    private static long termIter(long n, long mod) {
        if (n == 0)
            return zero;

        long a = 1, b = 1;
        while (n > 2) {
            n--;
            long c = a;
            a = (a + b) % mod;
            b = c;
        }

        return a % mod;
    }
}
