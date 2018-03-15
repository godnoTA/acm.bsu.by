#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main(){
	ifstream in("input.txt");
	ofstream out("output.txt");
	int N;
	in >> N;
	int MAX =  2147483647;
	vector<int> p(N+1, 0);
	for(int i = 0; i < N; i++){
		int temp1, temp2;
		in >> temp1 >> temp2;
		p[i] = temp1;
		if(i == N-1)
			p[i+1] = temp2;
	}

	vector<vector<int>> dp(N+1, vector<int>(N+1, 0));
		for (int l = 2; l <= N; l++) {
			for (int i = 1; i <= N - l + 1; i++) {
				int j = i + l - 1;
				dp[i][j] = MAX;
				for (int k = i; k <= j - 1; k++) {
					dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
				}
			}
		}
		
	
		out << dp[1][N] << " ";
}
