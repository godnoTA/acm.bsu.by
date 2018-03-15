#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
/*struct Node
{
	int Oper;
	int k;
};*/
int main()
{
	setlocale(LC_ALL, "rus");
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	vector <pair<int, int>> Matr;
	int N;
	fin >> N;
	int n;
	int m;
	while (fin >> n)
	{
		fin >> m;
		Matr.push_back(make_pair(n, m));
	}
	fin.close();
/*	for (int i = 0; i < Matr.size(); i++)
	{
		cout << Matr[i].first << " " << Matr[i].second << endl;
	}*/
	int **F = new int *[N];
	for (int i = 0; i < N; i++)
	{
		F[i] = new int[N];
	}
/*	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			F[i][j] = 0;
		}
	}*/
//	F[1][1] = 0;
	int k = 0;
	while (k < N)
	{
		for (int i = k; i < N; i++)
		{
			int j = i - k;
			if (i == j)
			{
				F[i][j] = 0;
			}
			else
			{
				if (k == 1)
				{
					int buff = Matr[j].first * Matr[j].second * Matr[i].second;
					F[j][i] = buff;
				}
				else
				{
					int min;
					for (int d = j; d < j + k; d++)
					{
						if (d == j)
						{
							min = F[j][d] + F[d + 1][i] + Matr[j].first * Matr[d + 1].first * Matr[i].second;
						}
						else
						{
							int buff = F[j][d] + F[d + 1][i] + Matr[j].first * Matr[d + 1].first * Matr[i].second;
							if (min > buff)
							{
								min = buff;
							}
						}
					}
					F[j][i] = min;
				}
				
			}
		}
		k++;
	}
	cout << "Result: " << F[0][N - 1] << endl;
	fout << F[0][N - 1];
	fout.close();
}