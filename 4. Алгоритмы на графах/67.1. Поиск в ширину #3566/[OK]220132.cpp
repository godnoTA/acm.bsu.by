#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <queue>


int main() {
	std::ios_base::sync_with_stdio(false);
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	int n;
	int**adjacentMatrix;
	int *labels;
	bool *visited;
	std::queue<int> queue;

	std::cin >> n;
	adjacentMatrix = new int*[n];
	labels = new int[n];
	visited = new bool[n];
	for (int i = 0; i < n; i++) {
		adjacentMatrix[i] = new int[n];
		visited[i] = false;
		labels[i] = 0;
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			std::cin >> adjacentMatrix[i][j];
		}
	}

	int label = 0;
	for (int k = 0; k < n; k++) {
		
		if (!visited[k]) {
			queue.push(k);
			visited[k] = true;
		}
		
		while (!queue.empty()) {
			int vertex = queue.front();
			queue.pop();
			label++;
			labels[vertex] = label;
			for (int i = 1; i < n; i++) {
				if (adjacentMatrix[vertex][i] == 1) {
					if (!visited[i]) {
						visited[i] = true;
						queue.push(i);
						

					}
				}

			}
		}

	}
	
	
	for (int i = 0; i < n; i++) {
		std::cout << labels[i] << " ";
	}



	fclose(stdout);
	fclose(stdin);
	system("pause");
	return 0;
}