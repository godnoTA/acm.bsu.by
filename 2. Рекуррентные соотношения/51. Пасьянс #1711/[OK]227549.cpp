#include <iostream>
#include <fstream>
#include "math.h"
#include <algorithm>
using namespace std;

ifstream in("input.txt");
ofstream out("output.txt");

int main()
{
	int n;
	in >> n;
	int *mast =new int [n+1] ;
	int **mas = new int*[n+1];
	for (int i = 0; i < n + 1; i++)
	{
		mas[i] = new int[n + 1];
	}
	for (int i = 0; i < n + 1; i++)
	{
		for (int j = 0; j < n + 1; j++)
		{
			mas[i][j] = n*n;
			if (i == j)
			{
				mas[i][j] = 0;
			}
		}
	}
	for (int i = 1; i <= n; i++)
	{
		int t;
		in >> t;
		mast[t] = i;
	}
	for (int i = 1; i <= n; i++)
		for (int j = i; j >= 1; j--)
		{
			if (mas[j][i] != 0) {
				for (int k = j; k < i; k++)
				{
					mas[j][i] = min(mas[j][i], mas[j][k] + mas[k + 1][i] + abs(mast[k] - mast[i]));
				}
			}
		}
	out << mas[1][n];
	return 0;
}