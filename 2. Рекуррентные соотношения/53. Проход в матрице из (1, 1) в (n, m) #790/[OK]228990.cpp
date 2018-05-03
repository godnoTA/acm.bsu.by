// main.cpp : Defines the entry point for the console application.
//
#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;
int n, m;
long long a[210][1010];
long long mas[210][1010];
ifstream in("input.txt");
ofstream out("output.txt");

int main() {
	in >> n >> m;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			in >> a[i][j];
			mas[i][j] = 999999999999;
		}
	}
	mas[1][1] = a[1][1];
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			mas[i + 1][j - 1] = min(mas[i + 1][j - 1], mas[i][j] + a[i + 1][j - 1]);
			mas[i + 1][j] = min(mas[i + 1][j], mas[i][j] + a[i + 1][j]);
			mas[i + 1][j + 1] = min(mas[i + 1][j + 1], mas[i][j] + a[i + 1][j + 1]);
		}
	}
	if (mas[n][m] >= 999999999999)
		out << -1 << endl;
	else
		out << mas[n][m] << endl;
}