
#include <iostream>
#include <fstream>
#include <queue>
#include <algorithm>
#include <string>
using namespace std;

int mas[101][101];
bool visit[101];
int path[101];
int label = 0;

void dfs(int j, int n) {
	label = label + 1;
	path[j] = label;
	visit[j] = true;

	for (int i = 0; i < n; i++)
	{
		if (mas[j][i] == 1 && visit[i] == 0)
		{
			visit[i] = true;
			path[i] = label + 1;
			dfs(i, n);

		}
	}
}

int main()
{
	ifstream fin("input.txt", ios_base::in);
	ofstream fout("output.txt", ios_base::out);
	int n;
	fin >> n;

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			fin >> mas[i][j];
			path[i] = 0;
			visit[i] = false;
		}
	}

	for (int j = 0; j < n; j++)
	{
		if (visit[j] == 0) {
			dfs(j, n);
		}
	}

	for (int i = 0; i < n; i++)
	{
		fout << path[i] << " ";

	}

	fout.close();
	return 0;
}