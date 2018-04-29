#include<iostream>
#include<fstream>
#include<stack>
using namespace std;


int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");

	int m, n;
	in >> n >> m;
	bool**adjacency = new bool*[n];
	for (int i = 0; i < n; ++i) {
		adjacency[i] = new bool[n];
		for (int j = 0; j < n; ++j) {
			adjacency[i][j] = false;
		}
	}
	for (int i = 0; i < m; ++i) {
		int v1, v2;
		in >> v1 >> v2;
		adjacency[v1 - 1][v2 - 1] = true;
		adjacency[v2 - 1][v1 - 1] = true;
	}

	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j) {
			out<<adjacency[i][j]<<' ';
		}
		out << endl;
	}

	return 0;
}
