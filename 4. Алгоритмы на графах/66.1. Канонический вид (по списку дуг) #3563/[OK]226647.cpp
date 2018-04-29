#include <iostream>
#include <fstream>

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

	int *array = new int[dimension];
	for (int i = 0; i < dimension; i++) {
		array[i] = 0;
	}

	int x, y;

	for (int i = 0; i < dimension - 1; i++) {
		in >> x;
		in >> y;
		array[y - 1] = x;
	}

	for (int i = 0; i < dimension; i++) {
		if (i != 0) {
			out << " ";
		}
		out << array[i];
	}

	delete[] array;
	in.close();
	out.close();
}