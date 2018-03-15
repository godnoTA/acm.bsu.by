#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int N, M;
	int a, b;
	fin >> N >> M;
	int **matrix = new int*[N];
	for (int i = 0; i < N; i++) {
		matrix[i] = new int[N];		
	}
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			matrix[i][j] = 0;

	for (int i = 0; i < M; i++)	{
		fin >> a >> b;
		matrix[a-1][b-1] = 1;
		matrix[b-1][a-1] = 1;
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++)
			fout << matrix[i][j] << " ";
		fout << endl;
	}
	return 0;
}