#include <iostream>
#include <fstream>
#include <algorithm>
#include <list>
#include <vector>

using namespace std;

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	int	n,a,b;
	int mas[105];
	for (int i = 0; i < 105; i++) mas[i] = 0;
	f1 >> n;
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= n; j++)
		{
			f1 >> a;
			if (a == 1) mas[j] = i;
		}
	for (int i = 1; i <= n; i++)
		f2 << mas[i] << ' ';
	f1.close();
	f2.close();
	return 0;
}