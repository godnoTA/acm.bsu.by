#include <fstream>
#include <iostream>

using namespace std;

int main()
{
	int n, a,b, *arr2;
	ifstream f("input.txt");
	f >> n;
	arr2 = new int[n];
	for (int j = 0; j < n; j++)
	{
		arr2[j] = 0;
	}
	for (int i = 0; i < n; i++)
	{
		f >> a;
		f >> b;
		arr2[b - 1] = a;
	}

	f.close();
	ofstream of("output.txt");
	for (int i = 0; i < n; i++)
	{
		of << arr2[i] << " ";
	}
	of.close();
	return 0;
}