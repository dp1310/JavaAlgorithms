package Code.DSA;

import Code.Algorithms.Matrix;

import Code.Graph.Graph;
import Code.Graph.GraphList;

import Code.Graph.WGraph;

import Code.Main.InputReader;
import Code.Main.Output;

import Problems.Fibonacci;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * This class is for testing purpose only.
 *
 * @author: Ashok Rajpurohit (ashok1113@gmail.com)
 */

public class Main {

    private static Output out = new Output();
    private static InputReader in = new InputReader();

    private static String line =
        "---------------------------------------------------------------------";

    public static void main(String[] args) throws IOException {
        //        OutputStream outputStream = System.out;
        //        in = System.in;
        //        out = new PrintWriter(outputStream);
        //        String input = "input.txt", output = "output.txt";
        //        FileInputStream fip = new FileInputStream(input);
        //        FileOutputStream fop = new FileOutputStream(output);


        Main a = new Main();
        a.solve();
        out.close();
        in.close();
    }

    public void solve() throws IOException {
        while (true) {
            out.println(in.readInt() << 2);
            out.flush();
        }
    }

    final static class Lino implements Comparable<Lino> {
        int a, b, c;

        Lino(int x, int y, int z) {
            a = x;
            b = y;
            c = z;
        }

        public int compareTo(Main.Lino o) {
            if (this.a != o.a)
                return this.a - o.a;

            if (this.b != o.b)
                return this.b - o.b;

            return this.c - o.c;
        }
    }

    public void populate(WGraph g, int size) {
        int mid = size >>> 1;
        for (int i = 0; i < mid; i++)
            g.addEdge(i, (i + mid) % size);
    }

    public void populate(WGraph g, int a, int b, int size) {
        a %= size;
        b %= size;
        int mid = size >>> 1;
        if (b == a)
            b = (b + mid) % size;

        for (int i = 0; i < size; i++)
            g.addEdge((a + i) % size, (b + i) % size);
    }

    public boolean check() {
        out.println("le ashok");
        return true;
    }

    public static int[] gen_rand(int n) {
        RandomStrings random = new RandomStrings();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++)
            ar[i] = random.nextInt();

        //        int temp = random.nextInt();
        //        for (int i = 0; i < n >>> 3; i++)

        return ar;
    }

    private static Integer[] gen_rando(int n) {
        RandomStrings random = new RandomStrings();
        Integer[] ar = new Integer[n];
        for (int i = 0; i < n; i++)
            ar[i] = random.nextInt();

        //        int temp = random.nextInt();
        //        for (int i = 0; i < n >>> 3; i++)

        return ar;
    }

    public static int[] gen_rand(int size, int mod) {
        Random random = new Random();
        int[] ar = new int[size];
        for (int i = 0; i < size; i++)
            ar[i] = random.nextInt(mod);

        return ar;
    }

    private static int[] gen_rand(int size, int start, int end) {
        int len = end + 1 - start;
        int[] ar = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++)
            ar[i] = start + random.nextInt(len);

        return ar;
    }

    private static int[] gen_Sorted(int size) {
        int[] ar = new int[size];
        ar[0] = new Random().nextInt();

        for (int i = 1; i < size; i++)
            ar[i] = ar[0] + i;

        return ar;
    }

    private static int[] gen_Same(int size) {
        return new int[size];
    }

    private static int[] genInput(int n, int k) {
        int[] kar = gen_rand(k, 1, 100000);
        int[] ar = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++)
            ar[i] = kar[random.nextInt(k)];

        return ar;
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;

        return gcd(b, a % b);
    }

    private static void print(int[] ar) {
        StringBuilder sb = new StringBuilder(ar.length);
        for (int i = 0; i < ar.length - 1; i++)
            sb.append(ar[i]).append(", ");

        sb.append(ar[ar.length - 1]).append('\n');
        System.out.print(sb);
    }

    private static int getmin(String s) {
        int res = powOfFive(s, 0);
        if (res == 50)
            return -1;

        return res;
    }

    private static int powOfFive(String s, int start) {
        if (start == s.length())
            return 0;
        long num = 0;
        int count = 50;
        for (int i = start; i < s.length(); i++) {
            num = (num << 1) + s.charAt(i) - '0';
            if (isPowOfFive(num))
                count = Math.min(count, 1 + powOfFive(s, i + 1));
        }

        return count;
    }

    private static boolean isPowOfFive(long num) {
        if (num == 1)
            return true;

        if (num < 5)
            return false;

        while (num >= 5) {
            if (num % 5 != 0)
                return false;

            num /= 5;
        }
        return true;
    }

    private static void compute(int[] ar, int n) {
        StringBuilder sb = new StringBuilder(n << 2);
        boolean[] diff = new boolean[1001];
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                diff[Math.abs(ar[j] - ar[i])] = true;

        for (int i = 0; i < 1001; i++)
            if (diff[i])
                sb.append(i).append(' ');

        System.out.println(sb);
    }

    private static double totalvalue(double[] w, double[] v, int n, double C) {
        double[] ratio = new double[n];
        for (int i = 0; i < n; i++)
            ratio[i] = v[i] / w[i];

        //        int[] sort = MergeSort.sortIndex(ratio);
        double weight = C, value = 0;
        //        for (int i = 0; i < n && weight > 0; i++) {
        //            if (weight > w[sort[i]]) {
        //                weight -= w[sort[i]];
        //                value += v[sort[i]];
        //            } else {
        //                value += ratio[sort[i]] * weight;
        //                weight = 0;
        //            }
        //        }

        return value;
    }

    public static void print_larger_num(String N) {
        print_larger_num(N.toCharArray());
    }

    private static void print_larger_num(char[] ar) {
        int n = ar.length;
        if (n == 1)
            return;

        else if (n == 2) {
            if (ar[0] < ar[1])
                System.out.println(ar[1] + ar[0] + "");
            else
                System.out.println(String.valueOf(ar));
            return;
        } else {

            int a, b;

            for (a = n - 2; a >= 0; a--) {
                if (ar[a] < ar[a + 1])
                    break;
            }

            if (a < 0) {
                //                System.out.println(String.valueOf(ar));
                return;
            } else {

                for (b = a + 1; b < n; b++) {
                    if (ar[b] <= ar[a])
                        break;
                }
                b--;

                char temp = ar[a];
                ar[a] = ar[b];
                ar[b] = temp;

                sort(ar, a + 1, n - 1);
                System.out.println(String.valueOf(ar));
                print_larger_num(ar);
            }
        }
    }

    private static void sort(char[] a, int i, int j) {
        if (i == j)
            return;

        int[] ar = new int[10];

        for (int k = i; k <= j; k++)
            ar[a[k] - '0']++;

        for (int k = 0; k < 10; k++) {
            while (ar[k] > 0) {
                ar[k]--;
                a[i] = (char)('0' + k);
                i++;
            }
        }
    }

    private static int[] copy(int[] ar) {
        int[] res = new int[ar.length];
        for (int i = 0; i < ar.length; i++)
            res[i] = ar[i];

        return res;
    }

    private static int[][] copy(int[][] ar) {
        int[][] res = new int[ar.length][];
        for (int i = 0; i < ar.length; i++)
            res[i] = copy(ar[i]);

        return res;
    }

    private static boolean isSorted(int[] ar) {
        for (int i = 1; i < ar.length; i++)
            if (ar[i] < ar[i - 1])
                return false;
        return true;
    }
}
