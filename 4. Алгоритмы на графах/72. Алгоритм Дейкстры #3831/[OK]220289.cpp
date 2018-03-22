#include <iostream>
#include <fstream>
#include <vector>
#include <set>

using namespace std;
vector < pair<int, int> > g[200000];
long long d[200000];
#define INF INT64_MAX
int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n, m;
	fin >> n >> m;
	for (int i = 0; i < m; i++) {
		int u, v, w;
		fin >> u >> v >> w;
		g[u - 1].push_back(make_pair(v - 1, w));
		g[v - 1].push_back(make_pair(u - 1, w));
	}
	int s = 0;
	for (int i = 0; i < n; i++) {
		d[i] = INF;
	}
	d[s] = 0;
	set < pair<long long, long long> > q;
	q.insert(make_pair(d[s], s));
	while (!q.empty()) {
		int v = q.begin()->second;
		q.erase(q.begin());
		for (int j = 0; j < g[v].size(); ++j) {
			int to = g[v][j].first,
				len = g[v][j].second;
			if (d[v] + len < d[to]) {
				q.erase(make_pair(d[to], to));
				d[to] = d[v] + len;
				q.insert(make_pair(d[to], to));
			}
		}
	}
	fout << d[n - 1];
	
	return 0;
}

