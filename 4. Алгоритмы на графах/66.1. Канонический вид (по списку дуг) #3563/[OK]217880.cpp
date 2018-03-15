#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

vector<vector<int>> v;

int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n, m;
	in >> n;

	if (n == 1) {
		out << "0";
		return 0;
	}

	int* p = new int[n];

	for (int i = 0; i < n; i++) {
		p[i] = 0;
 	}

	for (int i = 0; i < n - 1; i++) {
		int u, v;
		in >> u;
		in >> v;
		p[v - 1] = u;
	}


	for (int i = 0; i < n; i++) {
		out << p[i] << " ";
	}
	out.close();
    return 0;
}

