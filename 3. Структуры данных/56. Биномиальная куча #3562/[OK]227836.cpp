#include <iostream>
#include <fstream>
#include <vector>
using namespace std;
ifstream fin("input.txt");
ofstream fout("output.txt");

int main() {
	long long n;
	fin >> n;
	fin.close();
	vector<int> a;
	int i = 0;
	while (n != 0) {
		if (n % 2 != 0)
			a.push_back(i);
		i++;
		n /= 2;
	}
	for (int j = 0; j < a.size(); j++) {
		fout << a[j] << endl;
	}
	fout.close();
}