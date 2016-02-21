package Code.Algorithms;

/**
 * Matrix class is created specifically for matrix multiplication, addition etc.
 * Most common use of matrix multiplication is in calculating nth
 * fibonacci term.
 * For recursive functions we can use matrix multiplications to calculate
 * nth term.
 * for General Recursive function, the transpose matrix is yet to be created,
 * I am working on it how to create a transpose matrix for the same.
 * Pending tasks are: Inverse Matrix, Transpose Matrix.
 * This class has one two dimensional array for matix representation.
 * The parameter n and m are the dimensions of matrix of size n x m.
 * n and m are for shorthand purpose only.
 * 
 * @author Ashok Rajpurohit (ashok1113@gmail.com)
 */
public class Matrix {
    private long[][] matrix;
    private int n = 0, m = 0;

    private Matrix() {
        /**
         * this constructor is only for internal purpose to create dummy
         * matrix to store the result temporary.
         */
    }

    public Matrix(long[][] ar) {
        n = ar.length;
        m = ar[0].length;
        matrix = new long[n][m];
        for (int i = 0; i < ar.length; i++)
            for (int j = 0; j < ar[0].length; j++)
                matrix[i][j] = ar[i][j];
    }

    /**
     * Multiplies matrix a and b, stores the result in new matrix.
     * @param a
     * @param b
     * @return returns a x b matrix
     * @throws Exception
     */
    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.m != b.n)
            throw new RuntimeException("matrices not multiplicable");

        Matrix result = new Matrix();
        result.n = a.n;
        result.m = b.m;
        long[][] ar = new long[a.n][b.m];
        for (int i = 0; i < a.n; i++) {
            for (int j = 0; j < b.m; j++) {
                for (int k = 0; k < a.m; k++)
                    ar[i][j] += a.matrix[i][k] * b.matrix[k][j];
            }
        }
        result.matrix = ar;
        return result;
    }

    /**
     * Multiplies two matrices a and b and stores the result into third matrix
     * @param a
     * @param b
     * @param result
     * @throws Exception when a and b are not multiplicable or result matrix
     * is not of proper dimension.
     */
    private static void multiply(Matrix a, Matrix b, Matrix result) {
        if (a.m != b.n || result.n != a.n || result.m != b.m)
            throw new RuntimeException("matrices not multiplicable");

        result.reset();
        for (int i = 0; i < a.n; i++) {
            for (int j = 0; j < b.m; j++) {
                for (int k = 0; k < a.m; k++)
                    result.matrix[i][j] += a.matrix[i][k] * b.matrix[k][j];
            }
        }
    }

    /**
     * Calculates Matrix a raised to power n and returns a new matrix
     *
     * @param a
     * @param n
     * @param mod
     * @return
     * @throws Exception
     */
    public static Matrix pow(Matrix a, long n, long mod) {
        if (a.n != a.m)
            throw new RuntimeException("Matrix should be square matrix only");
        Matrix result = new Matrix(a.matrix);
        if (n == 1)
            return result;

        Matrix temp = new Matrix(a.matrix);

        long r = Long.highestOneBit(n);
        while (r > 1) {
            r = r >>> 1;
            square(result, temp);
            result.mod(mod);
            if ((r & n) != 0) {
                multiply(result, a, temp);
                result.copy(temp);
                result.mod(mod);
            }
        }
        return result;
    }

    public static Matrix pow(Matrix a, long n) {
        if (a.n != a.m)
            throw new RuntimeException("Matrix should be square matrix only");
        Matrix result = new Matrix(a.matrix);
        if (n == 1)
            return result;

        Matrix temp = new Matrix(a.matrix);

        long r = Long.highestOneBit(n);
        while (r > 1) {
            r = r >>> 1;
            square(result, temp);
            if ((r & n) != 0) {
                multiply(result, a, temp);
                result.copy(temp);
            }
        }
        return result;
    }

    private static boolean insert(Matrix a, Matrix b, int i, int j) {
        if (i + b.n > a.n || j + b.m > a.m)
            return false;

        for (int k = 0; k < b.n; k++)
            for (int m = 0; m < b.m; m++) {
                a.matrix[i + k][j + m] = b.matrix[k][m];
            }

        return true;
    }

    private static boolean insertAdd(Matrix a, Matrix b, int i, int j) {
        if (i + b.n > a.n || j + b.m > a.m)
            return false;

        for (int k = 0; k < b.n; k++)
            for (int m = 0; m < b.m; m++)
                a.matrix[i + k][j + m] += b.matrix[k][m];

        return true;
    }

    /**
     * Copy the contents of matrix temp into this matrix.
     * @param temp Temporary Matrix.
     * @throws Exception if the matrices are not compatible
     */
    private void copy(Matrix temp) {
        if (this.n != temp.n || this.m != temp.m)
            throw new RuntimeException("Matrices not compatible");

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                matrix[i][j] = temp.matrix[i][j];
    }

    /**
     * Squaring the matrix a, storing the result temporarily in matrix b.
     * finally the result is stored in matrix a.
     * @param a matrix to be squared
     * @param b temporary result storage
     */
    private static void square(Matrix a, Matrix b) {
        b.reset();
        for (int i = 0; i < a.n; i++)
            for (int j = 0; j < a.n; j++) {
                for (int k = 0; k < a.n; k++)
                    b.matrix[i][j] += a.matrix[i][k] * a.matrix[k][j];
            }

        for (int i = 0; i < a.n; i++)
            for (int j = 0; j < a.n; j++)
                a.matrix[i][j] = b.matrix[i][j];
    }

    /**
     * Squares the matrix and stores the result in the same matrix.
     * @throws Exception if the Matrix is not square matrix.
     */
    public void square() {
        if (this.m != this.n)
            throw new RuntimeException("Matrix should be square matrix");
        Matrix temp = clone();
        square(this, temp);
    }

    /**
     * Returns a hash code value for the Matrix. if two matrices are equal
     * then the hash code value for both the matrices will be same, but if
     * hash code value is same that does not guarantee that matrices are
     * equal. This function can be used in hashing.
     * @return
     */
    public final int hashCode() {
        int mod = 1000000007;
        long res = 0;
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.m; j++)
                res = ((res << 29) + matrix[i][j]) % mod;

        return (int)res;
    }

    /**
     * Compares the object o to this Matrix.
     * Return true if o and this matrix are equal.
     * Equality is defined as values of all the members is equals.
     *
     * @param o Object reference with which to compare.
     * @return <code>true</code> if this object is the same as the o
     *          argument; <code>false</code> otherwise.
     */
    public final boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Matrix))
            return false;

        Matrix x = (Matrix)o;
        if (x.n != this.n || x.m != this.m)
            return false;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (this.matrix[i][j] != x.matrix[i][j])
                    return false;

        return true;
    }

    /**
     * Create and Returns a new copy of this object.
     * @return
     */
    public Matrix clone() {
        return new Matrix(this.matrix);
    }

    public long get(int i, int j) {
        return matrix[i][j];
    }

    /**
     * Create a Transpose Matrix for recursive relation defined by ar.
     * The format of ar is assumed as
     * ar[0] * F[n-1] + ar[1] * F[n - 2] + ... + ar[end] * F[n - 1 - end]
     * This Matrix can be used for calculating nth term of the series.
     * 
     * @param ar
     * @return
     */
    public static Matrix getTranspose(long[] ar) {
        Matrix transpose = new Matrix();
        transpose.matrix = new long[ar.length][ar.length];
        transpose.n = ar.length;
        transpose.m = ar.length;

        for (int i = 0; i < ar.length; i++)
            transpose.matrix[0][i] = ar[i];

        for (int i = 1, j = 0; i < ar.length; i++, j++)
            transpose.matrix[i][j] = 1;

        return transpose;
    }

    public void mod(long mod) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                matrix[i][j] %= mod;
    }

    /**
     * resets the all values in matrix to zero.
     */
    private void reset() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                matrix[i][j] = 0;
    }

    /**
     * Checks whether two matrices a and b are multiplicable.
     * @param a
     * @param b
     * @return
     */
    private static boolean multiplicability(Matrix a, Matrix b) {
        return a.m == b.n;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(n * m << 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                sb.append(matrix[i][j]).append(' ');
            sb.append('\n');
        }

        return sb.toString();
    }
}
