#include <iostream>
#include <fstream>
#include <vector>
#include <queue>

using namespace std;

	vector<vector<int>> g;
	vector<int> used;
	int n;
	int use;

	void bfs(int s) {
		queue<int> q;
		q.push(s);
		vector<int> d(n), p(n);
		used[s] = use;
		p[s] = -1;
		use++;
		while (!q.empty()) {
			int v = q.front();
			q.pop();
			for (size_t i = 0; i < g[v].size(); ++i) {
				int to = g[v][i];
				if (!used[to]) {
					used[to] = use;
					use++;
					q.push(to);
					d[to] = d[v] + 1;
					p[to] = v;
				}
			}
		}
	}

int main() {

	ifstream fin("input.txt");
	fin >> n;
	g.resize(n);
	used.resize(n);
	use = 1;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int tmp;
			fin >> tmp;
			if (tmp == 1) {
				g[i].push_back(j);
			}
		}
	}
	int j = 0;
	while (use <= n) {
		for (j; j < n; j++) {
			if (used[j] == 0) {
				bfs(j);
				break;
			}
		}
		cout << use;
	}

	ofstream fout("output.txt");
	for (auto j : used) {
		fout << j << " ";
	}
}