#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	if (fin.is_open())
	{
		int N = 0;
		fin >> N;
		int* P = new int[N];
		for (int i = 0; i < N; i++)
			P[i] = 0;
		for (int i = 0; i < N - 1; i++)
		{
			int k = 0, l = 0;
			fin >> k;
			fin >> l;
			P[l - 1] = k;
		}

		fout << P[0];
		for (int i = 1; i < N; i++)
		{
			fout << " " << P[i];
		}
	}

	fin.close();
	fout.close();
}