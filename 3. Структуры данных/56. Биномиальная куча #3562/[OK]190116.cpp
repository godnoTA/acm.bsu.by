#include <iostream>
#include <fstream>
#include <cmath>
using namespace std;

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	unsigned long long N;
	fin >> N;
	int razmer = (log10(N) / log10(2)) + 1;
	int *ar = new int[razmer];
	int i = 0;
	while (N > 0) {
		ar[i] = N % 2;
		N = N / 2;
		i++;
	}
	for (int i = 0; i < razmer; i++)
		if (ar[i] == 1) fout << i << endl;

	return 0;
}