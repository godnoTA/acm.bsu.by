#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

typedef long long ll;

const int INF = 1000000000;

const int maxN = 1000;
                      
int x[maxN], y[maxN], p[maxN], cnt = 0, ans[maxN];
bool used[maxN];     
vector<int> g[maxN];


int distance(int x1, int y1, int x2, int y2) {
	return abs(x1 - x2) + abs(y1 - y2);
}


int get(int x) {
	if (p[x] == x) return x;
	p[x] = get(p[x]);
	return p[x];
}

void unite(int a, int b) {
	a = get(a), b = get(b);
	p[a] = b;
}

void dfs(int v, int p) {
	if (used[v]) {
		used[v] = false;
		++cnt;
		ans[cnt] = v;
	}
	for (int i = 0; i < g[v].size(); ++i) {
		int next = g[v][i];
		if (next == p) continue;
		dfs(next, v);
	}
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	int n;
	cin >> n;
	for (int i = 1; i <= n; ++i) cin >> x[i] >> y[i];
	for (int i = 1; i <= n; ++i) p[i] = i, used[i] = true;
	vector<pair<int, pair<int, int> > > all;
	for (int i = 1; i <= n; ++i) {
		for (int j = 1; j <= n; ++j) {
			if (i < j) all.push_back(make_pair(distance(x[i], y[i], x[j], y[j]), make_pair(i, j)));
		}
	}
	sort(all.begin(), all.end());
	for (int i = 0; i < all.size(); ++i) {
		int d = all[i].first;
		int x = all[i].second.first;
		int y = all[i].second.second;
		if (get(x) != get(y)) {
			unite(x, y);
			g[x].push_back(y);
			g[y].push_back(x);
		}
	}
	int t = n;
	dfs(t, -1);
	ll answer = 0;
	for (int i = 1; i <= n; ++i) answer += (ll)distance(x[ans[i]], y[ans[i]], x[ans[i % n + 1]], y[ans[i % n + 1]]);
	for (int i = 1; i <= n; ++i) cout << ans[i] << " ";
	cout << t << endl;
	cout << answer << endl;
	return 0;
}