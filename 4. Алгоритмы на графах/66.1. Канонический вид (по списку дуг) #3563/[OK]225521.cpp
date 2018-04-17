#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");

	int n;
	in >> n;
	n++;

	int* p = new int[n];
	for (int i = 0; i < n; i++)
		p[i] = 0;

	int u, v;
	for (int i = 0; i < n; i++)
	{
		in >> u;
		in >> v;
		p[v] = u;
	}

	for (int i = 1; i < n; i++)
		out << to_string(p[i]) << " ";
	delete[] p;

	return 0;
}