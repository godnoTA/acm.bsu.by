#include <fstream>
#include <iostream>
#include <algorithm>
using namespace std;


int maxArr(int *a, int n) {
	int max = a[0];
	for (int i = 1; i < n; i++) {
		if (a[i] > max)
			max = a[i];
	}
	return max;
}

int MAT[1001][1001];
int MAX[1001][1001];

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j <= i; j++)
			fin >> MAT[i][j];
	}
	MAX[0][0] = MAT[0][0];
	if (n == 1) {
		fout << MAX[0][0];
	}
	else {
		MAX[1][0] = MAT[1][0] + MAX[0][0];
		MAX[1][1] = MAT[1][1] + MAX[0][0];
		for (int i = 2; i < n; i++) {
			MAX[i][0] = MAX[i-1][0] + MAT[i][0];
			for (int j = 1; j <= i - 1; j++) {
				MAX[i][j] = max(MAX[i - 1][j - 1], MAX[i - 1][j]) + MAT[i][j];
			}
			MAX[i][i] = MAX[i - 1][i - 1] + MAT[i][i];
		}
		fout << maxArr(MAX[n - 1], n);
	}
	system("pause");
	return 0;
}
