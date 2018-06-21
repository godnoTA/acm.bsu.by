#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

void DFS(int &i, bool *&isVisited, int &number, int *&way, int &dimension, int **&graph) {
	isVisited[i] = true;
	number++;
	way[i] = number;
	for (int j = 0; j < dimension; j++) {
		if (graph[i][j] == 1 && isVisited[j] == false) {
			DFS(j, isVisited, number, way, dimension, graph);
		}
	}
}

int main() {
	ifstream in("input.txt");
	ofstream of("output.txt");

	int n;
	in >> n;

	int **matrix = new int*[n];
	for (int i = 0; i < n; i++) {
		matrix[i] = new int[n];
		for (int j = 0; j < n; j++)
			in >> matrix[i][j];
	}

	bool *isVisited = new bool[n];
	for (int i = 0; i < n; i++) {
		isVisited[i] = false;
	}

	int *way = new int[n];
	for (int i = 0; i < n; i++) {
		way[i] = 0;
	}

	int number = 0;

	for (int i = 0; i < n; i++) {
		if (isVisited[i] == false){
			DFS(i, isVisited, number, way, n, matrix);
		}
			
	}
	for (int i = 0; i < n; i++) {
		of << way[i] << " ";
	}
	return 0;
}
