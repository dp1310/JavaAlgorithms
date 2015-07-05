package DSA;

/**
 * @author Ashok Rajpurohit
 * Binary GCD Implementation.
 * Euler's method is faster than Binary GCD when one of the parameter is
 * in Integer range. If both the parameters are in Long range, in that case
 * Binary GCD is faster.
 */

public class BinaryGCD {
    private BinaryGCD() {
        super();
    }

    public static int gcd(int a, int b) {
        return (int)euler(a, b);
    }

    public static int gcd(int a, long b) {
        return (int)euler(a, b);
    }

    public static int gcd(long a, int b) {
        return (int)euler(a, b);
    }

    public static long gcd(long a, long b) {
        if (a < Integer.MAX_VALUE || b < Integer.MAX_VALUE)
            return euler(a, b);
        return bgcd(a, b);
    }

    private static long bgcd(long a, long b) {
        if (a == 0)
            return b;

        if (b == 0)
            return a;

        if (a == 1 || b == 1)
            return 1;

        int res = 0;
        int x = Long.numberOfTrailingZeros(a);
        int y = Long.numberOfTrailingZeros(b);
        res = x > y ? y : x;
        a = a >>> x;
        b = b >>> y;

        while (a != b && a != 1) {
            if (a > b) {
                a -= b;
                a = a >>> Long.numberOfTrailingZeros(a);
            } else {
                b -= a;
                b = b >>> Long.numberOfTrailingZeros(b);
            }
        }
        return a << res;
    }

    private static long euler(long a, long b) {
        if (a == 0)
            return b;
        return euler(b % a, a);
    }
}
