#include <iostream>
#include <sstream>
#include <iomanip>
#include <climits>
#include <stdio.h>
#include <vector>
#include <queue>
#include <cmath>

using namespace std;

vector< vector<int> > a;
vector<int> group_1;
vector<int> group_2;

string line;
int value;
int n;

void read_string(vector<int> &dest) {
	getline(cin, line);
	istringstream str_stream(line);
	while (str_stream >> value)
		dest.push_back(value);
}

void veng() {
	vector<int> u(n + 1), v(n + 1), p(n + 1), way(n + 1);
	for (int i = 1; i <= n; ++i) {
		p[0] = i;
		int j0 = 0;
		vector<int> minv(n + 1, INT_MAX);
		vector<char> used(n + 1, false);
		do {
			used[j0] = true;
			int i0 = p[j0], delta = INT_MAX, j1;
			for (int j = 1; j <= n; ++j)
				if (!used[j]) {
					int cur = a[i0][j] - u[i0] - v[j];
					if (cur < minv[j])
						minv[j] = cur, way[j] = j0;
					if (minv[j] < delta)
						delta = minv[j], j1 = j;
				}
			for (int j = 0; j <= n; ++j)
				if (used[j])
					u[p[j]] += delta, v[j] -= delta;
				else
					minv[j] -= delta;
			j0 = j1;
		} while (p[j0] != 0);
		do {
			int j1 = way[j0];
			p[j0] = p[j1];
			j0 = j1;
		} while (j0);
	}
	cout << fixed << setprecision(1) << -v[0] / 2.0 << endl;
}

void build_matrix() {
	n = max(group_1.size(), group_2.size());
	a.assign(n + 1, vector<int>());
	for (int i = 0; i < n; i++) {
		a[i + 1].assign(n + 1, 0);
		for (int j = 0; j < n; j++) {
			if (group_1.size() == n) {
				if (j >= group_2.size()) a[i + 1][j + 1] = group_1[i];
				else a[i + 1][j + 1] = 2 * abs(group_1[i] - group_2[j]);
			}
			else {
				if (j >= group_1.size()) a[i + 1][j + 1] = group_2[i];
				else a[i + 1][j + 1] = 2 * abs(group_1[j] - group_2[i]);
			}
		}
	}
}

int main() {
	ios::sync_with_stdio(false);
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	read_string(group_1);
	read_string(group_2);
	build_matrix();
	veng();
}