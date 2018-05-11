#include <fstream>
#include <iostream>
 
using namespace std;
 
int main ()
{
    ifstream fin ("input.txt");
    ofstream fout ("output.txt");

	int n, m, d, t;

	fin >> n;
	fin >> m;
	d = m;
	t = n;

	long double **matrix = new long double* [n];
    for (int i = 0; i < n; i++) matrix[i] = new long double [n];

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            matrix[i][j] = 0;

	for (int i = 0; i < d; i++)
	{
		fin >> n;
		fin >> m;
		matrix[n-1][m-1] = 1;
		matrix[m-1][n-1] = 1;
	}
	fin.close();

	for (int i = 0; i < t; i++)
	{
		for (int j = 0; j < t; j++)
		{
			if (j == t - 1)
				fout << matrix[i][j];
			else
				fout << matrix[i][j] << " ";
		}
		fout << endl;
	}

	fout.close();
    return 0;
}

