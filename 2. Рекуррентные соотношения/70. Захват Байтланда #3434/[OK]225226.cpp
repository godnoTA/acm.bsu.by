#include <fstream>
#include <iostream>

using namespace std;
int* trainline;

int***pieces;
int F(int i, int j, int k) {
	int piece = 0;
	if (pieces[i][j][k] != 0) {
		return pieces[i][j][k];
	}
	if (k == 0)
	{
		for (int a = i; a < j; a++) {
			for (int b = a + 1; b <= j; b++) {
				piece += trainline[a] * trainline[b];
			}
		}
	}
	if (k > 0) {
		piece= INT_MAX;
		for (int s = 0; s <= j - i-1; s++) {
			
				if ( (j - i - s) < k) {
					break;
				}
				int tmp=(F(i, i + s, 0) + F(i + s + 1, j, k -1));	
				if (tmp < piece) {
					piece = tmp;
				}
		}
	}
	pieces[i][j][k] = piece;
	return piece;

}

void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n;
	int m;
	in >> n;
	in >> m;
	trainline = new int[n];
	for (int i = 0; i < n; i++) {
		in >> trainline[i];
	}
	pieces = new int**[n];
	for (int i = 0; i < n; i++) {
		pieces[i] = new int*[n];
		for (int j= 0; j < n; j++) {
			pieces[i][j] = new int[m+1];
			for (int k = 0; k < m+1; k++) {
				pieces[i][j][k]=0;
			}
		}
	}
	out << F(0, n-1, m) << endl;
}