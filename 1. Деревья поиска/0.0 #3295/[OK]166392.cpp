#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	vector<long long> *v = new vector<long long>();
	ifstream fin("input.txt");
	long long in, in2;
	long long count = 0;
	
	while (fin >> in) {
		if (find(v->begin(), v->end(), in) == v->end()) {
			v->push_back(in);
			count += in;
		}
	}

	ofstream fout("output.txt");
	fout << count;
	delete v;
	return 0;
}