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
	int mas[100005];
	for (int i = 0; i < 100005; i++) mas[i] = 0;
	f1 >> n;
	for (int i = 0; i < n-1; i++)
	{
		f1 >> a >> b;
		mas[b] = a;
	}
	for (int i = 1; i <= n; i++)
		f2 << mas[i] << ' ';
	f1.close();
	f2.close();
	return 0;
}