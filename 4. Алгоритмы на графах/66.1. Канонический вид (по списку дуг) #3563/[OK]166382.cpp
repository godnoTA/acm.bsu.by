#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main() {
	ifstream fin("input.txt");
	int in,in2;
	fin >> in;
	int *mas = new int[in];
	for (int i = 0; i < in; i++)
		mas[i] = 0;
	int count = in;
	int max = 0;
	while (fin>>in) {
		fin >> in2;
		mas[in2-1] = in;
	}
	ofstream fout("output.txt");
	for (int i = 0; i < count; i++) {
		fout << mas[i]<<" ";
	}

	return 0;
}