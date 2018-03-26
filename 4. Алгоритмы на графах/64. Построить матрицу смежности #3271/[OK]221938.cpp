#include <fstream>
#include <iostream>

using namespace std;



int main()
{
	int m, n, **arr;
	ifstream f("input.txt");
	f >> n >> m;
	arr = new int *[n];
	for (int i = 0; i < n; i++)
	{
		arr[i] = new int[n];
	}
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			arr[i][j] = 0;
		}
	}
	int a, b;
	for (int i = 0; i < m;i++)
	{
		f >> a;
		f >> b;
		arr[a-1][b-1] = 1;
		arr[b-1][a-1] = 1;
	}
	f.close();
	ofstream of("output.txt");
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			of<<arr[i][j]<<" ";
		}
		of << '\n';
	}
	of.close();
	return 0;
}