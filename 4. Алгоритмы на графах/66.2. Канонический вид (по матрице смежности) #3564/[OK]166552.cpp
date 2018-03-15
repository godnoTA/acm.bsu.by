#include <iostream>
#include <fstream>

using namespace std;


int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;

	int** matrix = new int*[n];
	for (int i = 0; i < n; i++)
		matrix[i] = new int[n];
	int value;
	for (int i = 0; i < n;i++) {
		for (int j = 0; j < n; j++) {
			fin >> matrix[i][j];
		}
	}
	int root = 0;
	for (int j = 0; j < n; j++) {
		root = 0;
		for (int i = 0; i < n; i++) {
			if (matrix[i][j] == 1) {
				fout << i + 1 << " ";
			}
			if (matrix[i][j] == 0)
				root++;
		}
		if (root == n)
			fout << 0 << " ";
	}
	fin.close();
	return 0;
}