#include <iostream>
#include <vector>
#include <queue>
#include <fstream>

using namespace std;


int bfs(int s, vector<vector<int>>& g) {
	vector<bool> used(g.size(), false);
	queue<int> q;
	q.push(s);
	used[s] = true;
	size_t time, verts = time = 0;
	while (!q.empty()) {
		int k = q.size();
		time++;
		verts += k;
		for (int j = 0; j < k; j++) {
			int v = q.front();
			q.pop();
			for (size_t i = 0; i < g[v].size(); ++i) {
				int to = g[v][i];
				if (!used[to]) {
					used[to] = true;
					q.push(to);
				}
			}
		}
	}
	return verts == g.size() ? time - 1 : -1;
}

int main() {
	ifstream fin("in.txt");
	int n;
	fin >> n;
	vector<vector<int>> g(n);
	for (int i = 0; i < n; i++) {
		int k;
		fin >> k;
		for (int j = 0; j < k; j++) {
			int elem;
			fin >> elem;
			g[i].push_back(elem - 1);
		}
	}
	int vert, time = -1;
	for (int i = 0; i < n; i++) {
		int nt = bfs(i, g);
		if (nt >= time) {
			vert = i + 1;
			time = nt;
		}
	}
	ofstream fout("out.txt");
	if (time > -1) {
		fout << time << '\n' << vert;
	}
	else {
		fout << "impossible";
	}
}