#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int k = 0;

void dfs(int i, vector<vector<int>> m, vector<bool> &used, int n, vector<int> &res) {
	used[i] = true;
	res[i] = ++k;
	for (int j = 0; j < n; j++)
		if (m[i][j] == 1 && !used[j]) {
			dfs(j, m, used, n, res);
		}
}

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;
	vector< vector<int> > matr;
	vector<bool> used(n);
	for (int i = 0; i < n; i++)
	{
		vector <int> v;
		for (int i = 0; i < n; i++)
		{
			int c;
			fin >> c;
			v.push_back(c);
		}
		matr.push_back(v);
	}

	vector<int>res(n);
	dfs(0, matr, used, n, res);
	while (true) {
		int c;
		for (c = 0; c < n; c++)
		{
			if (!used[c])
				break;
		}
		if (c < n) {
			dfs(c, matr, used, n, res);
		}
		else break;
	}
	for (int i = 0; i < n; i++)
	{
		fout << res[i] << " ";
	}
	return 0;
}