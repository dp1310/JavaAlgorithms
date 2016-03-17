package Code.Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Problem Name: Ankit Soni
 * Link: Office
 *
 * @author: Ashok Rajpurohit (ashok1113@gmail.com)
 */
public class AnkitSoni {
    private static Output out;
    private static InputReader in;

    public static void main(String[] args) throws IOException {
        in = new InputReader(System.in);
        out = new Output(System.out);

        AnkitSoni a = new AnkitSoni();
        a.solve();
        out.close();
    }

    private void solve() throws IOException {
        int t = in.readInt();
        while (t > 0) {
            t--;
            int n = in.readInt(), k = in.readInt();
            out.println(chipMonkOne(n, k));
        }
    }

    public static int[] gen_prime(int n) {
        int[] primes;
        int sqrt = (int)Math.sqrt(n);
        LinkedList<Integer> prime = new LinkedList<Integer>();

        for (int i = 6; i <= n; i += 6) {
            int root = (int)Math.sqrt(i);
            Iterator<Integer> iter = prime.iterator();
            boolean isPrime = true;
            int v = i - 1;

            while (iter.hasNext() && isPrime) {
                int num = iter.next();
                if (num > root)
                    break;

                isPrime = v % num != 0;
            }

            if (isPrime)
                prime.add(v);

            isPrime = true;
            v = i + 1;

            iter = prime.iterator();

            while (iter.hasNext() && isPrime) {
                int num = iter.next();
                if (num > root + 1)
                    break;

                isPrime = v % num != 0;
            }

            if (isPrime)
                prime.add(v);
        }

        primes = new int[prime.size() + 2];
        primes[0] = 2;
        primes[1] = 3;

        int index = 2;
        Iterator<Integer> iter = prime.iterator();

        while (iter.hasNext()) {
            primes[index++] = iter.next();
        }

        return primes;
    }

    private static long chipMonkOne(int n, int k) {
        long cycle = 1L * n * (n + 1) / 2;
        if (k == 0)
            return cycle;

        k %= cycle;
        if (k == 0)
            return 0;

        return cycle - k;
    }
}
