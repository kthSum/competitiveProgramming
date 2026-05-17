import java.io.*;
import java.util.*;

public class Main {
    InputStream is;
    PrintWriter out;
    String INPUT = "";

    void solve() throws Exception {
        // int t = ni();
        // while (t-- > 0) {
        //     int[] a = na(ni());
        //     int e = 0, o = 0;
        //     for (int x : a) {
        //         if ((x & 1) == 1) o++;
        //         else e++;
        //     }
        //     int diff = Math.abs(e - o);
        //     int ans = ((diff & 1) == 1) ? -1 : diff / 2;
        //     out.println(ans);
        // }

        // optimized
        int tc = ni();
        while(tc-- > 0){
        int n = ni();
        if ((n & 1) == 1) {
        while (n-- > 0) ni();  // just consume input, no allocation
        out.println(-1);
        }
        else {
            int e = 0; // taking even side for no reason.
            int half = n / 2; // count of evens should be (n/2) as well as count of odds too.
            while (n-- > 0) {
                if ((ni() & 1) == 0) e++; // if x is even , e = e + 1;
            }
                // calculating how much operations required as "value":
                // even opetions required : value if((half -  e) > 0) , indicating more odds.
                // odd opetions required : value if((half -  e) < 0) , indicating more evens.
                // no operation required : value if (half - e) = 0 , indicating evens = odds.
                int value = Math.abs(half - e);
                out.println(value);
            
        }
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
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    private String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!isSpaceChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
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
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') num = num * 10 + (b - '0');
            else return minus ? -num : num;
            b = readByte();
        }
    }

    private int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private double nd() {
        return Double.parseDouble(ns());
    }
}