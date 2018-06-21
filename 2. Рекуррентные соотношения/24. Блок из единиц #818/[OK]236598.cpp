#include <bits/stdc++.h>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);
    int n, m;
    cin >> n >> m;
    vector<vector<int>> v(n, vector<int> (m));
    for (auto &a : v) {
        for (auto &i : a) {
            cin >> i;
        }
        for (int i = m - 2; i >= 0; --i) {
            if (a[i] == 1) {
                a[i] = a[i + 1] + 1;
            }
        }
    }
    int best_ans = 0;
    int best_i = 0;
    int best_j = 0;
    for (int j = 0; j < m; ++j) {
        for (int i = 0; i < n; ++i) {
            int ans = INT32_MAX;
            for (int k = i; k < n; ++k) {
                ans = min(ans, v[k][j]);
                if (best_ans < min(ans, k - i + 1)) {
                    best_ans = min(ans, k - i + 1);
                    best_i = i;
                    best_j = j;
                }
            }
        }
    }
    cout << best_ans << endl << best_j + 1 << endl << best_i + 1 << endl;
    return 0;
}