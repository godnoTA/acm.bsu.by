#include <iostream>
#include <fstream>

using namespace std;

void bin(long num, int*arr) {
	int i = 0;
	while (num>0) {
		arr[i] = num % 2;
		num /= 2;
		i++;
	}
}

void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");

	unsigned long long quantity;
	unsigned long long buffer = 1;
	int degree = 0;
	in >> quantity;


	while (buffer <= quantity) {
		buffer *= 2;
		degree++;
	}

	int *array = new int[degree];

	for (int i = 0; i < degree; i++) {
		array[i] = -10;
	}

	buffer /= 2;
	degree--;

	int i = 0;
	int j = degree;

	while (quantity > 0) {

		if (buffer <= quantity) {
			quantity = quantity - buffer;
			array[i] = degree;
			i++;
		}
		buffer /= 2;
		degree--;
	}

	if(quantity==0){
		for (int i = j; i >= 0; i--) {
			if (array[i] >= 0) {
				out << array[i];
				out << endl;
			}
		}
	}

	else {
		out << -1;
	}
	in.close();
	out.close();
}