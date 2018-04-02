#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	int n;
	bool flag = true;
	f1 >> n;
	int mas[100005];
	for (int i = 1; i <= n; i++) f1 >> mas[i];
	for (int i = 1; i <= n / 2; i++)
	{
		if (i == n / 2 && ((n/2)*2 == n))
		{
			if (mas[n] < mas[n / 2]) flag = false;
		}
		else
		{
			if (mas[i * 2] < mas[i] || mas[i * 2 + 1] < mas[i]) flag = false;
		}
		if (!flag) break;
	}
	if (flag) f2 << "Yes";
	else f2 << "No";
	f1.close();
	f2.close();
	return 0;
}