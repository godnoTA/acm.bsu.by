#include <iostream> 
#include <fstream> 
#include <vector> 
#include<set>
const long long INF = LLONG_MAX;

using namespace std;
int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");

		int n, m, u, v, c;
		cin >> n>>m;
		vector<vector<pair<int, int>>> g(n);
		for (int i = 0; i < m; i++){
			cin >> u >> v >> c;
			g[u - 1].push_back(make_pair(v - 1, c));
			g[v - 1].push_back(make_pair(u - 1, c));
		}
		int s = 0;

		vector<long long> d(n, INF), p(n);
		d[s] = 0;
		set<pair<int, int>> q;
		q.insert(make_pair(d[s], s));
		while (!q.empty()) {
			int v = q.begin()->second;
			q.erase(q.begin());

			for (size_t j = 0; j<g[v].size(); ++j) {
				int to = g[v][j].first,
					len = g[v][j].second;
				if (d[v] + len < d[to]) {
					q.erase(make_pair(d[to], to));
					d[to] = d[v] + len;
					p[to] = v;
					q.insert(make_pair(d[to], to));
				}
			}
		}
		cout << d[n - 1];
	return 0;
}