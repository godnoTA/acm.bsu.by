
#include <iostream>
#include  <alg.h>
#include <fstream>
#include <string>

using namespace std;

const int inf = 1e5+2;
int a[inf];

int main()
{
	int n, i = 1;
	ifstream fin("input.txt", ios_base::in);
	fin >> n;
	while (!fin.eof() && i<=n) {
		fin >> a[i];
		i++;
	}

	string str = "Yes";

	if (n % 2 == 1)
	{
		for (int i = 1; i <= n / 2; i++)
		{
			if ((a[i] > a[2 * i] || a[i] > a[2 * i + 1]))
				str = "No";
		}
	}
	else {
		for (int i = 1; i <= n / 2; i++)
		{
			if ((a[i] > a[2 * i]))
				str = "No";
		}
	}
	ofstream fout("output.txt", ios_base::out);
	fout << str;
	cout << str;
	fout.close();
}