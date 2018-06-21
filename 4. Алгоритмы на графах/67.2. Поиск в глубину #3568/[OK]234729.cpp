#include<vector>
#include<queue>
#include<fstream>
using namespace std;

void dfs(int v, vector<vector<int>>& g, vector<char>& used, int& timer, vector<int>& time) {
	time[v] = timer++;
	used[v] = true;
	for (int i = 0; i < g.size(); i++) 
		if (g[v][i] == 1) 
			if (!used[i]) 
				dfs(i, g, used, timer, time);
}

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;
	int first, second;
	vector<vector<int>> g(n, vector<int>(n));
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			fin >> g[i][j];
	queue<int> q;
	vector<char> used(n, false);
	int timer = 1;
	vector<int> time(n);
	for (int i = 0; i < n; i++)
		if (!used[i]) dfs(i, g, used, timer, time);

	for (int i : time) fout << i << " ";

	return 0;
}