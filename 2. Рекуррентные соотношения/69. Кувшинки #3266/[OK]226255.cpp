#include <fstream>
#include <iostream>

using namespace std;

int max(int a, int b) {
	if (a > b)
		return a;
	else
		return b;
}

int a[1000];
int dp[1000];

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;
	for (int i = 0; i < n; i++)
		fin >> a[i];
	dp[0] = a[0];
	dp[1] = -1;
	dp[2] = a[0] + a[2];
	dp[3] = a[0] + a[3];
	dp[4] = a[4] + dp[2];
	for (int i = 5; i < n; i++) {
		dp[i] = a[i] + max(dp[i - 2], dp[i - 3]);
	}
	fout << dp[n - 1];
    return 0;
}