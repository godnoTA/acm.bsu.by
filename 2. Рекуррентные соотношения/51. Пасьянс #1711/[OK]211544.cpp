
#include <iostream>
#include  <alg.h>
#include <fstream>
using namespace std;

const int maxn = 505;
const int inf = 1e9;

int a[maxn];
int dp[maxn][maxn];
int idx[maxn];

int main()
{
	int n,i=0;
	ifstream fin("input.txt", ios_base::in);
	fin >> n;
	while (!fin.eof()  && i<n) {
		fin >> a[i];
		idx[a[i]] = i;
		i++;
	}

	for (int i = 0; i < maxn; ++i) {
		for (int j = 0; j < maxn; ++j)
			dp[i][j] = inf;
		dp[i][i] = 0;
	}


	for (int len = 2; len <= n; ++len)
		for (int l = 1; l <= n - len + 1; ++l) {
			int r = l + len - 1;
			for (int mid = l; mid < r; ++mid)
				dp[l][r] = min(dp[l][r], dp[l][mid] + dp[mid + 1][r] + abs(idx[mid] - idx[r]));
		}

	ofstream fout("output.txt", ios_base::out); 
	fout<< dp[1][n];
	fout.close();
}