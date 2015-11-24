package Problems;

import Code.Algorithms.Matrix;

import Code.DSA.*;

import Code.Main.InputReader;
import Code.Main.Output;

import java.io.IOException;

/**
 * Problem Name: Moves in Tower of Hanoi Problem.
 *
 * How many moves you need to perform in order to move n disk from one
 * needle to another using a third needle (intermediate needle).
 *
 * We have three needles A, B and C. Currently all the disks are on
 * needle A. We have to move these disks to needle B using C.
 * Let's say we number of moves needed to move n elements is F(n).
 * Think how can we move n elements effectively from needle A to B.
 *
 * 1. We move all but last (n - 1) disks to needle C.
 * 2. We move the last disk on A to B.
 * 3. We move all the disks on C to B using the same procedure used in step 1.
 *
 * Formulating these steps, moves needed for n disks
 *      F(n) = F(n - 1) + 1 + F(n - 1)
 *      F(n) = 2 * F(n - 1) + 1
 *
 * Now we know the recursive formula for n.
 * There are three ways to implement it.
 *
 * 1. Calling function recursively.
 * 2. Using Matrix Exponentiation.
 * 3. Simplifying the formula using recurring series
 *
 * 1. For small value of n first method (Recursive) is more efficient as there
 * are extra multiplications in Matrix Multiplication.
 *
 * 2. But for sufficiently large value of n let's say 10^9 we can't call the
 * function n times, in that case Matrix Exponentiation will help us.
 * We can use Power by Square Method to reduce the number of multiplication to
 * order of log(n).
 *
 * 3. Using recurring series we can prove that
 * F(n) = 2^n - 1
 * Please see Higher Algebra by H. S. Hall & S. R. Knight or
 * Higher Algebra by Bernard and Child.
 *
 * @author: Ashok Rajpurohit (ashok1113@gmail.com)
 * @see Power
 * @see Matrix
 */
public class Hanoi {
    private static Output out;
    private static InputReader in;
    private static Matrix base;
    private static long[][] matrixArray = new long[2][];

    static {
        matrixArray[0] = new long[] { 2, 0 };
        matrixArray[1] = new long[] { 1, 1 };
        base = new Matrix(matrixArray);
    }

    public static void main(String[] args) throws IOException, Exception {
        in = new InputReader(System.in);
        out = new Output(System.out);

        Hanoi a = new Hanoi();
        a.solve();
        out.close();
    }

    private void solve() throws IOException, Exception {
        int mod = 1000000007;
        while (true) {
            int n = in.readInt(), t = in.readInt();
            Random random = new Random();
            long time = System.currentTimeMillis();

            for (int i = 0; i < t; i++)
                matrixMethod(n, mod);

            System.out.println("Matrix:\t" +
                               (System.currentTimeMillis() - time));

            time = System.currentTimeMillis();

            for (int i = 0; i < t; i++)
                iterative(n, mod);

            System.out.println("Iterative:\t" +
                               (System.currentTimeMillis() - time) + "\n");
        }
    }

    public static long Moves(long n, long mod) {
        return recurringSeries(n, mod);
    }

    public static long Moves(long n) {
        return recurringSeries(n);
    }

    private static long iterative(long n, long mod) {
        if (n == 0)
            return 0;

        long res = 1;

        for (int i = 1; i < n; i++) {
            res = ((res << 1) + 1) % mod;
        }

        return res;
    }

    private static long iterative(long n) {
        if (n == 0)
            return 0;

        long res = 1;

        for (int i = 1; i < n; i++) {
            res = (res << 1) + 1;
        }

        return res;
    }

    private static long matrixMethod(long n, long mod) throws Exception {
        if (n == 0)
            return 0;

        if (n == 1)
            return 1;

        if (n == 2)
            return 3;

        Matrix res = Matrix.pow(base, n - 1, mod);
        return (res.get(0, 0) + res.get(1, 0)) % mod;
    }

    private static long matrixMethod(long n) throws Exception {
        if (n == 1)
            return 1;

        if (n == 2)
            return 3;

        Matrix res = Matrix.pow(base, n - 1);
        return res.get(0, 0) + res.get(1, 0);
    }

    private static long recurringSeries(long n, long mod) {
        if (n == 0)
            return 0;

        return (mod + Power.pow(2, n, mod) - 1) % mod;
    }

    private static long recurringSeries(long n) {
        if (n == 0)
            return 0;

        return Power.pow(2, n) - 1;
    }
}
