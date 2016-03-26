package Code.Main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

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

    static class A653 {
        public static void main(String[] args) {
            InputReader in = new InputReader(System.in);
            PrintWriter out = new PrintWriter(System.out);
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            Arrays.sort(a);
            boolean flag = false;
            for (int i = 1; i < n - 1; i++) {
                if (a[i - 1] + 1 == a[i] && a[i] + 1 == a[i + 1]) {
                    out.println("YES");
                    flag = true;
                    break;
                }
            }
            if (!flag)
                out.println("NO");
            out.close();
        }

        static class InputReader {
            public BufferedReader reader;
            public StringTokenizer tokenizer;

            public InputReader(InputStream stream) {
                reader =
                        new BufferedReader(new InputStreamReader(stream), 32768);
                tokenizer = null;
            }

            public String next() {
                while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                    try {
                        tokenizer = new StringTokenizer(reader.readLine());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return tokenizer.nextToken();
            }

            public int nextInt() {
                return Integer.parseInt(next());
            }

            public long nextLong() {
                return Long.parseLong(next());
            }

            public double nextDouble() {
                return Double.parseDouble(next());
            }

        }
    }
}
