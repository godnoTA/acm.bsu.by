#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	
	int N;
	int a, b;
	fin >> N;
	
	vector<int> v(N);
	for (int i = 0; i < N; i++)
		v[i] = 0;

	for (int i = 0; i < N; i++) {
		fin >> a >> b;
		v[b - 1] = a;
	}

	for (int i = 0; i < N; i++) 
		fout << v[i] << " ";
	
	return 0;
}