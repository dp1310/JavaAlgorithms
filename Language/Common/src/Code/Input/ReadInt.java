package Code.Input;

import java.io.BufferedReader;
//import static java.lang.System.in;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.StringTokenizer;

public class ReadInt {

    //    private static NewInputReader in;
    private static PrintWriter out;
    private static InputStream in;

    public ReadInt() {
        super();
    }

    public static void main(String[] args) throws IOException {
        //        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FileInputStream fin = new FileInputStream("input.txt");
        in = fin;
        //        NewInputReader in = new NewInputReader();
        out = new PrintWriter(outputStream);
        Code.Input.ReadInt a = new Code.Input.ReadInt();
        a.solve();
        fin.close();
        out.close();
    }

    public void solve() throws IOException {
        NewInputReader in = new NewInputReader();
        int count = 0;
        //        int m = in.readInt();
        //        int n = (int)' ';
        //        out.println(n);
        int a = -1;
        String b = "";
        //        a = in.readInt();
        //        out.println(a);
        //        out.println(in.read());
        long t1 = System.currentTimeMillis();
        try {
            while (true) {
                a = in.readInt();
                //                b = in.read();
                count++;
            }
        } catch (Exception ioe) {
        }
        long t2 = System.currentTimeMillis();
        out.println(b);
        out.println(count);
        //        out.println(a+","+count + "," + b);
        out.println(t2 - t1);
        //        out.println(b);

        //        int t = in.readInt();
        //        long l = in.readLong();
        //        t = t<<1;
        //        l = l<<1;
        //        StringBuilder sb = new StringBuilder();
        //        sb.append(t).append(' ');
        //        sb.append(l);
        //        out.println(sb);
    }

    final static class NewInputReader {
        byte[] buffer = new byte[8192];
        int offset = 0;
        int bufferSize = 0;

        public int readInt() throws IOException {
            int number = 0;
            int s = 1;
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }
            if (bufferSize == -1)
                throw new IOException("No new bytes");
            for (; buffer[offset] < 0x30 || buffer[offset] == '-'; ++offset) {
                if (buffer[offset] == '-')
                    s = -1;
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (; offset < bufferSize && buffer[offset] > 0x2f; ++offset) {
                number = (number << 3) + (number << 1) + buffer[offset] - 0x30;
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            ++offset;
            return number * s;
        }

        public long readLong() throws IOException {
            long res = 0;
            int s = 1;
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }
            for (; buffer[offset] < 0x30 || buffer[offset] == '-'; ++offset) {
                if (buffer[offset] == '-')
                    s = -1;
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (; offset < bufferSize && buffer[offset] > 0x2f; ++offset) {
                res = (res << 3) + (res << 1) + buffer[offset] - 0x30;
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            ++offset;
            if (s == -1)
                res = -res;
            return res;
        }

        public String read() throws IOException {
            StringBuilder sb = new StringBuilder();
            if (offset == bufferSize) {
                offset = 0;
                bufferSize = in.read(buffer);
            }

            if (bufferSize == -1 || bufferSize == 0)
                throw new IOException("No new bytes");

            for (;
                 buffer[offset] == ' ' || buffer[offset] == '\t' || buffer[offset] ==
                 '\n'; ++offset) {
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            for (; offset < bufferSize; ++offset) {
                if (buffer[offset] == ' ' || buffer[offset] == '\t' ||
                    buffer[offset] == '\n')
                    break;
                if (Character.isValidCodePoint(buffer[offset])) {
                    sb.appendCodePoint(buffer[offset]);
                }
                if (offset == bufferSize - 1) {
                    offset = -1;
                    bufferSize = in.read(buffer);
                }
            }
            return sb.toString();
        }
    }
}
