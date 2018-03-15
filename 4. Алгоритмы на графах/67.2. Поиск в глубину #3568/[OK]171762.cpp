#include <iostream>
#include <fstream>
#include <vector>
#include <queue>


int id = 1;

void dfs(int v,std::vector<std::vector<int>> matrix,std::vector<bool> &used, int n,std::vector<int> &index) {
	used[v] = true;
	if (index[v]==0)
		index[v] = id++;
	for (int i = 0; i < n;i++) {
		if (matrix[v][i] && !used[i]) {
			dfs(i, matrix, used, n, index);
		}
	}
}

int main() {
	std::ifstream fin("input.txt");
	std::ofstream fout("output.txt");
	int N;
	fin >> N;

	int val;
	std::vector<std::vector<int>> matrix;
	std::vector<bool> used(N,false);
	std::vector<int> index(N,0);
	for (int i = 0; i < N; i++) {
		std::vector <int> temp;
		for (int j = 0; j < N; j++) {
			fin >> val;
			temp.push_back(val);
		}
		matrix.push_back(temp);
	}
	fin.close();
	for (int i = 0; i < N;i++) {
		dfs(i,matrix,used,N,index);
	}

	for (int i : index)
		fout << i << " ";
	fout.close();
	return 0;
}