#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

int main()
{
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	long long int n,k(0);
	f1 >> n;
	while (n != 0)
	{
		if (n % 2 == 1) f2 << k << endl;
		n /= 2;
		k++;
	}
	f1.close();
	f2.close();
	return 0;
}