#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;
int kol[100000];
int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;
	int m;
	fin >> m;
	vector<vector<int>> matrix(n, vector<int>());
    for (int i = 0; i < n; i++) {
        kol[i] = 0;
    }
	for (int i = 0; i < m; i++) {
		int u;
		fin >> u;
		int v;
		fin >> v;
		kol[u - 1]++;
		kol[v - 1]++;
		matrix[u - 1].push_back(v);
		matrix[v - 1].push_back(u);
	}
    for (int i = 0; i < n; i++){
       fout << kol[i] << " ";
       for (int j: matrix[i]){
         fout << j << " ";
       }
	   fout << endl;
    }
	return 0;
}
