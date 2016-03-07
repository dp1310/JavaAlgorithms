package Code.Main;

import Code.DSA.ModularArithmatic;
import Code.DSA.Power;

import Code.DSA.Prime;

import Problems.Fibonacci;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        while (true) {
            int n = in.readInt();
            out.println(Prime.primeFactors(n));
            out.println(ModularArithmatic.totient(n));
            out.flush();
        }
    }

    private static int[] gen_test(int n) {
        Random random = new Random();
        int mod = 1000000007;
        int[] res = new int[n];
        int mostFrequent = random.nextInt(mod) + 1;

        int frequency = (n >>> 1) + 1 + random.nextInt(n - 1 - (n >>> 1));
        boolean[] check = new boolean[n];
        int count = 0;
        while (count < frequency) {
            int i = random.nextInt(n);
            while (check[i])
                i = random.nextInt(n);

            count++;
            check[i] = true;
            res[i] = mostFrequent;
        }

        for (int i = 0; i < n; i++)
            if (!check[i]) {
                res[i] = random.nextInt(mod);
            }

        return res;
    }

    public static int[] gen_rand(int size, int mod) {
        Random random = new Random();
        int[] ar = new int[size];
        for (int i = 0; i < size; i++)
            ar[i] = random.nextInt(mod);

        return ar;
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
