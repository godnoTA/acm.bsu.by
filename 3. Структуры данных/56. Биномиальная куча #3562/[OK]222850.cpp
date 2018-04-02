#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector> 
#include <string>
#include <iterator>

using namespace std;

int findDeg (long long val)
{
	int deg = 0;
	while (val != 1)
	{
		val /= 2;
		deg++;
	}
	return deg;
}

int main()
{
	FILE *in, *out;
	long long n;
	in = fopen("input.txt", "rt");
	out = fopen("output.txt", "wt");
	fscanf(in, "%lld", &n);
	fclose(in);
	vector<int> v = vector<int>();
	try
	{
		for (int i = findDeg(n); i > -1; i--)
		{
			if ((n / (long long)pow(2, i)) == 1)
			{
				v.push_back(i);
				n -= (long long)pow(2, i);
			}
		}
	}
	catch (...)
	{
		fprintf(out, "-1\n");
		return 0;
	}
	for (int i = v.size() - 1; i >= 0; i--)
		fprintf(out, "%d\n", v[i]);
	fclose(out);
	return 0;
}