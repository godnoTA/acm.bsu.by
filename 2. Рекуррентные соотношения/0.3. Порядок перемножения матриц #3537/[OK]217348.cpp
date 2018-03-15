#include <iostream>
#include <fstream>

using namespace std;

int solution(int* array, int dimension) {
	
	int** solutionArray = new int* [dimension + 1];

	for (int i = 0; i < dimension + 1; i++) {
		solutionArray[i] = new int[dimension+1];
	}

	for (int i = 1; i < dimension+1; i++) {
		solutionArray[i][i] = 0;
	}

	for (int k = 2; k < dimension + 1; k++) {
		for (int i = 1; i < dimension + 1 - k + 1; i++) {
			int j = i + k - 1;
			solutionArray[i][j] = 2147483647;
			for (int l = i; l <= j - 1; l++) {
				if (solutionArray[i][j] > solutionArray[i][l] + solutionArray[l + 1][j] + array[i - 1] * array[l] * array[j]) {
					solutionArray[i][j] = solutionArray[i][l] + solutionArray[l + 1][j] + array[i - 1] * array[l] * array[j];
				}
			}
		}
	}
	return solutionArray[1][dimension];
}

void main() {
	int dimension;

	ifstream in("input.txt");
	ofstream out("output.txt");

	in >> dimension;

	int *array = new int[dimension+1];

	for (int i = 0; i < dimension; i++) {
		in >> array[i];
		in >> array[i + 1];
	}

	out << solution(array, dimension);
}