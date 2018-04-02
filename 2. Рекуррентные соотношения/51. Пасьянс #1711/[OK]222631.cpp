#include <iostream>
#include <fstream>
using namespace std;

int mod(int a)
{
	if (a < 0) return -a;
	return a;
}

int minim(int a, int b)
{
	if (a < b) return a;
	return b;
}

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	int n;
	int dan[505];
	int pos[505];
	int **mas = new int*[505];
	for (int i = 0; i < 505; i++)
		mas[i] = new int[505];
	for (int i = 0; i < 505; i++)
		for (int j = 0; j < 505; j++)
			mas[i][j] = 50000;
	f1 >> n;
	for (int i = 1; i <= n; i++)
	{
		f1 >> dan[i];
		pos[dan[i]] = i;
	}
	for (int i = 1; i <= n; i++)
		for (int j = i; j >= 1; j--)
		{
			if (i == j) mas[j][i] = 0;
			else
			for (int k = j; k < i; k++)
			{
				mas[j][i] = minim(mas[j][i], mas[j][k] + mas[k + 1][i] + mod(pos[k] - pos[i]));
			}
		}
	f2 << mas[1][n];
	for (int i = 0; i < 505; i++)
		delete mas[i];
	delete mas;
	f1.close();
	f2.close();
	return 0;
}