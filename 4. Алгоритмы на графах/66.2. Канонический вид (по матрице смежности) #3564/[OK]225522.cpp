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

	int x;
	for (int i = 1; i < n; i++)
	for (int j = 1; j < n; j++)
	{
		in >> x;
		if (x == 1)
			p[j] = i;
	}
	


	for (int i = 1; i < n; i++)
		out << to_string(p[i]) << " ";
	delete[] p;

	return 0;
}