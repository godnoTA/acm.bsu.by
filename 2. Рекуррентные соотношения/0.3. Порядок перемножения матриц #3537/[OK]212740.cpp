#include <fstream>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Pair
{
	long long a1;
	long long a2;
};

int main()
{
	long long a, b, n;
	ifstream f("input.txt");
	f >> n;
	vector<Pair> vec;
	for (int i = 0; i < n; i++)
	{

		Pair p;
		f >> p.a1;
		f >> p.a2;
		vec.push_back(p);
	}
	f.close();
	long long **arr;
	arr = new long long *[n];
	for (int i = 0; i < n; i++)
		arr[i] = new long long [n];


	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			arr[i][j] = 0;

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n - 1 - i; j++)
		{
			arr[j][j + 1 + i] = LONG_MAX;
			for (int k = j; k <= j+i ; k++) {
				arr[j][j + 1 + i] = min(arr[j][j + 1 + i],arr[j][k] + arr[k+1][j + 1 + i] + vec[j].a1*vec[k].a2*vec[j + 1 + i].a2);
			}
		}
	}


	

	ofstream of("output.txt");
	of << arr[0][n-1];
	of.close();

	return 0;
}