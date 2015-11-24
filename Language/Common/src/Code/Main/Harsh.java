package Code.Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is to solve Harshvardhan's problems (programming only ;) )
 *
 * @author: Ashok Rajpurohit (ashok1113@gmail.com)
 */
public class Harsh {
    private static Output out;
    private static InputReader in;

    public static void main(String[] args) throws IOException {
        in = new InputReader(System.in);
        out = new Output(System.out);

        Harsh a = new Harsh();
        a.solve();
        out.close();
    }

    private void solve() throws IOException {
        while (true) {
            int n = in.readInt();
            functionOne(n);
            functionTwo(n);
        }
    }

    private static void functionOne(int n) {
        for (int i = 0; i < n << 1; i++)
            System.out.print((i > n ? (n << 1) - i : i) + " ");

        System.out.println();
    }

    private static void functionTwo(int n) {
        for (int i = 0; i < n << 1; i++)
            System.out.print((n - (int)Math.sqrt((n - i) * (n - i))) + " ");

        System.out.println();
    }
}
