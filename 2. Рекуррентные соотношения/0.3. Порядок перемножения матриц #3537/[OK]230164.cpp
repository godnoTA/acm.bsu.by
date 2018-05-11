#include <iostream> 
#include <fstream>
#include<vector>
#include<cmath>
using namespace std;
int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int n;
	cin >> n;
	vector<pair<int, int>> matrix_sizes(n+1);
	for (int i = 1; i < n+1; i++)
		cin >> matrix_sizes[i].first >> matrix_sizes[i].second;

	vector<vector<int>> dp(n+1, vector<int>(n+1));
	for (int i = 0; i<n; i++) dp[i][i + 1] = matrix_sizes[i].first*matrix_sizes[i].second*matrix_sizes[i + 1].second;

	int min;
	for (int i = n - 2; i>0; i--){
		for (int j = i + 2; j < n + 1; j++){
			min = INT_MAX;
			int k = i;
			while (k != j){
				min = fmin(min, dp[i][k] + dp[k + 1][j] + matrix_sizes[i].first * matrix_sizes[k].second * matrix_sizes[j].second);
				k++;
			}
			dp[i][j] = min;
		}
	}

	cout << dp[1][n];

	return 0;
}