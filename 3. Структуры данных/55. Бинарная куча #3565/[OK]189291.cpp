#include <iostream>
#include <fstream>

using namespace std;

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;
	int* ar = new int[n];
	for (int i = 0; i < n; i++)
		fin >> ar[i];

	for (int i = n - 1; i > -1; i--)
	{
		if (!(ar[(i-1) / 2] <= ar[i])) {
			fout << "No";
			return 0;
		}
	}
	fout << "Yes";
	return 0;
}