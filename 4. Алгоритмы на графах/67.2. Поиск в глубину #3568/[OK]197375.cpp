#include <iostream>
#include <fstream>
#include <vector>
#include <stack>
#include <set>
#include <algorithm>

using namespace std;
int I = 1;
struct Point
{
	int I;
    vector <int> S;
	int i;
};

void stacksearch(stack <Point> &S, vector <Point> &P, vector <bool> &IsVisited, vector <int> &Result)
{
	while (!S.empty())
	{
		Point Buff = S.top();
		if (Result[Buff.I] == 0)
		{
			Result[Buff.I] = I++;
		}
		for (int i = Buff.i; i < Buff.S.size(); i++, Buff.i++)
		{
			if (IsVisited[Buff.S[i]] == false)
			{
				IsVisited[Buff.S[i]] = true;
				S.push(P[Buff.S[i]]);
//				Buff.IT = i;
				break;
			}
		}
		if (Buff.i == Buff.S.size())
		{
			S.pop();
		}
	}

}

void recursivesearch(vector <Point> &P, vector <bool> &IsVisited, vector <int> &Result, Point Buff)
{
	Result[Buff.I] = I++;
	for (int i = 0; i < Buff.S.size(); i++, Buff.i++)
	{
		if (IsVisited[Buff.S[i]] == false)
		{
			IsVisited[Buff.S[i]] = true;
			recursivesearch(P, IsVisited, Result, P[Buff.S[i]]);
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
	vector <int> Result(N);
//	stack <Point> S;
	for (int i = 0; i < N; i++)
	{
		Point Buff;
		Buff.I = i;
		Buff.i = 0;
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
				P[i].S.push_back(j);
			}
		}
		sort(P[i].S.begin(), P[i].S.end());
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
//				S.push(P[i]);
//				stacksearch(S, P, IsVisited, Result);
				recursivesearch(P, IsVisited, Result, P[i]);
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
	fout.close();
	return 0;
}