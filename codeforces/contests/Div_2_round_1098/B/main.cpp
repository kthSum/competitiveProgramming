#include <bits/stdc++.h>
using namespace std;
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
 
    int T;
    cin >> T;
 
    while (T--) {
        long long n, x1, x2, k;
        cin >> n >> x1 >> x2 >> k;
 
        if (n <= 3) {
            cout << 1 << '\n';
            continue;
        }
 
        long long diff = llabs(x1 - x2);
        long long d = min(diff, n - diff);
 
        cout << d + k << '\n';
    }
 
    return 0;
}