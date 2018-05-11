#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	long n, m;
	fin >> n >> m;
	vector<vector<long>> g;
	vector<long> v;
	g.assign(n, v);
	for (long i = 0; i < m; i++)
	{
		int u, v;
		fin >> u >> v;
		g[u - 1].push_back(v);
		g[v - 1].push_back(u);
	}
	for (int i = 0; i < n; i++)
	{
		fout << g[i].size();
		for (int j = 0; j < g[i].size(); j++)
		{
			fout << " " << g[i][j];
		}
		fout << "\n";
	}
	//system("pause");
	return 0;
}