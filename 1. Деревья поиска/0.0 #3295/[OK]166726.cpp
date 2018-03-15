#include <iostream>
#include <fstream>
#include <math.h>
#include <set>

using namespace std;

ifstream inStream("input.txt", ios::in);
ofstream outStream("output.txt", ios::out);

int main()
{
	int ch;
	set <int> keys;
	while (inStream >> ch)
	{
		keys.insert(ch);
	}
	long long sum = 0;
	for (set<int>::iterator i = keys.begin(); i != keys.end(); i++)
	{
		sum += (*i);
	}
	outStream << sum << "\n";
	inStream.close();
	outStream.close();
	return 0;
}