#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <queue>

int n;
int**adjacentMatrix;
bool*visited;
int*labels;
int label = 0;

void dfs(int vertex) {
	if (!visited[vertex]) {
		visited[vertex] = true;
		labels[vertex] = ++label;
		for (int i = 0; i < n; i++) {
			if (adjacentMatrix[vertex][i] == 1 && !visited[i]) {
				labels[i] = label;
				dfs(i);
			}
		}
	}
	
}
int main() {
	std::ios_base::sync_with_stdio(false);
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);


	std::cin >> n;
	adjacentMatrix = new int*[n];
	visited = new bool[n];
	labels = new int[n];

	for (int i = 0; i < n; i++) {
		adjacentMatrix[i] = new int[n];
		visited[i] = false;
		labels[i] = 1;
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			std::cin >> adjacentMatrix[i][j];
		}
	}
	
	for (int i = 0; i < n; i++) {
		dfs(i);
	}

	for (int i = 0; i < n; i++) {
		std::cout << labels[i] << " ";
	}



	fclose(stdout);
	fclose(stdin);
	system("pause");
	return 0;
}