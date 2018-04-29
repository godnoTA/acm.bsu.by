#include<iostream>
#include<fstream>
#include<vector>
using namespace std;


int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");

	int m, n;
	in >> n >> m;
	vector<vector<int>>adjacency(n);

	for (int i = 0; i < m; ++i) {
		int v1, v2;
		in >> v1 >> v2;
		adjacency[v1 - 1].push_back(v2);
		adjacency[v2 - 1].push_back(v1);
	}

	for (int i = 0; i < n; ++i) {
		out << adjacency[i].size() << ' ';
		for (int j = 0; j < adjacency[i].size(); ++j) {
			out<<adjacency[i][j]<<' ';
		}
		out << endl;
	}

	return 0;
}
