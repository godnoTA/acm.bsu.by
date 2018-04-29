#include <iostream>
#include <fstream>

using namespace std;

void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");

	int dimension;
	in >> dimension;

	int number;
	in >> number;

	int **matrix = new int*[dimension];
	for (int i = 0; i < dimension; i++) {
		matrix[i] = new int[dimension];
		for (int j = 0; j < dimension; j++) {
			matrix[i][j] = 0;
		}
	}

	int x, y;

	for (int i = 0; i < number; i++) {
		in >> x;
		in >> y;
		matrix[x - 1][y - 1] = 1;
		matrix[y - 1][x - 1] = 1;
	}

	for (int i = 0; i < dimension; i++) {
		for (int j = 0; j < dimension; j++) {
			out << matrix[i][j];
			if (j < dimension - 1) {
				out << " ";
			}
		}
		out << endl;
	}

	in.close();
	out.close();
}