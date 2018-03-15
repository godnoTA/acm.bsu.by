#include <iostream>
#include <fstream>
#include <algorithm>
#include <stdio.h>

using namespace std;
#define MAX_NUMB 10001

int positions[MAX_NUMB];
int times[MAX_NUMB];
int min_time_left[MAX_NUMB][MAX_NUMB];
int min_time_right[MAX_NUMB][MAX_NUMB];

int main() {
	ios_base::sync_with_stdio(false);
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;

	for (int i = 0; i < n; i++) {
		fin >> positions[i];
		fin >> times[i];
	}
	for (int i = 0; i < n; i++) {
		for (int j = 1; j < n - i; j++) {
			min_time_left[i][j] = MAX_NUMB;
			min_time_right[i][j] = MAX_NUMB;
		}
		min_time_left[i][0] = 0;
		min_time_right[i][0] = 0;
	}
	int mtl, mtr;
	bool flag = true;
	for (int j = 1; j < n && flag; j++) {
		for (int i = 0; i < n - j && flag; i++) {
			mtl = min(min_time_left[i + 1][j - 1] + positions[i + 1] - positions[i], min_time_right[i + 1][j - 1] + positions[i + j] - positions[i]);
			if (mtl <= times[i])
				min_time_left[i][j] = mtl;
			mtr = min(min_time_right[i][j - 1] + positions[i + j] - positions[i + j - 1], min_time_left[i][j - 1] + positions[i + j] - positions[i]);
			if (mtr <= times[i + j])
				min_time_right[i][j] = mtr;
			if (min_time_left[i][j] == MAX_NUMB && min_time_right[i][j] == MAX_NUMB) {
				flag = false;
			}
		}
	}
	if (flag) {
		int min_time = min(min_time_left[0][n - 1], min_time_right[0][n - 1]);
		fout << min_time;
	}
	else fout << "No solution";
	return 0;
}
