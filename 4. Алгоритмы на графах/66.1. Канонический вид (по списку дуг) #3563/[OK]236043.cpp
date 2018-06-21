#include <fstream>
#include <iostream>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int n, m, d, t;

	fin >> n;
	t = n;

	long double *matrix = new long double[n];

	for (int i = 0; i < n; i++)
		matrix[i] = 0;

	for (int i = 0; i < t - 1; i++)
	{
		fin >> n;
		fin >> m;
		matrix[m - 1] = n;
	}
	fin.close();

	for (int j = 0; j < t; j++)
	{
		if (j == t - 1)
			fout << matrix[j];
		else
			fout << matrix[j] << " ";
	}


	fout.close();
	return 0;
}

