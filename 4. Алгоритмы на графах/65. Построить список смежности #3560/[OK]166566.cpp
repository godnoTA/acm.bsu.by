#include <iostream>
#include <fstream>
#include <vector>

using namespace std;


int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n, m;

	fin >> n;
	fin >> m;

	int value1, value2;
	vector <vector<int>> V(n+1);
	for (int i = 0; i < m; i++) {
		fin >> value1;
		fin >> value2;
		V.at(value1).push_back(value2);
		V.at(value2).push_back(value1);
	}
	for (int i = 0; i < n; i++) {
		fout << V[i + 1].size() << " ";
		for (int j = 0; j < (V[i + 1]).size(); j++)
			fout << V[i + 1][j] << " ";
		fout << endl;
	}
	fin.close();
	return 0;
}