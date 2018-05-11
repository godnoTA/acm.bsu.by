#include <iostream>
#include <fstream>

using namespace std;


void move(int**A, int **B, bool **C, int n, int x, int y) {
	B[x][y] = A[x][y];
	C[x][y] = true;
	if (x > 0) {
		if (A[x - 1][y] < A[x][y] && C[x-1][y]==false) {
			move(A, B, C, n, x - 1, y);
		}
	}
	if (y > 0) {
		if (A[x][y - 1] < A[x][y] && C[x][y-1] == false) {
			move(A, B, C,  n, x, y - 1);
		}
	}
	if (x < n-1) {
		if (A[x + 1][y] < A[x][y] && C[x + 1][y] == false) {
			move(A, B, C, n, x + 1, y);
		}
	}
	if (y < n-1) {
		if (A[x][y + 1] < A[x][y] && C[x][y+1] == false) {
			move(A, B, C, n, x, y + 1);
		}
	}
}

int main() {
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	int n, x1, y1, x2, y2;
	fin >> n;
	fin >> x1;
	fin >> y1;
	fin >> x2;
	fin >> y2;
	int ** field = new int *[n];
	for (int i = 0; i < n; i++)
		field[i] = new int[n];
	bool ** isVisited = new bool *[n];
	for (int i = 0; i < n; i++)
		isVisited[i] = new bool[n];
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++)
			isVisited[i][j] = false;
	}
	int ** ans = new int *[n];
	for (int i = 0; i < n; i++)
		ans[i] = new int[n];
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++)
			fin >> field[i][j];
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++)
			ans[i][j] = 0;
	}
	move(field, ans, isVisited, n, x1 - 1, y1 - 1);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n-1; j++) {
			fout << ans[i][j] << " ";	
		}
		fout << ans[i][n - 1];
		fout << endl;
	}
	system("pause");
	return 0;
}