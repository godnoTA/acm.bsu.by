#include <fstream>
#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int N = 0;
	fin >> N;
	
	int*n = new int[N];
	int*m = new int[N];

	for (int i = 0; i < N; i++) {
		fin >> n[i] >> m[i];
	}

	int**matrix = new int*[N];
	for (int i = 0; i < N; i++)
		matrix[i] = new int[N];

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			matrix[i][j] = 2000000000;

	for (int j = 0; j < N; j++)
		for (int i = j; i >= 0; i--)
		{
			if (i == j) matrix[i][j] = 0;
			else if (abs(i - j) == 1) {
				matrix[i][j] = n[i] * m[i] * m[i + 1];
			}
		
			else
				for (int k = i; k < j; k++)
					matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k + 1][j] + n[i] * m[k] * m[j]);
		}

	fout << matrix[0][N - 1] << endl;

	return 0;
}