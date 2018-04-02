#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	int n, a, b;
	int p[105];
	int mas[105][105];
	f1 >> n;
	for (int i = 1; i <= n; i++)
	{
		f1 >> a >> b;
		p[i - 1] = a;
	}
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
			mas[i][j] = 2000000000;
	p[n] = b;
	for (int i = 1; i <= n; i++)
		for (int j = i; j >= 1; j--)
			if (i == j) mas[j][i] = 0;
			else
				for (int k = j; k < i; k++)
					mas[j][i] = min(mas[j][i], mas[j][k] + mas[k + 1][i] + p[j - 1] * p[k] * p[i]);
	f2 << mas[1][n];
	f1.close();
	f2.close();
	return 0;
}