#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <vector>


int main() {

	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	int n = 0;
	int m = 0;
	std::cin >> n >> m;
	std::vector<std::vector<int>> list(n);
	int v = 0;
	int w = 0;
	for (int i = 0; i < m; i++) {
		std::cin >> v >> w;
		list[v - 1].push_back(w);
		list[w - 1].push_back(v);
	}
	for (int i = 0; i < n; i++) {
		std::cout << list[i].size();
		for (int j = 0; j < list[i].size(); j++) {
			std::cout << " " << list[i][j];
		}
		if (i != n - 1) {
			std::cout << std::endl;
		}
		
	}
	

	fclose(stdout);
	fclose(stdin);
	system("pause");
	return 0;
}