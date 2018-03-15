#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main() {
	ifstream fin("input.txt");
	int n1, n2;
	fin >> n1;
	fin >> n2;
	vector<vector<int>> mas(n1);


	//mas.size() = n1;
	int I, J;
	vector<vector<int>>::iterator beg = mas.begin();
	for (int i = 0; i < n2; i++) {
		fin >> I;
		fin >> J;
		(beg + I-1)->push_back(J);
		(beg + J-1)->push_back(I);
	}
	fin.close();	

	ofstream fout("output.txt");
	beg = mas.begin();
	for (int i = 0; i < n1; i++) {
		fout << (beg+i)->size()<<" ";
		if ((beg + i)->size() >= 1) {
			if((beg + i)->size() != 1)
				for (int j = (beg + i)->size() - 1; j > 0; j--)
					fout << *((beg + i)->begin() + j) << " ";

			fout << *((beg + i)->begin());
		}
		fout << "\n";
	}


	fout.close();

	return 0;
}