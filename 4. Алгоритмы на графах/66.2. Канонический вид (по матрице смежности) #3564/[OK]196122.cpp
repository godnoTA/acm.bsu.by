#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int N;
	fin >> N;

	vector<int> v(N);

	int **matrix = new int*[N];	
	for (int i = 0; i < N; i++)
		matrix[i] = new int[N];

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			fin >> matrix[i][j];

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			if (matrix[i][j] == 1)
				v[j] = i + 1;

	for (int i = 0; i < N; i++)
		fout << v[i] << " ";

	return 0;
}