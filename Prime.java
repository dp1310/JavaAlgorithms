package DSA;

/**
 * @author: Ashok Rajpurohit (ashok1113@gmail.com)
 * Prime Number Class.
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
            for (int j = i + 1; j <= n; j++) {
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
     * this function is now corrected.
     * @param n
     * @return returns true if n is prime else false
     */

    public static boolean primality(int n) {
        if (n == 1)
            return false;
        if (n <= 3)
            return true;

        int p = (int)Math.sqrt(n);

        int[] ar = gen_prime(p);
        for (int i = 0; i < ar.length; i++) {
            if (n % ar[i] == 0)
                return false;
        }
        return true;
        /*
        if (n <= 3)
            return true;

        int i = 20;
        while (factorial[i] > n)
            i--;

        n = n % factorial[i];
        if (n == 1)
            return true;

        if (n > i)
            return primality(n);

        return false;
         */
    }
}
