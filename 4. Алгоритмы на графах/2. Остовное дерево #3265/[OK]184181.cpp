#include <iostream>
#include <fstream>
#include <vector>


bool check = true;
std::vector <std::pair<int, int>> ribs;

void dfs(int v, std::vector<std::vector<int>> matrix, std::vector<bool> &used, int n) {
	used[v] = true;
	int c = 0, visited = 1;
	for (int i = 0; i < n; i++) {
		if (matrix[v][i] == 1) {
			if (used[i]==false) {
				ribs.push_back(std::make_pair(v+1, i+1));
				dfs(i, matrix, used, n);
			}
			else {
				visited++;
			}
		}
		else
			c++;
	}
	if (c==n && visited!=0)
		check = false;
}

int main() {
	std::ifstream fin("input.txt");
	std::ofstream fout("output.txt");
	int N;
	fin >> N;

	if (N == 1) {
		fout << 0;
		return 0;
	}
	int val;
	std::vector<std::vector<int>> matrix;
	std::vector<bool> used(N, false);
	for (int i = 0; i < N; i++) {
		std::vector <int> temp;
		for (int j = 0; j < N; j++) {
			fin >> val;
			temp.push_back(val);
		}
		matrix.push_back(temp);
	}
	fin.close();
	for (int i = 0; i < N; i++) {
		dfs(i, matrix, used, N);
	}
	if (ribs.size() != N - 1)
		check = false;
	if (check == false) {
		fout << -1;
		return 0;
	}
	if(check == true) {
		fout << ribs.size() << std::endl;
			for (int i = 0; i < ribs.size(); i++)
			fout << ribs.at(i).first << " " << ribs.at(i).second << std::endl;
	}
	fout.close();
	return 0;
}