#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	if (fin.is_open())
	{
		int N = 0, M = 0;
		fin >> N;
		fin >> M;
		int** A = new int*[N];
		for (int i = 0; i < N; i++)
			A[i] = new int[N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				A[i][j] = 0;
		for (int i = 0; i < M; i++)
		{
			int k, l;
			fin >> k;
			fin >> l;
			A[k - 1][l - 1] = A[l - 1][k - 1] = 1;
		}
		for (int i = 0; i < N; i++)
		{
			fout << A[i][0];
			for (int j = 1; j < N; j++)
				fout << " " << A[i][j];
			fout << endl;
		}
	}

	fin.close();
	fout.close();
}