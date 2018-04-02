#include <iostream>
#include <iomanip>
#include <fstream>
#include <math.h>

using namespace std;

int N;
double disc_arr[100];
double x_arr[100];


int find_disc(double radius, int index) {

	int res = -1;
	double max = -1;
	for (int i = index - 1; i>-1; i--) {
		if ((x_arr[i] + 2 * sqrt(disc_arr[i] * radius)) >= max) {
			res = i;
			max = x_arr[i] + 2 * sqrt(disc_arr[i] * radius);
		};
	};
	if (max <= radius)
		return -1;
	else
		return res;
};




int main() {

	ifstream fIn("in.txt", ios::in);
	fIn >> N;
	for (int i = 0; i<N; i++)
		fIn >> disc_arr[i];


	double x = disc_arr[0];
	double curr_x = x;
	x_arr[0] = x;
	int d1;
	for (int i = 1; i<N; i++) {
		d1 = find_disc(disc_arr[i], i);
		if (d1 == -1) {
			x = disc_arr[i];
			curr_x = x;
			x_arr[i] = x;
		}
		else {
			curr_x = x_arr[d1] + 2 * sqrt(disc_arr[d1] * disc_arr[i]);
			x_arr[i] = curr_x;
		};
	};
	double len_max = 0;
	for (int i = 0; i<N; i++) {
		if ((x_arr[i] + disc_arr[i]) >= len_max)
			len_max = x_arr[i] + disc_arr[i];
	};

	ofstream fOut("out.txt", ios::out);
	fOut << setprecision(5)
		<< setiosflags(ios::fixed | ios::showpoint)
		<< len_max << endl;
	fOut.close();
	return 0;
};
