#include <iostream>
#include <fstream>
#include <vector>
#include <list>
using namespace std;
int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int N;
	fin >> N;
	vector <int> Result;
	for (int i = 0; i < N; i++)
	{
		Result.push_back(0);
	}
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			int Buff;
			fin >> Buff;
			if (Buff == 1)
			{
				Result[j] = i + 1;
			}
		}
	}
	fin.close();
	for (int i = 0; i < N; i++)
	{
		fout << Result[i] << " ";
	}
	fout.close();
	return 0;
}