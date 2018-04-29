#include <fstream>
#include <iostream>
using namespace std;

int min(int a, int b) {
	if (a < b)
		return a;
	else
		return b;
}

int min(int a, int b, int c) {
	int min = a;
	if (b < min)
		min = b;
	if (c < min)
		min = c;
	return min;
}

int MAT[1000][1000];
int MIN[1000][1000];

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n, m;
	fin >> n;
	fin >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++)
			fin >> MAT[i][j];
	}
	MIN[0][0] = MAT[0][0];
	if (m == 1) {
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum = sum + MAT[i][0];
		fout << sum;
		cout << sum;
	}
	else if(n==1){
		fout << -1;
		cout << -1;
	
	}
	else {
		MIN[1][0] = MAT[1][0] + MAT[0][0];
		MIN[1][1] = MAT[1][1] + MAT[0][0];
		for (int i = 2; i < m; i++) {
			MIN[i][0] = min(MIN[i - 1][0], MIN[i - 1][1]) + MAT[i][0];
			for (int j = 1; j < i - 1; j++) {
				MIN[i][j] = min(MIN[i - 1][j - 1], MIN[i - 1][j], MIN[i - 1][j + 1]) + MAT[i][j];
			}
			MIN[i][i - 1] = min(MIN[i - 1][i - 2], MIN[i - 1][i - 1]) + MAT[i][i - 1];
			MIN[i][i] = MIN[i - 1][i - 1] + MAT[i][i];
		}
		for (int i = m; i < n; i++) {
			MIN[i][0] = min(MIN[i - 1][0], MIN[i - 1][1]) + MAT[i][0];
			for (int j = 1; j < m - 1; j++) {
				MIN[i][j] = min(MIN[i - 1][j - 1], MIN[i - 1][j], MIN[i - 1][j + 1]) + MAT[i][j];
			}
			MIN[i][m - 1] = min(MIN[i - 1][m - 2], MIN[i - 1][m - 1]) + MAT[i][m - 1];
		}
		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++)
				MIN[i][j] = -1;
		}
		fout << MIN[n - 1][m - 1];
		cout << MIN[n - 1][m - 1];
	}
	
	/*cout << endl;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++)
			cout << MIN[i][j] << " ";
		cout << endl;
	}*/
	fin.close();
	fout.close();
    return 0;
}