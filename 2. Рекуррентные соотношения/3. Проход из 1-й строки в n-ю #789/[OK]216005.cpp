#include <iostream> 
#include <cstdio> 
#include <vector> 
#include <algorithm> 

using namespace std;

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	int n, m;
	cin >> n >> m;
	int a[210][1010];
	int dp[250][1010];
	for (int i = 0; i <= n; ++i) {
		for (int j = 0; j <= m; ++j) {
			dp[i][j] = (int)2e9;
		}
	}
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; ++j) {
			cin >> a[i][j];
		}
	}
	for (int i = 0; i <= m; ++i) {
		dp[0][i] = 0;
	}
	for (int i = 0; i < n; ++i) {
		for (int j = 1; j <= m; ++j) {
			if (j - 1 >= 0) {
				dp[i + 1][j - 1] = min(dp[i + 1][j - 1], a[i + 1][j - 1] + dp[i][j]);
			}
			if (j + 1 <= m) {
				dp[i + 1][j + 1] = min(dp[i + 1][j + 1], a[i + 1][j + 1] + dp[i][j]);
			}
			dp[i + 1][j] = min(dp[i + 1][j], a[i + 1][j] + dp[i][j]);
		}
	}
	int ans = dp[n][1];
	for (int i = 2; i <= m; ++i) {
		ans = min(ans, dp[n][i]);
	}
	cout << ans << endl;
	return 0;
}
