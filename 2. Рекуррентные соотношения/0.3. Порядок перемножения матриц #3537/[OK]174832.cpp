#include <iostream>
#include <fstream>
#include<vector>
#include<stdlib.h>
#include<math.h>

using namespace std;
int Min(int a, int b){
	if(a<b)
		return a;
	else
		return b;
}

ifstream in("input.txt");
ofstream out("output.txt");

int multiply(int l,int n, vector<int> vector, int** mas){
	if (mas[l][n] == -1)
	{
		if (l == n - 1) 
			mas[l][n] = 0;
		else 
			mas[l][n] = INT_MAX;
		for (int i = l+1; i < n; i++)
			mas[l][n] = Min(mas[l][n], vector[l] * vector[i] * vector[n] + multiply(l,i, vector, mas) + multiply(i,n,vector, mas));
	}
	return mas[l][n];
	
}

int main(){
	int n;
	in >> n;
	vector<vector<int>> vec(n);
	while (!in.eof()){
		for (int i = 0; i < n; i++)
		for (int j = 0; j < 2; j++)
		{
			int l;
			in >> l;
			vec[i].push_back(l);
		}
	}
	vector<int> v(n + 1);
	v[0] = vec[0][0];
	for (int i = 1; i < n + 1; i++) 
		v[i] = vec[i - 1][1];
	int** dp = new int*[n+1];
	for (int i = 0; i < n + 1; i++)
		dp[i] = new int[n + 1];
	for (int i = 0; i < n + 1;i++)
	for (int j = 0; j < n + 1; j++)
		dp[i][j] = -1;
	int t=multiply(0,n,v, dp);
	out << t;
	return 0;
}