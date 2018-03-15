#include<iostream>
#include<fstream>
#include<vector>
#include<math.h>
#include<stdlib.h>
using namespace std;
ifstream fin("input.txt");
ofstream fout("output.txt");
int min(int a, int b)
{
	return (a < b ? a : b);
}
int multipl(int l, int r,int** dp,vector<int> v){
	if (dp[l][r] == -1){
		if (l == r - 1) dp[l][r] = 0;
		else dp[l][r] = INT_MAX;
		for (int i = l + 1; i < r; i++) dp[l][r] = min(dp[l][r], v[l] * v[i] * v[r] + multipl(l, i, dp,v) + multipl(i, r, dp,v));
	}
	return dp[l][r];
}

int main(){
	int n;
	fin >> n;
	vector<vector<int>> v0(n);
	while (!fin.eof()){
		for (int i = 0; i < n; i++)
		for (int j = 0; j < 2; j++)
		{
			int q;
			fin >> q;
			v0[i].push_back(q);
		}
	}
	vector<int> v(n + 1);
	v[0] = v0[0][0];
	for (int i = 1; i < n + 1; i++) 
		v[i] = v0[i - 1][1];
	int** dp = new int*[n+1];
	for (int i = 0; i < n + 1; i++)
		dp[i] = new int[n + 1];
	for (int i = 0; i < n + 1;i++)
	for (int j = 0; j < n + 1; j++)
		dp[i][j] = -1;
	int t=multipl(0, n, dp, v);
	fout << t;
	return 0;
}