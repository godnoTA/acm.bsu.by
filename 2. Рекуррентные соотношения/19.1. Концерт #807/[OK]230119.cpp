#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <vector>
#include <cmath>

const int N_MAX = 201;
const int M_MAX = 51;
const int D_MAX = 1001;
int matrix[N_MAX][M_MAX][D_MAX];



int main() {
	std::ios_base::sync_with_stdio(false);
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	int n, m, d;
	int *songs_duration;
	std::cin >> n >> m >> d;
	songs_duration = new int[n];
	for (int i = 0; i < n; i++) {
		std::cin >> songs_duration[i];
	}
	for (int i = 0; i < N_MAX; i++) {
		for (int j = 0; j < M_MAX; j++) {
			for (int k = 0; k < D_MAX; k++) {
				matrix[i][j][k] = -1;
			}
		}
	}

	if (songs_duration[0] < d) {
		matrix[0][0][d - songs_duration[0]] = 1;
	}
	else if (songs_duration[0] == d) {
		matrix[0][1][d] = 1;
	}
	matrix[0][0][d] = 0;

	for (int i = 1; i < n; i++) {
		for (int j = 0; j < m; j++) {
			for (int k = 1; k <= d; k++) {
				if (matrix[i - 1][j][k] != -1) {
					if (songs_duration[i] < k) {
						matrix[i][j][k - songs_duration[i]] = fmax(matrix[i - 1][j][k] + 1, matrix[i][j][k - songs_duration[i]]);
					}
					else if (songs_duration[i] == k && j != m) {
						matrix[i][j + 1][d] = fmax(matrix[i - 1][j][k] + 1, matrix[i][j + 1][d]);
					}
					else if (songs_duration[i] < d && j < m - 1) {
						matrix[i][j + 1][d - songs_duration[i]] = fmax(matrix[i - 1][j][k] + 1, matrix[i][j + 1][d - songs_duration[i]]);
					}
					else if (songs_duration[i] == d && j < m - 1) {
						matrix[i][j + 2][d] = fmax(matrix[i - 1][j][k] + 1, matrix[i][j + 2][d]);
					}
					matrix[i][j][k] = fmax(matrix[i][j][k], matrix[i - 1][j][k]);
				}
			}
		}
	}
	int max = 0;
	for (int i = 0; i <= m; i++) {
		for (int j = 1; j <= d; j++) {
			if (matrix[n - 1][i][j] > max) {
				max = matrix[n - 1][i][j];
			} 
		}
	} 
	std::cout << max;
	

	fclose(stdout);
	fclose(stdin);
	system("pause");
	return 0;
}