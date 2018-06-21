#include <bits/stdc++.h>

using namespace std;



bool comp(const pair<int, vector<int>> &a, const pair<int, vector<int>> &b) {
    if (a.second.size() != b.second.size()) {
        return a.second.size() > b.second.size();
    }
    return a.second < b.second;
}



int main() {
    ios_base::sync_with_stdio(false);
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int n, m;
    cin >> n >> m;
    vector<int> in(n);
    vector<vector<int>> out(n);
    vector<vector<int>> edges(n);
    for (int i = 0; i < n; ++i) {
        char c;
        int a, b, d;
        cin >> a >> c >> b;
        --a;
        in[a] = b;
        edges[a].resize(b);
        for (int j = 0; j < b; ++j) {
            cin >> c >> d;
            out[--d].push_back(a);
            edges[a][j] = d;
        }
    }
    queue<int> q;
    for (int i = 0; i < n; ++i) {
        if (in[i] == 0) {
            q.push(i);
        }
    }
    vector<pair<int, vector<int>>> ans(n);
    while (!q.empty()) {
        int i = q.front();
        q.pop();
        vector<pair<int, vector<int>>> temp;
        vector<int> e;
        for (int j : edges[i]) {
            temp.push_back(ans[j]);
        }
        sort(temp.begin(), temp.end(), comp);
        int u = 1;
        for (int j = 0; j < temp.size(); ++j) {
            u += j * temp[j].second.size();
            u += temp[j].first;
            e.insert(e.end(), temp[j].second.begin(), temp[j].second.end());
        }
        e.push_back(i + 1);
        ans[i] = make_pair(u, e);
        for (int j : out[i]) {
            --in[j];
            if (in[j] == 0) {
                q.push(j);
            }
        }
    }
    cout << ans[--m].first - 1 << endl;
    for (int i = 0; i < ans[m].second.size(); ++i) {
        if (i != 0) {
            cout << " ";
        }
        cout << ans[m].second[i];
    }
    cout << endl;
    return 0;
}