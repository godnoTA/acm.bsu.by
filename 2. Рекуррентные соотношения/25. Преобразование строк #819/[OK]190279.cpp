#include <iostream>
#include <string>
#include <fstream>
using namespace std;
int main()
{
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	int x, y, z;
	string A;
	string B;
	fin >> x;// Удаление
	fin >> y;// Вставка
	fin >> z;// Замена
	getline(fin, A);
	getline(fin, A);
	getline(fin, B);
	fin.close();
	int n = A.size() + 1;
	int m = B.size() + 1;
	int **Matr = new int *[n];
	for (int i = 0; i < n; i++)
	{
		Matr[i] = new int[m];
	}
	for (int i = 0; i < n; i++)
	{
		Matr[i][0] = i * x;//Удаление
	}
	for (int i = 0; i < m; i++)
	{
		Matr[0][i] = i * y;//Вставка
	}
	for (int i = 1; i < n; i++)
	{
		for (int j = 1; j < m; j++)
		{
			int Change;
			if (A[i - 1] != B[j - 1])
			{
				Change = Matr[i - 1][j - 1] + z;
			}
			else
			{
				Change = Matr[i - 1][j - 1];
			}
			int min = Matr[i - 1][j] + x;
			if (min > Change)
			{
				min = Change;
			}
			if (min > Matr[i][j - 1] + y)
			{
				min = Matr[i][j - 1] + y;
			}
			Matr[i][j] = min;
		}
	}
/*	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			cout << Matr[i][j] << " ";
		}
		cout << endl;
	}*/
	fout << Matr[n - 1][m - 1];
	fout.close();
}