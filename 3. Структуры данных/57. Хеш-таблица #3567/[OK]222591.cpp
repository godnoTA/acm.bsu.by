#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	bool flag = true;
	int m, c, n, b, h, v(0);
	int mas[10005];
	int mass[10005];
	for (int i = 0; i < 10005; i++) mas[i] = -1;
	f1 >> m >> c >> n;
	for (int i = 0; i < n; i++)
	{
		flag = true;
		h = 0;
		f1 >> b;
		for (int j = 0; j<v; j++)
			if (mass[j] == b) { flag = false; break; }
		if (flag)
		{
			mass[v++] = b;
			while (mas[((b%m) + c * h) % m] != -1) h++;
			mas[((b%m) + c * h) % m] = b;
		}
	}
	for (int i = 0; i < m; i++) f2 << mas[i] << ' ';
	f1.close();
	f2.close();
	return 0;
}