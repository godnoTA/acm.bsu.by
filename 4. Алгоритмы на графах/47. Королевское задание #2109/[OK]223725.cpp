//#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

vector<int> tout, a[444444], b[444444], c[444444];
int n, p[444444], f[444444], col = 1;

void dfs(int v) {
	if (f[v]) return;
	f[v] = 1;
	for (int i = 0; i < a[v].size(); i++) dfs(a[v][i]);
	tout.push_back(v);
}

void dfsr(int v) {
	if (f[v] != 1) return;
	f[v] = col;
	for (int i = 0; i < b[v].size(); i++) dfsr(b[v][i]);
}

int main()
{
	ifstream cin("king.in");
	ofstream cout("king.out");
	cin >> n;
	for (int i = 1; i <= n; i++) {
		int k;
		int x;
		cin >> k;
		for (int j = 0; j < k; j++) {
			cin >> x;
			c[i].push_back(x);
		}
	}
	for (int i = 1; i <= n; i++) cin >> p[i];
	int x;
	for (int i = 1; i <= n; i++) {
		for (int j = 0; j < c[i].size(); j++) {
			x = c[i][j] + n;
			if (c[i][j] == p[i]) {
				a[i].push_back(x);
				b[x].push_back(i);
			}
			else {
				a[x].push_back(i);
				b[i].push_back(x);
			}
		}
	}
	for (int i = 1; i <= 2 * n; i++)
		if (!f[i]) dfs(i);
	for (int i = tout.size() - 1; i >= 0; i--) if (f[tout[i]] == 1) {
		col++;
		dfsr(tout[i]);
	}
	vector<int> ans;
	for (int i = 1; i <= n; i++) {
		ans.clear();
		for (int j = 0; j < c[i].size(); j++)
			if (c[i][j] == p[i] || f[i] == f[c[i][j] + n])
				ans.push_back(c[i][j]);
		cout << ans.size();
		for (int j = 0; j < ans.size(); j++) cout << " " << ans[j];
		cout << endl;
	}
    return 0;
}