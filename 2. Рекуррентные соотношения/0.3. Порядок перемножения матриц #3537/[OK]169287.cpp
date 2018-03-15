#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

int main() {
	std::ofstream fout("output.txt");
	std::ifstream fin("input.txt");
	int value,n;
	fin >> n;
	fin >> value;
	std::vector<int> sizes;
	sizes.push_back(value);
	int i = 0;
	while (!fin.eof()) {
		fin >> value;
		if (i % 2 == 0) {
			sizes.push_back(value);
		}
		i++;
	}
	long long **matrix = new long long*[n+1];
	for (int i = 0; i < n+1; ++i) {
		matrix[i] = new long long[n + 1]{};
	}
	for (int i = 1; i <= n;i++) {
		matrix[i][i] = 0;
	}

	for (int k = 2; k <= n;k++) {
		for (int i = 1; i <= n + 1 - k;i++) {
			int j = i + k - 1;
			matrix[i][j] = LLONG_MAX;
			for (int l = i; l <= j - 1;l++) {
				matrix[i][j] = std::min(matrix[i][j],matrix[i][l]+matrix[l+1][j]+sizes[i-1]*sizes[l]*sizes[j]);
			}
		}
	}
	fout << matrix[1][n];
	fin.close();
	fin.close();
	return 0;
}