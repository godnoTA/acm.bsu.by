#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n, m;
	fin >> n >> m;
	int **a = new int*[n];
	for (int i = 0; i < n; i++)
	{
		a[i] = new int[n];
		for (int j = 0; j < n; j++)
			a[i][j] = 0;
	}

	for (int i = 0; i < m; i++)
	{
		int u, v;
		fin >> u >> v;
		a[u - 1][v - 1] = 1;
		a[v - 1][u - 1] = 1;
	}
	
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			fout << a[i][j] << " ";
		}
		fout << "\n";
	}
	//system("pause");
	return 0;
}