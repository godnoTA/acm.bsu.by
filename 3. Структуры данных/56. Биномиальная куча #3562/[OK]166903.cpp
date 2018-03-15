#include <iostream>
#include <fstream>
#include <math.h>
#include <vector>

using namespace std;

ifstream inStream("input.txt", ios::in);
ofstream outStream("output.txt", ios::out);

int main()
{
	long long N, sum = 0;
	inStream >> N;
	inStream.close();
	vector <int> kuchka;
	for (int i = (int)log2(N); i >= 0; i--) 
	{
		long long power = 1;
		for (int l = 0; l < i; l++)
		{
			power *= 2;
		}
		if ((sum + power) <= N)
		{
			sum += power;
			kuchka.push_back(i);
		}
	}
	for (int i = kuchka.size() - 1; i >= 0; i--)
	{
		outStream << kuchka[i] << "\n";
	}
	outStream << "\n";
	outStream.close();
	return 0;
}

