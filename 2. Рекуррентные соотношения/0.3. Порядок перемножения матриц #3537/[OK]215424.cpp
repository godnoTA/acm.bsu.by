#include<limits.h>
#include<fstream>

using namespace std;

int MatrixChainOrder(int p[], int i, int j)
{
	if (i == j)
		return 0;
	int min = INT_MAX;
	int count;
	for (int k = i; k <j; k++)
	{
		count = MatrixChainOrder(p, i, k) +
			MatrixChainOrder(p, k + 1, j) +
			p[i - 1] * p[k] * p[j];

		if (count < min)
			min = count;
	}
	return min;
}
int multiplyOrder(int p[],int n) {
	int** dp = new int*[n + 1];
	for (int i = 0; i < n + 1; i++) {
		dp[i] = new int[n + 1];
	}

	for (int i = 1; i <= n; i++) {
		dp[i][i] = 0;
	}

	for (int l = 2; l <= n; l++) {
		for (int i = 1; i <= n - l + 1; i++) {
			int j = i + l - 1;
			dp[i][j] = INT_MAX;
			for (int k = i; k <= j - 1; k++) {
				dp[i][j] = dp[i][j]<=dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]?
					dp[i][j]: dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
			}
		}
	}
	return dp[1][n];
}

void main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n;
	in >> n;
	int* arr = new int[n + 1];
	int tmp=0;
	in >> arr[0];
	for (int i = 1; i <= n; i++) {
		in >> arr[i];
		in >> tmp;
	}
	out<<multiplyOrder(arr, n )<<endl;
}