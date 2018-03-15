#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <set>
using namespace std;
typedef set <int>::iterator it;
int I = 1;
struct Point
{
	int I;
	set <int> S;
};
void queuesearch(queue <Point> &Q, vector <Point> &P, vector <bool> &IsVisited, vector <int> &Result)
{
	while (!Q.empty())
	{
		Point Buff = Q.front();
		Q.pop();
		Result[Buff.I] = I++;
//		fout << Buff.I + 1 << " ";
		for (it i = Buff.S.begin(); i != Buff.S.end(); i++)
		{
			if (IsVisited[*i] == false)
			{
				IsVisited[*i] = true;
				Q.push(P[*i]);
			}
		}
	}
	
}
int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int N;
	fin >> N;
	vector <bool> IsVisited;
	vector <Point> P;
	queue <Point> Q;
	vector <int> Result(N);
	for (int i = 0; i < N; i++)
	{
		Point Buff;
		Buff.I = i;
		P.push_back(Buff);
		IsVisited.push_back(false);
	}
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			int Buff;
			fin >> Buff;
			if (Buff == 1)
			{
				P[i].S.insert(j);
			}
		}
	}
	fin.close();
	bool isFinished = false;
	while (!isFinished)
	{
		int i;
		for (i = 0; i < N; i++)
		{
			if (IsVisited[i] == false)
			{
				IsVisited[i] = true;
				Q.push(P[i]);
				queuesearch(Q, P, IsVisited, Result);
				break;
			}
		}
		if (i == N)
		{
			isFinished = true;
		}
	}
	for (int i = 0; i < N; i++)
	{
		fout << Result[i] << " ";
	}
//	fout.close();
/*/	for (int i = 0; i < N; i++)
	{
		fout << Result[i] << " ";
	}*/
	fout.close();
	return 0;
}