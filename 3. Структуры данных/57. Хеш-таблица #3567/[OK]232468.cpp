#include<iostream>
#include <fstream>

using namespace std;

void insert(long long *mas, long long m, long long x, long long c) {
	long long i = 0;
	for (int j = 0; j < m; j++)
		if (mas[j] == x)
			return;
	while (true) {
		long long ind = (x % m + c * i) % m;
		if (mas[ind] == -1)
		{
			mas[ind] = x;
			break;
		}
		i++;
	}
}

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	long long m, c, n;
	fin >> m >> c >> n;
	long long *dict = new long long [m];
	for (long long i = 0; i < m; i++) {
		dict[i] = -1;
	}
	for (long long i = 0; i < n; i++)
	{
		long long x;
		fin >> x;
		insert(dict, m, x, c);
	}

	for (long long i = 0; i < m; i++)
	{
		fout << dict[i] << " ";
	}
	return 0;
}