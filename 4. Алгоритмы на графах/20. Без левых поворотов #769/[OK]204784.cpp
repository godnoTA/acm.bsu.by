#include <iostream>
#include <cstdio>
#include <fstream>
#include <stack>
#include <map>
#include <queue>
using namespace std;

int main() {
	FILE *in;
	FILE *out;
	freopen_s(&in, "input.txt", "r", stdin);
	freopen_s(&out, "output.txt", "w", stdout);

	int N, M, node1, node2, start, finish;
	long long x1, x2, y1, y2;
	fscanf_s(in, "%d %d", &N, &M);

	map<int, pair<int, int>> mp;
	map<pair<int, int>, pair<int, int>> path;
	int**matrix = new int*[N];
	for (int i = 0; i < N; i++)
		matrix[i] = new int[N];

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			matrix[i][j] = 0;

	vector<pair<long long, long long>> c(N + 1);

	for (int i = 0; i < M; i++)
	{
		cin >> x1 >> y1 >> x2 >> y2 >> node1 >> node2;
		c[node1 - 1] = make_pair(x1, y1);
		c[node2 - 1] = make_pair(x2, y2);
		matrix[node1 - 1][node2 - 1] = 1;
		matrix[node2 - 1][node1 - 1] = 1;
	}
	fscanf_s(in, "%d %d", &start, &finish);

	start--;
	finish--;

	c[N] = make_pair(c[start].first, c[start].second - 1);
	queue<pair<int, int>> searcher;
	searcher.push(make_pair(N, start));

	path[make_pair(N, start)] = make_pair(-1, -1);
	vector<pair<int, int>> arcs;

	while (!searcher.empty()) {
		pair<int, int> del = searcher.front();
		int from = del.first;
		int to = del.second;

		searcher.pop();

		if (to == finish) {
			fprintf_s(out, "Yes\n");
			arcs.push_back(del);
			pair<int, int> index;
			while (del.first != -1 && del.second != -1) {
				arcs.push_back(path[del]);
				del = path[del];
			}
			for (int i = arcs.size() - 2; i >= 0; i--)
				if (i != 0)
					fprintf_s(out, "%d ", (arcs[i].second + 1));
				else
					fprintf_s(out, "%d", (arcs[i].second + 1));
			return 0;
		}
		for (int k = 0; k < N; k++)
			if (matrix[to][k] == 1) {
				//проверка на то откуда пришел
				if (!(c[from].first == c[k].first && c[from].second == c[k].second)) {
					//подсчет определителя
					//|from.x from.y 1|
					//|cur.x  cur.y  1|
					//|togo.x togo.y 1|
					long long CENTR = c[from].first * c[to].second + c[from].second * c[k].first + c[to].first * c[k].second
						- c[k].first * c[to].second - c[from].second * c[to].first - c[from].first * c[k].second;
					if (CENTR < 0 || CENTR == 0) {
						searcher.push(make_pair(to, k));
						matrix[to][k] = 0;
						path[make_pair(to, k)] = del;
					}
				}
			}
	}
	fprintf_s(out, "No");
	return 0;
}