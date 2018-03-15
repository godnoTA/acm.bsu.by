#include <iostream>
#include <fstream>
#include <vector>
#include <queue>

using namespace std;

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int global = 1;
	int N;
	fin >> N;
	vector<bool> visit(N);
	vector<int> result(N);
	vector<int> path(N);
	for (int i = 0; i < N; i++) {
		visit[i] = false;
		result[i] = 0;
		path[i] = 0;
	}
	
	int **matrix = new int*[N];
	for (int i = 0; i < N; i++)
		matrix[i] = new int[N];

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			fin >> matrix[i][j];

	queue<int> searcher;

	for (int i = 0; i < N; i++) {
		if (searcher.empty()) {
			if (visit[i] == false)
				searcher.push(i);
			visit[i] = true;
		}
		if (!searcher.empty()) {
			while (!searcher.empty()) {
				int del = searcher.front();
				result[del] = global;
				global++;
				searcher.pop();
				for (int j = 0; j < N; j++)
					if (matrix[del][j] == 1 && visit[j] == false) {
						searcher.push(j);
						visit[j] = true;
					}
			}
		}
	}
	for (int i = 0; i < N; i++) {
		fout << result[i] << " ";
	}
	return 0;
}