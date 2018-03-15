#include <fstream>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
	long long a, n;
	ifstream f("input.txt");
	f >> n;
	long long *arr;
	arr = new long long [n];
	for (int i = 0; i < n; i++)
	{
		f >> a;
		arr[i] = a;
	}
	f.close();
	for (int i = 0; i < n/2; i++)
	{
		if (arr[2*i+1] < arr[i] )
		{
			ofstream of("output.txt");
			of << "No";
			of.close();
			return 0;
		}
		if(2*i+2<n)
		if(arr[2*i+2] < arr[i])
		{
			ofstream of("output.txt");
			of << "No";
			of.close();
			return 0;
		}
	}
	
	ofstream of("output.txt");
	of << "Yes";
	of.close();
	return 0;
}