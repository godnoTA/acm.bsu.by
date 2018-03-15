#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

vector<vector<int>> v;

int main()
{
	ifstream in("input.txt");

	int n, m;
	in >> n;
	in >> m;

	for (int i = 0; i < n; i++) {
		vector<int> each;
		v.push_back(each);
	}
	for (int i = 0; i < m; i++) {
		int a, b;
		in >> a;
		in >> b;

		v[a - 1].push_back(b);
		v[b - 1].push_back(a);
	}

	ofstream out("output.txt");

	for (int i = 0; i < v.size(); i++) {
		out << v[i].size() << " ";
		for (int j = 0; j < v[i].size(); j++) {
			out << v[i][j] << " ";
		}
		out << "\n";
	}
	out.close();
    return 0;
}

