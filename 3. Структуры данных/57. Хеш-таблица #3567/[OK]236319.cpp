#include <iostream>
#include <fstream>
#include <set>
#include <iterator>
using namespace std;
ifstream fin("input.txt");
ofstream fout("output.txt");
int m, n, c;

void Insert(int *T, int k) {
	int i = 0;
	while (i != m) {
		int j = ((k % m) + c * i) % m;
		if (T[j] == k)
			return;
		if (T[j] == -1) {
			T[j] = k;
			return;
		}
		i++;
	}
}

int main() {
	fin >> m;
	fin >> c;
	fin >> n;
	int i = 0;
	int* T = new int[m];
	for (i = 0; i < m; i++) {
		T[i] = -1;
	}
	int k;
	int j = 0;
	bool b = true;
	while (j != n) {
		fin >> k;
		Insert(T, k);
		j++;
	}
	fin.close();
	for (int i = 0; i < m; i++) {
		fout << T[i] << " ";
	}
	fout << "\n";
	fout.close();
	return 0;
}