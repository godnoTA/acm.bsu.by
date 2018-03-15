#include <iostream>
#include <fstream>

using namespace std;


int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n,m;

	fin >> n;
	fin >> m;

	int** matrix=new int*[n];
	for (int i = 0; i < n; i++)
		matrix[i] = new int[n];
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			matrix[i][j] = 0;
	int value1, value2;;
	for (int i = 0; i < m;i++) {
		fin >> value1;
		fin >> value2;
		matrix[value1 - 1][value2 - 1] = matrix[value2-1][value1-1] = 1;
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			fout << matrix[i][j] << " ";
		}
		fout << endl;
	}
	fin.close();
	return 0;
}