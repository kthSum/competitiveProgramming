import java.io.*;
import java.util.*;

public class Main {
    InputStream is;
    PrintWriter out;
    String INPUT = "";

void solve() throws Exception {
    int tc = ni();
    while (tc-- > 0) {
        int n = ni();
        long[] a = nal(n);

        // Process from right to left
        // We start at n-2 because the operation is a[i] = a[i] + a[i+1]
        // where i is 1-indexed (so 0 to n-2 in 0-indexed)
        for (int i = n - 2; i >= 0; i--) {
            if (a[i + 1] > 0) {
                a[i] += a[i + 1];
            }
        }

        int p = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > 0) {
                p++;
            }
        }
        out.println(p);
    }
}

    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);
        solve();
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    // ========== INPUT ==========
    private byte[] inbuf = new byte[1024];
    public int lenbuf = 0, ptrbuf = 0;

    private int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try { lenbuf = is.read(inbuf); }
            catch (IOException e) { throw new InputMismatchException(); }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
    private int skip() { int b; while ((b = readByte()) != -1 && isSpaceChar(b)); return b; }

    private String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!isSpaceChar(b)) { sb.appendCodePoint(b); b = readByte(); }
        return sb.toString();
    }

    private int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if (b == '-') { minus = true; b = readByte(); }
        while (true) {
            if (b >= '0' && b <= '9') num = num * 10 + (b - '0');
            else return minus ? -num : num;
            b = readByte();
        }
    }

    private long nl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if (b == '-') { minus = true; b = readByte(); }
        while (true) {
            if (b >= '0' && b <= '9') num = num * 10 + (b - '0');
            else return minus ? -num : num;
            b = readByte();
        }
    }
    private long[] nal(int n) {
    long[] a = new long[n];
    for (int i = 0; i < n; i++) a[i] = nl();
    return a;
}

    private int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private double nd() { return Double.parseDouble(ns()); }
}