#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

int N, v, w;

void BFS(int j, int** A, int* d)
{
	queue<int> q;
	q.push(--j);
	while (!q.empty())
	{
		for (int i = 0; i < N; i++)
			if (A[q.front()][i])
			{
				if (d[i] == -1)
				{
					d[i] = q.front();
					q.push(i);
				}
				if (i == w - 1)
					return;
			}
		q.pop();
	}
}
//
//int c_min(int* d, int** A)
//{
//	int i = w - 1;
//	int c = A[d[i]][i];
//	while(i != v)
//	{
//		if (c < A[d[i]][i])
//			c = A[d[i]][i];
//		i = d[i];
//	}
//	return c;
//}

void newC(int* d, int** A)
{
//	int c = c_min(d, A);
	int c = 1;
	int i = w - 1;
	while (i != v - 1)
	{
		A[d[i]][i] -= c;
		A[i][d[i]] += c;
		i = d[i];
	}
}

int MaxFlow(int** A)
{
	int* d = new int[N];
	for (; ;)
	{
		for (int i = 0; i < N; i++)
			d[i] = -1;
		BFS(v, A, d);
		if (d[w - 1] == -1)
			break;
		newC(d, A);
	}
	int count = 0;
	for (int i = 0; i < N; i++)
		if (A[i][v - 1] == 2)
			count++;
	delete[]d;
	return count;
}

int main()
{
	ifstream fin("input.in");
	ofstream fout("output.out");

	if (fin.is_open())
	{
		int M;
		fin >> N;
		fin >> M;

		int** A = new int*[N];
		for (int i = 0; i < N; i++)
			A[i] = new int[N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				A[i][j] = 0;

		for (int i = 0; i < N; i++)
		{
			int n = 0;
			for (; ;)
			{
				fin >> n;
				if (n != 0)
					A[i][n - 1] = A[n - 1][i] = 1;
				else
					break;
			}
		}
		fin >> v;
		fin >> w;

		fout << MaxFlow(A);

		//for (int i = 0; i < N; i++)
		//{
		//	for (int j = 0; j < N; j++)
		//		cout << A[i][j] << " ";
		//	cout << endl;
		//}
	}

	fin.close();
	fout.close();
}