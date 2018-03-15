#include <iostream>
#include <fstream>

using namespace std;


int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;

	int* Array = new int[n];
	for (int i = 0; i < n; i++)
		Array[i] = 0;
	int value1, value2;
	for (int i = 0; i < n-1;i++) {
		fin >> value1;
		fin >> value2;
		Array[value2 - 1] = value1;
	}
	for (int i = 0; i < n; i++)
		fout << Array[i] << " ";
	fin.close();
	return 0;
}