#include <iostream>
#include <fstream>
using namespace std;
ifstream fin("input.txt");
ofstream fout("output.txt");

long long multiplyOrder(int p[], int N) {
	int n = N - 1;
	long long** dp = new long long*[N];
	for (int i = 0; i < N; i++) {
		dp[i] = new long long[N];
	}

	for (int i = 1; i <= n; i++) {
		dp[i][i] = 0;
	}

	for (int l = 2; l <= n; l++) {
		for (int i = 1; i <= n - l + 1; i++) {
			int j = i + l - 1;
			dp[i][j] = 180000000000;
			for (int k = i; k <= j - 1; k++) {
				int q = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
				if (dp[i][j] > q)
					dp[i][j] = q;
			}
		}
	}
	return dp[1][n];
}

int main() {
	int n, g = 0, q = 2, a;
	fin >> n;
	int* p = new int[n + 1];
	fin >> p[0];
	fin >> p[1];
	while (!fin.eof()) {
		if (g == 0) {
			g++;
			fin >> a;
			continue;
		}
		else {
			g--;
			fin >> p[q];
			q++;
		}
	}
	fin.close();
	fout << multiplyOrder(p, n + 1) << endl;
	fout.close();
	return 0;
}