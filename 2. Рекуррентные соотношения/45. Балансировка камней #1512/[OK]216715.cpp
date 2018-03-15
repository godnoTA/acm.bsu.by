#include <iostream>
#include <string>
#include <fstream>
#include <vector>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

int main()
{
	long int n, k;
	int d;
	fin >> n >> k >> d;
	string w;
	fin >> w;
	string wbeg;
	for (int i = 0; i < n - k; i++)
	{
		wbeg += '0';
	}
	wbeg += w;
	w = wbeg;
	long long f, g;
	if (w[n - 1] == '1')
	{
		f = 1; g = 1;
	}
	else {
		f = 0;
		g = 1;
	}
	for (int i = n - 2; i != 0; i--)
	{
		if (w[i] == '1')
		{
			f = f + g;
			f = f % d;
		}
		else {
			g = f + g;
			g = g % d;
		}
	}
	fout << (f + g) % d << "\n";

	return 0;
}