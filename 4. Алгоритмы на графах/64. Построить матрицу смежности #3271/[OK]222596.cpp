#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	int n, m,a,b;
	int mas[105][105];
	for (int i = 0; i < 105; i++)
		for (int j = 0; j < 105; j++)
			mas[i][j] = 0;
	f1 >> n >> m;
	for (int i = 0; i < m; i++)
	{
		f1 >> a >> b;
		mas[a-1][b-1] = 1;
		mas[b - 1][a - 1] = 1;
	}
	for (int i = 0; i < n; i++)
	{
		if (i != 0) f2 << endl;
		for (int j = 0; j < n; j++)
		{
			f2 << mas[i][j];
			if (j != n - 1) f2 << ' ';
		}
	}
	f1.close();
	f2.close();
	return 0;
}