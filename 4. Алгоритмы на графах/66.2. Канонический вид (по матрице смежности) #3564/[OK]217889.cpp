#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

vector<vector<int>> v;

int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n;
	in >> n;

	if (n == 1) {
		out << "0";
		return 0;
	}

	int* p = new int[n];

	int** matrix = new int*[n];

	for (int i = 0; i < n; i++) {
		matrix[i] = new int[n];
	}
	for (int i = 0; i < n; i++) {
		p[i] = 0;
		for (int j = 0; j < n; j++) {
			in >> matrix[i][j];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (matrix[j][i] == 1) {
				p[i] = j + 1;
			}
		}
	}


	for (int i = 0; i < n; i++) {
		out << p[i] << " ";
	}
	out.close();
	return 0;
}

