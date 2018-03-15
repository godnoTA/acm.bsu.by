#include <iostream>
#include <fstream>
#include <set>

using namespace std;

int main() {
	ifstream fin("input.txt");

	long long n;
	set <long long> buff;

	while (fin >> n) {
		buff.insert(n);
	}

	long long sum = 0;

	for (set <long long>::iterator it = buff.begin(); it != buff.end(); it++) {
		sum += *(it);
	}

	ofstream fout("output.txt");

	fout << sum;

	fin.close();
	fout.close();

	return 0;
}