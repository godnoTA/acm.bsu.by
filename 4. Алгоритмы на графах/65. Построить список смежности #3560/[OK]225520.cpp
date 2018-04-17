#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n, m;
	in >> n;
	n++;
	in >> m;
	int* kol = new int[n];
	string* peeks = new string[n];
	for (int i = 0; i < n; i++)
	{
		kol[i] = 0;
		peeks[i] = "";
	}
	int x, y;
	for (int i = 0; i < m; i++)
	{
		in >> x;
		in >> y;
		kol[x]++;
		kol[y]++;
		peeks[x] += to_string(y) + " ";
		peeks[y] += to_string(x) + " ";
	}

	for (int i = 1; i < n; i++)
		out << to_string(kol[i]) << " " << peeks[i] << endl;

	delete[] kol;
	delete[] peeks;

	return 0;
}