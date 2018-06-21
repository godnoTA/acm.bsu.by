#include <bits/stdc++.h>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int n;
    cin >> n;
    vector<int> v(n + 3, INT32_MAX);
    v[0] = 0;
    for (int i = 0; i < n; ++i) {
        int a, b, c;
        cin >> a >> b >> c;
        v[i + 1] = min(v[i] + a, v[i + 1]);
        v[i + 2] = min(v[i] + b, v[i + 2]);
        v[i + 3] = min(v[i] + c, v[i + 3]);
    }
    cout << v[n] << endl;
    return 0;
}