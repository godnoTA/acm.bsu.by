#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

void dfs(int mas[105][105], int n, int *otv, int &k, int h)
{
	otv[h] = k++;
	for (int i = 1; i <= n; i++)
		if (mas[h][i] == 1 && otv[i] == 0)
			dfs(mas, n, otv, k, i);

}

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	int n;
	int mas[105][105];
	int k = 1;
	int otv[105];
	f1 >> n;
	for (int i = 0; i < 105; i++) otv[i] = 0;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			f1 >> mas[i][j];
	for (int i = 1; i <= n; i++)
		if (otv[i] == 0)
			dfs(mas, n, otv, k, i);
	for (int i = 1; i <= n; i++) f2 << otv[i] << ' ';
	f1.close();
	f2.close();
	return 0;
}