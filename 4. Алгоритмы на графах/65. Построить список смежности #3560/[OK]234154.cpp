#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

struct reb {
	int a;
	int b;
};

void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");

	int dimension;
	in >> dimension;

	int number;
	in >> number;

	vector<int> *spis = new vector<int>[dimension];

	int buff1, buff2;

	reb *list = new reb[number];
	for (int i = 0; i < number; i++) {
		in >> buff1;
		in >> buff2;

		spis[buff1 - 1].push_back(buff2);
		spis[buff2 - 1].push_back(buff1);
	}

	
		for (int j = 0; j < dimension; j++) {
			if (j != 0) {
				out << " ";
			}
			out << spis[j].size();
			for (int i = 0; i < spis[j].size(); i++) {
				out << " " << spis[j][i];
			}
			out << endl;
		}
		out << endl;

	in.close();
	out.close();
}