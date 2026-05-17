import java.io.*;

public class Main {
    static final class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do c = read(); while (c <= ' ' && c != -1);
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c;
            do c = read(); while (c <= ' ' && c != -1);
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return sign == 1 ? val : -val;
        }

        String next() throws IOException {
            int c;
            do c = read(); while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }
    }

    static final class FastWriter {
        private final StringBuilder sb = new StringBuilder();
        void print(Object x) { sb.append(x); }
        void println(Object x) { sb.append(x).append('\n'); }
        void println() { sb.append('\n'); }
        @Override public String toString() { return sb.toString(); }
    }

    static final FastScanner in = new FastScanner();
    static final FastWriter out = new FastWriter();

    public static void main(String[] args) throws Exception {
        solve();
        System.out.print(out.toString());
    }

    static void solve() throws Exception {
        int n = in.nextInt();
        while(n-- > 0){
            if(in.nextInt() == 1){
                out.println("HARD");
                return;
            }
        }
        out.println("EASY");
    }
}