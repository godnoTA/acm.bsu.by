#include <iostream>
#include <fstream>
using namespace std;
int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int N, M;
	fin >> N >> M;
	int **Matr = new int*[N];
	for (int i = 0; i < N; i++)
	{
		Matr[i] = new int[N] {0};
	}
	for (int i = 0; i < M; i++)
	{
		int I, J;
		fin >> I >> J;
		Matr[I - 1][J - 1] = 1;
		Matr[J - 1][I - 1] = 1;
	}
	fin.close();
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			fout << Matr[i][j] << " ";
		}
		fout << endl;
	}
	fout.close();
}