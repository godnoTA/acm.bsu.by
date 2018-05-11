#define _CRT_SECURE_NO_WARNINGS
#pragma warning(disable : 4996) 
#include<vector>
#include<iostream>
#include<queue>
#include<fstream>
using namespace std;

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
	for (int i = 0; i < n; i++){
		if (!used[i])
			q.push(i);
		used[i] = true;
		while (!q.empty()){
			int v = q.front();
			q.pop();
			time[v] = timer++;
			for (int j = 0; j < n; j++) if (g[v][j] == 1) if (!used[j]) q.push(j), used[j] = true;
		}
	}
		for (int i : time) cout << i << " ";






	return 0;
}