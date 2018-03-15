#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

int** A;
int* key;
int k;
int N;

void BFS(int i, int** A, int* key);

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	if (fin.is_open())
	{
		fin >> N;

		k = 0;
		int* key = new int[N];
		int** A = new int*[N];
		for (int i = 0; i < N; i++)
		{
			A[i] = new int[N];
			key[i] = 0;
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				fin >> A[i][j];

		for (int i = 0; i < N; i++)
			if (key[i] == 0)
				BFS(i, A, key);

		for (int i = 0; i < N - 1; i++)
			fout << key[i] << " ";
		fout << key[N - 1];

		delete[] key;
		for (int i = 0; i < N; i++)
			delete[] A[i];
		delete[]A;
	}
	else
		cout << "" << endl;
	fin.close();
	fout.close();
}

void BFS(int i, int** A, int* key)
{
	queue<int> q;
	q.push(i);
	key[i] = ++k;
	while (!q.empty())
	{
		for (int i = 0; i < N; i++)
			if (A[q.front()][i])
			{
				if (key[i] == 0)
				{
					key[i] = ++k;
					q.push(i);
				}
			}
		q.pop();
	}
}