package Code.DSA;

import java.util.LinkedList;

/**
 * This class implements functions related to prime numbers.
 * Work in progress.
 *
 * @author: Ashok Rajpurohit (ashok1113@gmail.com)
 */

public class Prime {
    private Prime() {
        super();
    }

    /**
     * calculate factorials of numbers upto 20 and
     * store into factorial array.
     * edit: this factorial is useless now as my algo was incorrect
     * see the last function primality.
     * in this class only gen_prime(int n) is correct.
     */
    private static long[] factorial = new long[21];
    static {
        factorial[0] = 1;
        for (int i = 1; i < 21; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    /**
     * This function generates prime numbers upto given integer n and
     * returns the array of primes upto n (inclusive).
     * @param n prime numbers upto integer n
     * @return
     */
    public static int[] gen_prime(int n) {
        boolean[] ar = new boolean[n + 1];
        int root = (int)Math.sqrt(n);

        for (int i = 2; i <= root; i++) {
            while (ar[i])
                i++;
            for (int j = i * 2; j <= n; j += i) {
                if (!ar[j]) {
                    ar[j] = j % i == 0;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= n; i++)
            if (!ar[i])
                count++;

        int[] ret = new int[count];

        for (int i = 2, j = 0; i <= n; i++) {
            if (!ar[i]) {
                ret[j] = i;
                j++;
            }
        }
        return ret;
    }

    public static int[] primes(int[] ar) {
        MergeSort.sort(ar);
        int p = (int)Math.sqrt(ar[ar.length - 1]);
        int[] primes = gen_prime(p);
        boolean[] primes_check = new boolean[ar.length];
        for (int i = 0; i < primes.length; i++) {
            for (int j = 0; j < primes.length && !primes_check[i]; j++)
                if (ar[i] % primes[j] == 0)
                    primes_check[i] = true;
        }

        int count = 0;
        for (int i = 0; i < ar.length; i++)
            if (!primes_check[i])
                count++;

        int[] prime = new int[count];
        for (int i = 0, j = 0; i < ar.length && j < count; i++) {
            if (!primes_check[i]) {
                prime[j] = ar[i];
                j++;
            }
        }
        return prime;
    }

    /**
     * Returns array of prime numbers in given range. start and end are inclusive.
     *
     * @param start start point of range
     * @param end end of the range
     * @return array of primes number in the range
     */
    public static int[] primesInRange(int start, int end) {
        if (start > end)
            return primesInRange(end, start);

        boolean[] ar = new boolean[end + 1 - start];
        int p = (int)Math.sqrt(end);
        int[] primes = gen_prime(p);

        for (int i = 0; i < primes.length; i++) {
            int j = (start + primes[i] - 1) / primes[i];
            j = j * primes[i] - start;

            for (; j < ar.length; j += primes[i])
                ar[j] = true;
        }

        int count = 0;
        for (int i = 0; i < ar.length; i++) {
            if (!ar[i]) {
                count++;
            }
        }

        int[] res = new int[count];
        for (int i = 0, j = 0; j < count; i++)
            if (!ar[i]) {
                res[j] = start + i;
                j++;
            }

        return res;
    }

    /**
     * this function is now corrected.
     * @param n
     * @return returns true if n is prime else false
     */

    public static boolean primality(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        int p = (int)Math.sqrt(n);
        boolean[] primes = new boolean[p + 1];
        primes[2] = true;

        for (int i = 3; i <= p; i++)
            primes[i] = true;

        for (int i = 2; i <= p; i++) {
            if (!primes[i])
                continue;

            if (n % i == 0)
                return false;

            for (int j = i << 1; j <= p; j += i)
                primes[j] = false;
        }
        return true;
    }

    /**
     * Returns list of prime factors for integer n.
     *
     * @param n
     * @return
     */
    public static LinkedList<Integer> primeFactors(int n) {
        LinkedList<Integer> list = new LinkedList<Integer>();

        if (n < 2)
            return list;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i != 0)
                continue;

            list.add(i);

            while (n % i == 0)
                n /= i;
        }

        if (n > 1)
            list.add(n);

        return list;
    }
}
