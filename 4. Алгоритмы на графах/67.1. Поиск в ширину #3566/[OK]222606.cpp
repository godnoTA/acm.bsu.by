#include <iostream>
#include <fstream>
#include <algorithm>
#include <queue>

using namespace std;

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	int n,k(1);
	int mas[105][105];
	int otv[105];
	f1 >> n;
	for (int i = 0; i < 105; i++) otv[i] = 0;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			f1 >> mas[i][j];
	queue <int> och;
	for (int i = 1; i <= n; i++)
	{
		if (otv[i] == 0)
		{
			otv[i] = k++;
			och.push(i);
			while (!och.empty())
			{
				int d = och.front();
				och.pop();
				for (int j = 1; j<=n; j++)
					if (mas[d][j] == 1 && otv[j] == 0)
					{
						otv[j] = k++;
						och.push(j);
					}
			}
		}
	}
	for (int i = 1; i <= n; i++) f2 << otv[i] << ' ';
	f1.close();
	f2.close();
	return 0;
}