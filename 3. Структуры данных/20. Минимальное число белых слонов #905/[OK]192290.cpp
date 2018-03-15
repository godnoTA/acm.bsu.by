#include <iostream>
#include <fstream>
#include <queue>
using namespace std;
int D = 1;
void paintFunc(int **table, int i, int j, const int N, const int M)
{
	queue <pair <int, int>> Q;
	Q.push(make_pair(i, j));
	table[i][j] = D;
	while (!Q.empty())
	{
		pair <int, int> Buff = Q.front();
		Q.pop();
		if ((i = Buff.first - 1) >= 0 && (j = Buff.second - 1) >= 0)//¬верх влево
		{
			if (table[i][j] == 0)
			{
				table[i][j] = D;
				Q.push(make_pair(i, j));
			}
		}
		if ((i = Buff.first - 1) >= 0 && (j = Buff.second + 1) < M)//¬верх вправо
		{
			if (table[i][j] == 0)
			{
				table[i][j] = D;
				Q.push(make_pair(i, j));
			}
		}
		if ((i = Buff.first + 1) < N && (j = Buff.second - 1) >= 0)//¬низ влево
		{
			if (table[i][j] == 0)
			{
				table[i][j] = D;
				Q.push(make_pair(i, j));
			}
		}
		if ((i = Buff.first + 1) < N && (j = Buff.second + 1) < M)//¬низ вправо
		{
			if (table[i][j] == 0)
			{
				table[i][j] = D;
				Q.push(make_pair(i, j));
			}
		}
	}
	D++;
}
int main()
{
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	int N, M;
	fin >> N;
	fin >> M;
	int **table = new int *[N];
	for (int i = 0; i < N; i++)
	{
		table[i] = new int[M];
	}
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			fin >> table[i][j];
		}
	}
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			if (table[i][j] == 0)
			{
				paintFunc(table, i, j, N, M);
			}
		}
	}
/*	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cout << table[i][j] << " ";
		}
		cout << endl;
	}*/
	fin.close();
	fout << D - 1;
	fout.close();
//	cout << D - 1 << endl;
}