#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <vector>
#include <cmath>
#include <iomanip>
const int INF = INT_MAX;
const int MAX_N = 100;
double matrix[MAX_N][2];
std::vector <long double> coordinates;
int main() {
	std::ios_base::sync_with_stdio(false);
	freopen("in.txt", "r", stdin);
	freopen("out.txt", "w", stdout);
	int n;
	std::cin >> n;
	for (int i = 0; i < n; i++) {
		double temp;
		std::cin >> temp;
		coordinates.push_back(temp);
	}
	for (int i = 0; i < n; i++) {
		matrix[i][0] = INF;
		matrix[i][1] = INF;
	}
	matrix[1][0] = 0;
	matrix[1][1] = abs(coordinates[0] - coordinates[1]);
	
	for (int i = 1; i < n - 1; i++) {
		matrix[i][0] = matrix[i - 1][1];
		long double left = abs(coordinates[i] - coordinates[i + 1]);
		matrix[i + 1][1] = fmin(matrix[i][0], matrix[i][1]) + left;
	}
	std::cout << std::fixed;
	std::cout << std::setprecision(2) << matrix[n - 1][1];





	fclose(stdout);
	fclose(stdin);
	system("pause");
	return 0;
}