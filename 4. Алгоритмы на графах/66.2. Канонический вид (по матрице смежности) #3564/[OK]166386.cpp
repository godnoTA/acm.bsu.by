#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main() {
	ifstream fin("input.txt");
	int in, in2;
	fin >> in;
	int *mas = new int[in];
	for (int i = 0; i < in; i++)
		mas[i] = 0;
	int count = in;
	for (int j = 1; j < count+1; j++) {
		for (int i = 0; i < count; i++) {
			fin >> in;
			if (in == 1)
				mas[i] = j;
		}
	}
	
	ofstream fout("output.txt");
	for (int i = 0; i < count; i++) {
		fout << mas[i] << " ";
	}
	delete mas;
	return 0;
}