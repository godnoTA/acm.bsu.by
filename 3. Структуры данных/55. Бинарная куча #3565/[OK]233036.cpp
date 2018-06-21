#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	long n;
	fin >> n;
	long long *m = new long long[n];
	for (long i = 0; i < n; i++)
		fin >> m[i];
	bool indicator = false;
	for (long i = 0; i < n; i++)
	{
		if (i + 1 > n / 2)
		{
			indicator = true;
			break;
		}
		if (i + 1 == n / 2.0)
		{
			if (m[i] > m[2 * i + 1])
				break;
			else {
				indicator = true;
				break;
			}
		}
		if (m[i] > m[2 * i + 1] || m[i] > m[2 * i + 2])
			break;
	}
	if (indicator == true)
		fout << "Yes\n";
	else fout << "No\n";
	//system("pause");
	return 0;
}