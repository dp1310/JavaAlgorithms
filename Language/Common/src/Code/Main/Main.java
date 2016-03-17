package Code.Main;

import Code.DSA.ModularArithmatic;
import Code.DSA.Power;

import Code.DSA.Prime;

import Code.DSA.RandomStrings;

import Problems.Fibonacci;

import Problems.MagicSquare;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author: Ashok Rajpurohit (ashok1113@gmail.com)
 */

public class Main {

    private static Output out = new Output();
    private static InputReader in = new InputReader();

    private static String line =
        "---------------------------------------------------------------------";

    public static void main(String[] args) throws IOException, Exception {
        OutputStream outputStream = System.out;

        //        String input = "input_file.in", output = "output_file.out";
        //        FileInputStream fip = new FileInputStream(input);
        //        FileOutputStream fop = new FileOutputStream(output);
        //        in = fip;
        //        out = new PrintWriter(fop);

        Code.Main.Main a = new Code.Main.Main();
        a.solve();
        out.close();
    }

    public void solve() throws IOException, Exception {
        InputReader in = new InputReader();
        RandomStrings random = new RandomStrings();
        while (true) {
            int n = in.readInt(), size = in.readInt();
            String s = random.nextString123(n);
            out.println(s);
            out.println(DevKashyap.combinationString(s, size));
            out.println(line);
            out.flush();
        }
    }

    private static List toList(Set set) {
        List list = new LinkedList();
        for (Object o : set)
            list.add(o);

        return list;
    }

    private static int[] gen_test(int n) {
        int[] res = new int[n];

        for (int i = 0, j = 1; i < n; i++, j++)
            res[i] = j;

        return res;
    }

    public static int[] gen_rand(int size, int mod) {
        Random random = new Random();
        int[] ar = new int[size];
        for (int i = 0; i < size; i++)
            ar[i] = random.nextInt(mod);

        return ar;
    }

    public static int[] gen_rand(int size, int start, int end) {
        int mod = end + 1 - start;
        int[] res = gen_rand(size, mod);

        for (int i = 0; i < size; i++)
            res[i] += start;

        return res;
    }

    private static boolean inTask = false;

    private static class MyTask extends Thread {
        public void run() {
            while (!inTask) {
                // do nothing
            }
        }
    }

    private static int intsum(String s) {
        int res = 0;
        for (int i = 0; i < 7; i += 2) {
            res += (s.charAt(i) << 3) + s.charAt(i + 1);
        }
        return res;
    }
}
