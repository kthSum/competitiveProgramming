import java.io.*;
import java.util.*;

public class Main {
    InputStream is;
    PrintWriter out;
    String INPUT = "";

    void solve() throws Exception {
        int t = ni();
        while (t-- > 0) {
            int n = ni();
            int[] a = na(n);

            // Count frequencies using a Map for large value support (like 10^9)
            Map<Integer, Integer> counts = new HashMap<>();
            for (int x : a) {
                counts.put(x, counts.getOrDefault(x, 0) + 1);
            }

            // 1. Find k (the first missing non-negative integer)
            int k = 0;
            while (counts.containsKey(k)) {
                k++;
            }

            // 2. Construct the optimal sequence
            // Order: [0, 1, ..., k-1] then [all others sorted descending]
            int[] result = new int[n];
            int idx = 0;

            // Fill MEX chain
            for (int i = 0; i < k; i++) {
                result[idx++] = i;
                counts.put(i, counts.get(i) - 1);
            }

            // Fill remainder
            List<Integer> remainder = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                int val = entry.getKey();
                int freq = entry.getValue();
                for (int i = 0; i < freq; i++) {
                    remainder.add(val);
                }
            }

            // Sort remainder descending to maximize Prefix MAX
            remainder.sort(Collections.reverseOrder());
            for (int x : remainder) {
                result[idx++] = x;
            }

            // 3. Calculate Sum of Prefix MEX + Prefix MAX
            long totalSum = 0;
            long currentMax = -1;
            int currentMex = 0;
            Set<Integer> seen = new HashSet<>();

            for (int x : result) {
                if (x > currentMax) currentMax = x;

                seen.add(x);
                while (seen.contains(currentMex)) {
                    currentMex++;
                }

                totalSum += (currentMex + currentMax);
            }

            out.println(totalSum);
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

    // ========== INPUT TEMPLATE METHODS ==========
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