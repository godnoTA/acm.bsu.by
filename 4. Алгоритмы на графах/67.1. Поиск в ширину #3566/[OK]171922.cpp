#include<iostream>
#include<vector>
#include<queue>
#include<fstream>
using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");



int main(){
	int n;
	fin >> n;
	vector<bool> used(n);
	vector<int> d(n);
	int t = 1;
	vector < vector<int> > g(n);
	for (int i = 0; i < n; i++)
	for (int j = 0; j < n; j++){
		int q;
		fin >> q;
		g[i].push_back(q);
	}
	for (int i = 0; i < n; i++)
	{
		if (!used[i]) {
			queue<int> q;
			q.push(i);
			used[i] = true;
			d[i] = t++;
			while (!q.empty()) {
				int v = q.front();
				q.pop();
				for (size_t j = 0; j<g[v].size(); ++j) {
					int to = g[v][j];
					if (g[v][j] == 1){
						if (!used[j]) {
							used[j] = true;
							q.push(j);
							d[j] = t++;
						}
					}
				}
			}
		}
	}
	for (int i = 0; i < n; i++) fout << d[i] << " ";
	return 0;
}