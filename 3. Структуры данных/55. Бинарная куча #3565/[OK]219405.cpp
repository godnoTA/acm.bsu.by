#include <iostream>
#include <fstream>

using namespace std;

void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");

	int quantity;
	in >> quantity;

	long *array = new long[quantity];

	for (int i = 0; i < quantity; i++) {
		in >> array[i];
	}

	int i;

	for (i = 0; i < quantity; i++) {
		if (2 * i + 2 < quantity) {
			if ((array[i] > array[2 * i + 1] || array[i] > array[2 * i + 2])) {
				out << "No";
				break;
			}
		}
		else if (2 * i + 1 < quantity) {
			if ((array[i] > array[2 * i + 1])) {
				out << "No";
				break;
			}
		}
	}

	if (i == quantity) {
		out << "Yes";
	}

	in.close();
	out.close();
}