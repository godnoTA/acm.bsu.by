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
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				int k = 0;
				fin >> k;
				if (k == 1)
					P[j] = i + 1;
			}
		fout << P[0];
		for (int i = 1; i < N; i++)
			fout << " " << P[i];
	}

	fin.close();
	fout.close();
}