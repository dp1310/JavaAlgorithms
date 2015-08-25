package Code.DSA;

public class Fibonacci {
    private Fibonacci() {
        super();
    }

    private static final long[] fib;
    private static double fi_plus, fi_minus, root_five = Math.sqrt(5);

    static {
        fi_plus = (1 + root_five) / 2;
        fi_minus = (1 - root_five) / 2;
    }

    static {
        fib = new long[92];
        fib[0] = 1;
        fib[1] = 1;
        long a = 1, b = 1, temp;
        int count = 2;
        while (count < 92) {
            b = a + b;
            temp = b;
            b = a;
            a = temp;
            fib[count] = a;
            count++;
        }
        System.out.println(fib[91] + "\t le ashok");
    }

    public static long getFibbyPower(int n, int mod) {
        return (long)((Power.pow(fi_plus, n, mod) -
                       Power.pow(fi_minus, n, mod)) / root_five);
    }

    public static long getFib(int n, int mod) {
        if (n < 3)
            return 1;

        if ((n & 1) == 0) {
            n = n >>> 1;
            long b = getFib(n, mod);
            long a = getFib(n - 1, mod);
            return ((a << 1) + b) * b % mod;
        }

        n = (n + 1) >>> 1;
        long a = getFib(n - 1, mod);
        long b = getFib(n, mod);
        return (a * a + b * b) % mod;
    }

    public static long getfib(int n) {
        if (n >= 92)
            return -1;
        return fib[n];
    }

    public static long[] getfibs() {
        return fib.clone();
    }

    //    public static long getfib(int n, long mod) {
    //        if (n < 92)
    //            return fib[n] % mod;
    //        return -1;
    //    }
}
