#include <iostream>
#include <fstream>
#include <set>
#include <map>
using namespace std;
int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	map<int, set<int>> list;
	int v1, v2, n, m;
	fin >> n >> m;

	for (int i = 0; i < n; ++i) {
		std::set<int> temp;
		list.insert(make_pair(i, temp));
	}

	for(int i = 0; i < m; i++){
		fin >> v1 >> v2;
		list[v1 - 1].insert(v2);
		list[v2 - 1].insert(v1);
	}

	for (auto v : list) {
		fout << v.second.size() << " ";
		for (auto v : v.second) {
			fout << v << " ";
		}
		fout << "\n";
	}

	return 0;
}
