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

	int x;
	int counter = -1;

	for (int j = 0; j < dimension; j++) {
		for (int i = 0; i < dimension; i++) {
			in >> x;
			counter++;
			if (x != 0) {
				array[counter] = j + 1;
			}
		}
		counter = -1;
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