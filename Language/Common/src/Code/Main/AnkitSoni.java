package Code.Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

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

    private static long chipMonkOne(int n, int k) {
        long cycle = 1L * n * (n + 1) / 2;
        if (k == 0)
            return cycle;

        k %= cycle;
        if (k == 0)
            return 0;

        return cycle - k;
    }
}
