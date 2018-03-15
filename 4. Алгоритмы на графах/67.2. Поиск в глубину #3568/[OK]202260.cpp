#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

vector<bool> visit;
vector<int> Nodes;
int **matrix;
int global;
int N;

void DFS(int i) {
	visit[i] = true;
	global++;
	Nodes[i] = global;
	for (int j = 0; j < N; j++) {
		if (matrix[i][j] == 1 && visit[j] == false)
			DFS(j);
	}
}

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	fin >> N;
	
	for (int i = 0; i < N; i++) {
		visit.push_back(false);
		Nodes.push_back(0);
	}

	matrix = new int*[N];
	for (int i = 0; i < N; i++)
		matrix[i] = new int[N];

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			fin >> matrix[i][j];


	global = 0;
	for (int i = 0; i < N; i++) {
		if (visit[i] == false)
			DFS(i);
	}
	for (int i = 0; i < N; i++) {
		fout << Nodes[i] << " ";
	}
	return 0;
}