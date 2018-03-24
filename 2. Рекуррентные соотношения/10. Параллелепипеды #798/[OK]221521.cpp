#include <iostream>
#include <fstream>
#include <set>
#include <cmath>
#include <iterator>
#include <time.h>
#include <vector>
#include <cstdlib>
#include <algorithm>


using namespace std;

int compare(const void * x1, const void * x2) {
	return (*(int*)x1 - *(int*)x2);
}

struct imp {
	int * array;
	int degree;
	double mul;

	void read(ifstream &in, int dim) {
		for (int i = 0; i < dim; i++) {
			in >> array[i];
			degree += (int)log10(array[i]);
			mul *= array[i] / pow(10, (int)log10(array[i]));
		}
		qsort(array, dim, sizeof(int), compare);
	}

	imp() {
		array = new int[100];

		degree = 0;

		mul = 1;
	}
};

int compareTo(const void * x111, const void * x222) {

	imp* x11 = (imp*)x111;
	imp* x22 = (imp*)x222;

	imp x1 = *x11;
	imp x2 = *x22;

	if (x1.degree > x2.degree|| x1.degree < x2.degree) {
		return x1.degree - x2.degree;
	}

	else {
		if (x1.mul - x2.mul < 0) {
			return -1;
		}
		if (x1.mul - x2.mul == 0) {
			return 0;
		}
		return 1;
	}
}

bool includes(int* firstArray, int* secondArray, int dimension) {
	for (int i = 0; i < dimension; i++) {
		if (firstArray[i] > secondArray[i]) {
			return false;
		}
	}
	return true;
}

int finder(imp* a, int dim, int qua) {
	int *d = new int[dim]; // константа MAXN равна наибольшему возможному значению n

	for (int i = 0; i<dim; ++i) {
		d[i] = 1;
		for (int j = 0; j<i; ++j)
			if (includes(a[j].array, a[i].array, qua))
				d[i] = max(d[i], 1 + d[j]);
	}

	int ans = d[0];
	for (int i = 0; i<dim; ++i)
		ans = max(ans, d[i]);

	ofstream out("output.txt");

	out << ans;

	out.close();
	return 0;
}

void main() {
	clock_t tic = clock();

	ifstream in("input.txt");

	int dimension;
	int quantity;

	in >> dimension;
	in >> quantity;

	imp *array = new imp [quantity];

	for (int i = 0; i < quantity; i++) {
		array[i].read(in, dimension);
	}

	qsort(array, quantity, sizeof(imp), compareTo);

	finder(array, quantity, dimension);

	/*clock_t toc = clock();

	printf("Elapsed: %f seconds\n", (double)(toc - tic) / CLOCKS_PER_SEC);*/

	in.close();

	system("pause");
}