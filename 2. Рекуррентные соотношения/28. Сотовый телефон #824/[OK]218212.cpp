#include <fstream>
#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int main()
{
	long long *arr, m, n, s=0, **matrix;
	ifstream f("in.txt");
	f >> n;
	f >> m;
	arr = new long long[m];
	for (int i = 0; i < m; i++)
	{
		f >> arr[i];
	}
	f.close();
	cout << n << " ";
	cout << m << " ";
	for (int i = 0; i < m; i++)
		cout << arr[i] << " ";
	////
	if (n == 0)
	{
		s == 0;
		ofstream of("out.txt");
		of << s;
		of.close();
		return 0;
	}
	else {
		if (n == 1)
		{
			for (int i = 0; i < m; i++)
				s += (i + 1)*arr[i];
			ofstream of("out.txt");
			of << s;
			of.close();
			return 0;
		}
		else
		{
		}
	}
	matrix = new long long *[n];
	for (int i = 0; i < n; i++)
	{
		matrix[i] = new long long[m];
	}
	
	int k = 0;
	long long a, b;
	for (int i = 0; i < n; i++)
	{
		k = 2;
		for (int j = i; j < m; j++)
		{
			if (j == 0) {
				matrix[i][j] = arr[j];
			}
			else
			if (j == i) {
				matrix[i][j] = matrix[i-1][j-1]+arr[j];
			}
			else {
				matrix[i][j] = matrix[i][j - 1] + k*arr[j];
				k++;
			}
		}
		if(i>0)
		for (int j = i+1; j < m; j++) {
			a = matrix[i - 1][j - 1];
			k = 1;
			for (int l = j; l < m; l++)
			{
				a += k*arr[l];
				k ++;
				if(matrix[i][l]>a)
				matrix[i][l] = a;
			}
		}
	}
	ofstream of("out.txt");
	of << matrix[n-1][m-1];
	of.close();
	return 0;
}