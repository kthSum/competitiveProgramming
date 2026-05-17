#include <bits/stdc++.h>
using namespace std;
 
using int64 = long long;
 
bool ok[10];
vector<int> digs;
 
string S;
 
string buildGE() {
    int n = S.size();
 
    function<string(int, bool)> dfs = [&](int pos, bool greater) -> string {
        if (pos == n) return "";
 
        int cur = S[pos] - '0';
 
        for (int d : digs) {
 
            if (pos == 0 && n > 1 && d == 0)
                continue;
 
            if (!greater && d < cur)
                continue;
 
            bool ng = greater || (d > cur);
 
            if (ng) {
                string res;
                res.push_back(char('0' + d));
 
                int mn = digs[0];
 
                for (int i = pos + 1; i < n; i++)
                    res.push_back(char('0' + mn));
 
                return res;
            }
 
            string suf = dfs(pos + 1, false);
 
            if (suf != "#") {
                string res;
                res.push_back(char('0' + d));
                res += suf;
                return res;
            }
        }
 
        return "#";
    };
 
    string ans = dfs(0, false);
 
    if (ans != "#")
        return ans;
 
    // longer length
    int mnNonZero = -1;
 
    for (int d : digs) {
        if (d != 0) {
            mnNonZero = d;
            break;
        }
    }
 
    if (mnNonZero == -1)
        return "#";
 
    string res;
    res.push_back(char('0' + mnNonZero));
 
    for (int i = 0; i < n; i++)
        res.push_back(char('0' + digs[0]));
 
    return res;
}
 
string buildLE() {
    int n = S.size();
 
    function<string(int, bool)> dfs = [&](int pos, bool smaller) -> string {
        if (pos == n) return "";
 
        int cur = S[pos] - '0';
 
        for (int i = (int)digs.size() - 1; i >= 0; i--) {
 
            int d = digs[i];
 
            if (pos == 0 && n > 1 && d == 0)
                continue;
 
            if (!smaller && d > cur)
                continue;
 
            bool ns = smaller || (d < cur);
 
            if (ns) {
                string res;
                res.push_back(char('0' + d));
 
                int mx = digs.back();
 
                for (int j = pos + 1; j < n; j++)
                    res.push_back(char('0' + mx));
 
                return res;
            }
 
            string suf = dfs(pos + 1, false);
 
            if (suf != "#") {
                string res;
                res.push_back(char('0' + d));
                res += suf;
                return res;
            }
        }
 
        return "#";
    };
 
    string ans = dfs(0, false);
 
    if (ans != "#")
        return ans;
 
    // shorter length
    int mx = digs.back();
 
    if (mx == 0)
        return "0";
 
    string res;
 
    for (int i = 0; i < n - 1; i++)
        res.push_back(char('0' + mx));
 
    return res.empty() ? "#" : res;
}
 
long long toLL(const string &s) {
    long long x = 0;
 
    for (char c : s)
        x = x * 10 + (c - '0');
 
    return x;
}
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
 
    int T;
    cin >> T;
 
    while (T--) {
 
        long long a;
        int n;
 
        cin >> a >> n;
 
        digs.clear();
        memset(ok, false, sizeof(ok));
 
        for (int i = 0; i < n; i++) {
            int x;
            cin >> x;
 
            digs.push_back(x);
            ok[x] = true;
        }
 
        S = to_string(a);
 
        string ge = buildGE();
        string le = buildLE();
 
        long long ans = (long long)4e18;
 
        if (ge != "#") {
            ans = min(ans, llabs(toLL(ge) - a));
        }
 
        if (le != "#") {
            ans = min(ans, llabs(toLL(le) - a));
        }
 
        cout << ans << '\n';
    }
}