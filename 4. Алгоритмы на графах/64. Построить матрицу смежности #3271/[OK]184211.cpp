#include <iostream>
#include <fstream>

using namespace std;

int main() {
	ifstream fin("input.txt");
	int n1, n2;
	fin >> n1;
	fin >> n2;

	bool** mas = new bool*[n1];
	for (int i = 0; i < n1; i++)
		mas[i] = new bool[n1];

	for (int i = 0; i < n1; i++) {
		for (int j = 0; j < n1; j++) {
			mas[i][j] = false;
		}
	}

	int I, J;
	for (int i = 0; i < n2; i++) {
		fin >> I;
		fin >> J;
		mas[I - 1][J - 1] = true;
		mas[J - 1][I - 1] = true;
	}
	fin.close();

	ofstream fout("output.txt");
	for (int i = 0; i < n1; i++) {
		for (int j = 0; j < n1 - 1; j++)
			fout << (mas[i][j] == true ? "1 " : "0 ");
		fout << (mas[i][n1-1] == true ? "1\n" : "0\n");
	}


	fout.close();
	
	for (int i = 0; i < n1; i++)
		delete[] mas[i];
	delete[] mas;
	return 0;
}