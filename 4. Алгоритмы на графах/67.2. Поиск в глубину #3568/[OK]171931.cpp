#include<iostream>
#include<vector>
#include<queue>
#include<fstream>
using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

int t = 1;
void dfs(int v, vector < vector<int> > &g, vector<bool> &used, vector<int> &d,int n){
	used[v] = true;
	d[v] = t++;
	for (int i = 0; i < n; i++){
		int to = g[v][i];
		if (!used[i] && to==1)
			dfs(i, g, used, d, n);
	}
}

int main(){
	int n;
	fin >> n;
	vector < vector<int> > g(n);

	vector<bool> used(n);
	vector<int> d(n);
	for (int i = 0; i < n; i++)
	for (int j = 0; j < n; j++){
		int q;
		fin >> q;
		g[i].push_back(q);
	}
	for (int i = 0; i < n; i++){
		if (!used[i]) dfs(i,g,used,d,n);
	}
	for (int i = 0; i < n; i++) fout << d[i] << " ";
	return 0;
}