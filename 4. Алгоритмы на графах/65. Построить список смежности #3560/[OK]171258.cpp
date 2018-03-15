#include <bits.h>
#include <iostream>
#include <fstream>
#include<vector>
using namespace std;
vector<int> graph[100000];    //массив из 100000 векторов.

int main() {
	ofstream fout("output.txt");
	ifstream fin("input.txt");
	//vector<int> graph[100000];
	int n, m;
	fin >> n >> m;

	for (int i = 0; i < m; i++) {
		int u, v;
		fin >> u >> v;
		u--, v--;

		graph[u].push_back(v);
		graph[v].push_back(u);
	}

	for (int i = 0; i < n; i++) {
		int c = graph[i].size();
		fout << c << " ";
		for (int v : graph[i]) {     //можно было бы просто записать "int c = graph[i].size();",
			fout << ++v << " ";
		}
		fout <<endl;
	}
	return 0;
}