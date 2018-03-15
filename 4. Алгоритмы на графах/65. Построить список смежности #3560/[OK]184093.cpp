#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

int main()
{
	int n = 0, m = 0;
	fin >> n>>m;
	vector<vector<int>> a(n);
	for (int i = 0; i < m; i++)
	{
		int p,q;
		fin >> p>>q;
		a[p - 1].push_back(q);
		a[q - 1].push_back(p);
	}
	for (int i = 0; i < n; i++)
	{
		fout << a[i].size();
		for (int j=0;j<a[i].size();j++)
                {
			fout << " " << a[i][j];
                }
		fout << endl;
	}
}