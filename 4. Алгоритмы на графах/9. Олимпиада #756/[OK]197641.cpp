#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
using namespace std;
bool FindFunc(queue <int> Q, vector <vector <int>> M, int i, int N)
{
	bool *IsVisited = new bool[N] { false };
	for (unsigned int k = 1; k < N; k++)
	{
		if (M[0][k] == 1)
		{
			IsVisited[k] = true;
		}
	}
	while (!Q.empty())
	{
		int Buff = Q.front();
		Q.pop();
		if (Buff == i)
		{
			return true;
		}
		for (int j = 1; j < N; j++)
		{
			if (M[Buff][j] == 1 && !IsVisited[j])
			{
				IsVisited[j] = true;
				Q.push(j);
			}
		}
	}
	return false;
}
int main()
{
	ifstream fin("input.in");
	ofstream fout("output.out");
	int N;
	fin >> N;
	vector <vector <int>> M;
	for (unsigned int i = 0; i < N; i++)
	{
		vector <int> T;
		M.push_back(T);
		for (unsigned int j = 0; j < N; j++)
		{
			int Buff;
			fin >> Buff;
			M[i].push_back(Buff);
		}
	}
	fin.close();
//////—читывание завершено
	queue <int> Q;
	for (unsigned int i = 1; i < N; i++)
	{
		if (M[0][i] == 1)
		{
			Q.push(i);
		}
	}
	for (unsigned int i = 1; i < N; i++)
	{
		if (M[0][i] == 0)
		{
			if (!FindFunc(Q, M, i, N))
			{
				fout << "NO"; 
				fout.close();
				return 0;
			}
		}
	}
	fout << "YES";
	fout.close();
	return 0;
}