#include<iostream>
#include<fstream>
#include<vector>
using namespace std;


int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n;
	in >> n;
	vector<int>tree(n,0);

	for (int i = 0; i < n-1; ++i) {
		int v1, v2;
		in >> v1 >> v2;
		tree[v2 - 1] = v1;
		
	}

	for (int i = 0; i < n; ++i) {
		out << tree[i] << ' ';
	}

	return 0;
}
