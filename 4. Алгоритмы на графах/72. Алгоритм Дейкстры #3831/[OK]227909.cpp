// ConsoleApplication1.cpp: определяет точку входа для консольного приложения.
//

#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;
long long d[200000];
//bool blocked[200000];
vector <int> adj_list[200000];
vector <int> weights[200000];

set<pair<long long, int> > blocked;
int main() {
	
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n, m;
	fin >> n >> m;
	for (int i = 0; i < m; i++) {
		int u, v, w;
		fin >> u >> v >> w;
		u--;
		v--;
		adj_list[u].push_back(v);
		weights[u].push_back(w);
		adj_list[v].push_back(u);
		weights[v].push_back(w);
	}
	d[0] = 0;
	for (int i = 1; i < n; i++) {
		d[i] = INT64_MAX - 1;
	}
	int kol_blocked = 1;
	blocked.insert(make_pair(0, 0));
	while (kol_blocked < n) {
		pair<long long, int> min_el = *blocked.begin();
		blocked.erase(min_el);
		kol_blocked++;
		for (int i = 0; i < adj_list[min_el.second].size(); i++) {
			int u = adj_list[min_el.second][i];
			pair<long long, int> p(d[u], u);
			int w = weights[min_el.second][i];
			long long old = d[u];
			d[u] = min(old, d[min_el.second] + w);
			if (old != d[u]) {
				pair<long long, int> p1(old, u);
				pair<long long, int> p2(d[u], u);
				blocked.erase(p1);
				blocked.insert(p2);
			}
		}
	}
	fout << d[n - 1];
	return 0;
}

