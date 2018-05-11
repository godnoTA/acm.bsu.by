#include <iostream> 
#include <fstream> 
#include <vector> 
#include <cmath> 
using namespace std;
int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");

	int n, m, d;
	cin >> n >> m;

	if (m>n){ cout << -1; return 0; }
	vector<vector<int>> a(n, vector <int>(m));
	for (int i = 0; i < n; i++)
	for (int j = 0; j < m; j++)
		cin >> a[i][j];
	long ans = 0;
	if (m == 1){
		for (int i = 0; i < n; i++) ans += a[i][0];
		cout << ans;
		return 0;
	}
	vector<vector<long>> dp(n, vector<long>(m,-1));
	dp[0][0] = a[0][0];
	for (int i = 1; i < n; i++){
		if(dp[i-1][1]!=-1) dp[i][0] = fmin(dp[i - 1][0] + a[i][0], dp[i - 1][1] + a[i][0]);
		else dp[i][0] = dp[i - 1][0] + a[i][0];
			if (dp[i-1][m - 1] != -1) dp[i][m - 1] = fmin(dp[i - 1][m - 1] + a[i][m - 1], dp[i - 1][m - 2] + a[i][m - 1]);
			else if (dp[i - 1][m - 2] != -1) dp[i][m - 1] = dp[i-1][m - 2] + a[i][m - 1];
		for (int j = 1; j < m-1; j++)
		if (dp[i - 1][j - 1] == -1 && dp[i - 1][j] == -1 && dp[i - 1][j + 1]) break;
		else if (dp[i - 1][j] == -1 && dp[i - 1][j + 1] == -1) dp[i][j] = dp[i - 1][j - 1]+a[i][j];
		else  if (dp[i - 1][j + 1] == -1) dp[i][j] = fmin(dp[i - 1][j - 1] + a[i][j], dp[i - 1][j] + a[i][j]);
		else  dp[i][j] = fmin(dp[i - 1][j] + a[i][j], fmin(dp[i - 1][j - 1] + a[i][j], dp[i - 1][j + 1] + a[i][j]));
	}
	cout << dp[n - 1][m - 1];
	return 0;
}