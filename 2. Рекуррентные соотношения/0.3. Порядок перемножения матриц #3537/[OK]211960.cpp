#include <iostream>
#include  <alg.h>
#include <fstream>
using namespace std;

const int maxn = 505;
const int inf = 1e9;

int a[maxn];
int str[maxn];
int col[maxn];
int dp[maxn][maxn];
int idx[maxn];

int main()
{
	int n, i = 1;
	ifstream fin("input.txt", ios_base::in);
	fin >> n;
	while ( i<=n) {
		fin >>str[i];
		fin >> col[i];
		i++;
	}


	for (int i = 1; i < maxn; ++i) {
		for (int j = 1; j < maxn; ++j)
			dp[i][j] = inf;
		dp[i][i] = 0;
	}


	for (int len = 2; len <= n; ++len)
		for (int l = 1; l <= n - len + 1; ++l) {
			int r = l + len - 1;
		for (int mid = l; mid < r; ++mid)
			{
				dp[l][r] = min(dp[l][r], dp[l][mid] + dp[mid + 1][r] + (str[l] * col[mid] * col[r]));
			
			}
				
		}

	ofstream fout("output.txt", ios_base::out);
	fout << dp[1][n];
	cout << dp[1][n];
	fout.close();
}