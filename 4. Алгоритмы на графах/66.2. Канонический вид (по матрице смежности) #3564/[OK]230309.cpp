#include <fstream>
#include <iostream>
 
using namespace std;
 
int main ()
{
    ifstream fin ("input.txt");
    ofstream fout ("output.txt");

	int n, m, d, t;

	fin >> n;
	t = n;

	long double *matrix1 = new long double[n];
	
	for (int i = 0; i < n; i++)
		matrix1[i] = 0;



    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
		{
			fin >> m;
			if (m == 1)
				matrix1[j] = i + 1; 
		}
			


	fin.close();


	for (int j = 0; j < t; j++)
	{
		if (j == t - 1)
			fout << matrix1[j];
		else
			fout << matrix1[j] << " ";
	}
		

	fout.close();
    return 0;
}

