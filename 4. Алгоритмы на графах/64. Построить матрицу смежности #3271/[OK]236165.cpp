#include <iostream>
#include <fstream>
#include<vector>
using namespace std;
ifstream fin("input.txt");
ofstream fout("output.txt");

int main()
{
	int n, m;
	fin >> n >> m;

	vector<vector<int> > arr(n+1, vector<int>(n+1,0));
	for (unsigned int i = 0; i < m; i++)
	{
		int a, b;
		fin >> a;
		fin >> b;
		arr[a][b] = arr[b][a] = 1;
	}
	fin.close();
	for (unsigned int i = 1; i < n + 1; i++)
	{
		for (unsigned int j = 1; j < n + 1; j++)
		{
			cout << arr[i][j] << " ";
			fout << arr[i][j] << " ";
		}
		cout << "\n";
		fout << "\n";
	}
	fout << "\n";
	return 0;
}