import java.io.*;
import java.util.*;
 
public class Main {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
 
    void solve() throws Exception {
        int t = ni();
        while (t-- > 0) {
            long a = nl();
            int n = ni();
            int[] d = new int[n]; // n=2
            for (int i = 0; i < n; i++) d[i] = ni();
 
            int lo = d[0], hi = d[1];
            long ans = Long.MAX_VALUE;
 
            // Try lengths: len-1, len, len+1 where len = digits of a
            String sa = Long.toString(a);
            int len = sa.length();
 
            for (int L = Math.max(1, len - 1); L <= len + 1; L++) {
                // Find ceiling and floor of 'a' among L-digit numbers using only lo,hi
                // Ceiling: smallest valid L-digit number >= a (if a has L digits), else smallest L-digit valid
                // Floor: largest valid L-digit number <= a
 
                long ceil = findCeil(a, L, lo, hi);
                long floor = findFloor(a, L, lo, hi);
 
                if (ceil >= 0) ans = Math.min(ans, Math.abs(a - ceil));
                if (floor >= 0) ans = Math.min(ans, Math.abs(a - floor));
            }
 
            pn(ans);
        }
    }
 
    // Smallest valid L-digit number (digits from {lo,hi}) >= a
    // Returns -1 if none exists
    long findCeil(long a, int L, int lo, int hi) {
        String sa = String.format("%0" + L + "d", a); // pad to L digits
        // if a has more than L digits, smallest L-digit valid number
        if (Long.toString(a).length() > L) return -1; // no L-digit number >= a... wait
        // if a has more digits than L, no L-digit number can be >= a
        if (Long.toString(Math.max(a,1)).length() > L) return -1;
 
        // Build greedily
        int[] res = new int[L];
        // Try to find smallest valid >= a digit by digit
        return ceilHelper(sa.toCharArray(), 0, L, lo, hi, res, false);
    }
 
    long ceilHelper(char[] a, int pos, int L, int lo, int hi, int[] res, boolean free) {
        if (pos == L) {
            long val = 0;
            for (int x : res) val = val * 10 + x;
            return val;
        }
        if (free) {
            // fill rest with lo (minimize)
            res[pos] = (pos == 0 && L > 1 && lo == 0) ? hi : lo;
            // handle leading zero: if L>1, first digit can't be 0
            if (pos == 0 && L > 1 && lo == 0) {
                res[pos] = hi; // but wait this might not be minimal...
                // actually if lo=0 and L>1, smallest L-digit valid starting digit is hi (if hi>0) 
                // Hmm but we want ceiling, and we're free, so smallest valid L-digit >= current prefix
                // If lo=0 and pos=0 and L>1: leading digit must be hi (only nonzero option), rest lo
            }
            return ceilHelper(a, pos + 1, L, lo, hi, res, true);
        }
        int ad = a[pos] - '0';
        // Try lo first
        if (lo > ad) { res[pos] = lo; return ceilHelper(a, pos+1, L, lo, hi, res, true); }
        if (lo == ad) { res[pos] = lo; long r = ceilHelper(a, pos+1, L, lo, hi, res, false); if (r >= 0) return r; }
        // Try hi
        if (hi > ad) { res[pos] = hi; return ceilHelper(a, pos+1, L, lo, hi, res, true); }
        if (hi == ad) { res[pos] = hi; long r = ceilHelper(a, pos+1, L, lo, hi, res, false); if (r >= 0) return r; }
        return -1; // need to backtrack (handled by caller getting -1)
    }
 
    long findFloor(long a, int L, int lo, int hi) {
        if (Long.toString(a).length() < L) return -1; // all L-digit numbers > a
        String sa = Long.toString(a);
        if (sa.length() > L) {
            // largest L-digit valid number = all hi's
            long val = 0; for (int i = 0; i < L; i++) val = val * 10 + hi;
            return val;
        }
        int[] res = new int[L];
        return floorHelper(sa.toCharArray(), 0, L, lo, hi, res, false);
    }
 
    long floorHelper(char[] a, int pos, int L, int lo, int hi, int[] res, boolean free) {
        if (pos == L) {
            long val = 0;
            for (int x : res) val = val * 10 + x;
            return val;
        }
        if (free) {
            res[pos] = hi; // maximize remaining
            return floorHelper(a, pos+1, L, lo, hi, res, true);
        }
        int ad = a[pos] - '0';
        // Try hi first (want largest <= a)
        if (hi < ad) { res[pos] = hi; return floorHelper(a, pos+1, L, lo, hi, res, true); }
        if (hi == ad) { res[pos] = hi; long r = floorHelper(a, pos+1, L, lo, hi, res, false); if (r >= 0) return r; }
        // Try lo
        if (lo < ad) { res[pos] = lo; return floorHelper(a, pos+1, L, lo, hi, res, true); }
        if (lo == ad) { res[pos] = lo; long r = floorHelper(a, pos+1, L, lo, hi, res, false); if (r >= 0) return r; }
        return -1;
    }
 
    // ======= template =======
    final int inf = (int) 1e9 + 9;
    final long biginf = (long) 1e18 + 7;
    final long mod = (long) 1e9 + 7;
 
    long pow(long a, long b) {
        long result = 1;
        while (b > 0) { if (b%2==1) result=(result*a)%mod; b/=2; a=(a*a)%mod; }
        return result;
    }
    long gcd(long a, long b) { return b==0?a:gcd(b,a%b); }
 
    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);
        long s = System.currentTimeMillis();
        solve();
        out.flush();
        System.err.println(System.currentTimeMillis() - s + "ms");
    }
    public static void main(String[] args) throws Exception { new Main().run(); }
    void pn(Object... o) { for (int i=0;i<o.length;i++) out.print(o[i]+(i+1<o.length?" ":"\n")); }
    private byte[] inbuf = new byte[1 << 16];
    public int lenbuf=0,ptrbuf=0;
    private int readByte() {
        if(lenbuf==-1) throw new InputMismatchException();
        if(ptrbuf>=lenbuf){ptrbuf=0;try{lenbuf=is.read(inbuf);}catch(IOException e){throw new InputMismatchException();}if(lenbuf<=0)return -1;}
        return inbuf[ptrbuf++];
    }
    private boolean isSpaceChar(int c){return !(c>=33&&c<=126);}
    private int skip(){int b;while((b=readByte())!=-1&&isSpaceChar(b));return b;}
    private String ns(){int b=skip();StringBuilder sb=new StringBuilder();while(!isSpaceChar(b)){sb.appendCodePoint(b);b=readByte();}return sb.toString();}
    private int ni(){int num=0,b;boolean minus=false;while((b=readByte())!=-1&&!((b>='0'&&b<='9')||b=='-'));if(b=='-'){minus=true;b=readByte();}while(true){if(b>='0'&&b<='9')num=num*10+(b-'0');else return minus?-num:num;b=readByte();}}
    private long nl(){long num=0;int b;boolean minus=false;while((b=readByte())!=-1&&!((b>='0'&&b<='9')||b=='-'));if(b=='-'){minus=true;b=readByte();}while(true){if(b>='0'&&b<='9')num=num*10+(b-'0');else return minus?-num:num;b=readByte();}}
    private int[] na(int n){int[]a=new int[n];for(int i=0;i<n;i++)a[i]=ni();return a;}
}