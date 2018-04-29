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

	for (int i = 0; i < n; ++i) {
		int v;
		for (int j = 0; j < n; ++j) {
			in >> v;
			if (v ==1 ) {
				tree[j] = i+1;
			}
		}
		
	}

	for (int i = 0; i < n; ++i) {
		out << tree[i] << ' ';
	}

	return 0;
}
