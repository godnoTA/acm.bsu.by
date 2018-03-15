#include <iostream>
#include <fstream>

using namespace std;


int main() {
	ifstream fin("input.txt");
	long long mainNum;
	fin >> mainNum;
	fin.close();
	int count = 0;
	ofstream fout("output.txt");
	while (mainNum > 0) {
		if (mainNum % 2 == 1)
			fout << count<<endl;
		mainNum = mainNum / 2;
		count++;
	}
	fout.close();

	return 0;
}