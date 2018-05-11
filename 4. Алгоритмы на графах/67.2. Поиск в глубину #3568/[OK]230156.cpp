#define _CRT_SECURE_NO_WARNINGS
#pragma warning(disable : 4996) 
#include<vector>
#include<iostream>
#include<queue>
#include<fstream>
using namespace std;

void dfs(int v,vector<vector<int>>& g, vector<char>& used, int& timer, vector<int>& time){
	time[v] = timer++;
	used[v] = true;
	for (int i = 0; i < g.size(); i++) if (g[v][i] == 1) if (!used[i]) dfs(i, g, used, timer, time);
}

int main(){
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int n;
	cin >> n;
	int first, second;
	vector<vector<int>> g(n, vector<int>(n));
	for (int i = 0; i < n; i++)
	for (int j = 0; j < n; j++)
		cin >> g[i][j];
	

	queue<int> q;
	vector<char> used(n, false);
	int timer = 1;
	vector<int> time(n);
	for (int i = 0; i < n; i++)
	if (!used[i]) dfs(i, g, used, timer, time);

	for (int i : time) cout << i << " ";




	return 0;
}