#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>
#include <set>
#include <string>

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	long long n = 0;
	set<long long>s;
	set<long long>::iterator stit;
	if (!fin)
		return 0;
	while (!fin.eof())
	{
		fin >> n;
		s.insert(n);
	}
	n = 0;
	for (stit = s.begin(); stit != s.end(); stit++)
		n += *stit;
	fout << n;
	cout << n;
	//system("pause");
	return 0;
}