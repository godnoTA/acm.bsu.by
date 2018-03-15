#include <iostream>
#include <fstream>
#include <vector>
#include <queue>

int main() {
	std::ifstream fin("input.txt");
	std::ofstream fout("output.txt");
	int N;
	fin >> N;

	int val;
	std::vector<std::vector<int>> matrix;
	for (int i = 0; i < N; i++) {
		std::vector <int> temp;
		for (int j = 0; j < N; j++) {
			fin >> val;
			temp.push_back(val);
		}
		matrix.push_back(temp);
	}
	fin.close();


	std::vector<bool> used(N, 0);
	std::vector<int> index(N, 0);
	int id = 1;
	for (int i = 0; i < N;i++) {
		std::queue<int> que;
		que.push(i);
		if (used[i] == false) {
			used[i] = true;
			index[i] = id++;
		}
		while (!que.empty()) {
			int v = que.front();
			que.pop();
			for (int j = 0; j < N; j++) {
				if (matrix[v][j] == 1) {
					if (used[j] == true)
						continue;
					used[j] = true;
					que.push(j);
					index[j] = id;
					id++;
				}
			}
		}
	}
	
	for (int i = 0; i < N;i++) {
		if (index[i] == 0) {
			index[i] = id;
			id++;
		}
	}
	for (int i : index)
		fout << i << " ";
	fout.close();
	return 0;
}