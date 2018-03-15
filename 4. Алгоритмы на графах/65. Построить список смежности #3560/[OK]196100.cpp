#include <list>
#include <fstream>
#include <vector>
#include <iostream>

using namespace std;

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int N, M;
	int a, b;
	fin >> N >> M;

	vector<list<int>> v(N);
	for (int i = 0; i < M; i++) {
		fin >> a >> b;
		v[a-1].push_back(b);
		v[b - 1].push_back(a);
	}

	for (int i = 0; i < N; i++) {
		fout << v[i].size() << " ";
		for (list<int>::iterator j = v[i].begin(); j != v[i].end(); j++)
			fout << *j << " ";
		fout << endl;
	}
	return 0;
}