package Code.DSA;

/**
 * Implementation of Range Query Data Structure using an array of arrays.
 * Preprocessing complexity is order of n * long(n) and quering complexity is
 * order of 1 (constant time). This is useful when the range of the query can
 * be anything from 1 to n. If the min range width is fixed,
 * {@link RangeQueryBlock} is the better option for range query.
 *
 * @author Ashok Rajpurohit ashok1113@gmail.com
 */
public class RangeQueryRead {
    private int[][] mar;
    private boolean min = true;

    private RangeQueryRead() {
        super();
    }

    public RangeQueryRead(int[] ar) {
        if (min)
            formatMin(ar);
        else
            formatMax(ar);
    }

    public RangeQueryRead(int[] ar, boolean min) {
        this.min = min;
        if (min)
            formatMin(ar);
        else
            formatMax(ar);
    }

    public int query(int L, int R) {
        if (R >= mar[1].length || L < 0)
            throw new IndexOutOfBoundsException(L + ", " + R);
        if (min)
            return queryMin(L, R);
        return queryMax(L, R);
    }

    private int queryMin(int L, int R) {
        int half = Integer.highestOneBit(R + 1 - L);
        return Math.min(mar[half][L], mar[half][R + 1 - half]);
    }

    private int queryMax(int L, int R) {
        int half = Integer.highestOneBit(R + 1 - L);
        return Math.max(mar[half][L], mar[half][R + 1 - half]);
    }

    private void format(int[] ar) {
        if (min)
            formatMin(ar);
        else
            formatMax(ar);
    }

    private void formatMin(int[] ar) {
        mar = new int[ar.length + 1][];
        mar[1] = ar;
        int bit = 2;
        while (bit < mar.length) {
            int half = bit >>> 1;
            mar[bit] = new int[ar.length - bit + 1];
            for (int i = 0; i <= ar.length - bit; i++) {
                mar[bit][i] = Math.min(mar[half][i], mar[half][i + half]);
            }
            bit <<= 1;
        }
    }

    private void formatMax(int[] ar) {
        mar = new int[ar.length + 1][];
        mar[1] = ar;
        int bit = 2;
        while (bit < mar.length) {
            int half = bit >>> 1;
            mar[bit] = new int[ar.length - bit + 1];
            for (int i = 0; i <= ar.length - bit; i++) {
                mar[bit][i] = Math.max(mar[half][i], mar[half][i + half]);
            }
            bit <<= 1;
        }
    }
}
