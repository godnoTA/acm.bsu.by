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
	long long * kucha = new long long[N];
	for (int i = 0; i < N; i++)
	{
		inStream >>kucha[i];
	}
	inStream.close();
	
	bool minheap = true;
	for (int i = 0; i  <N; i++)
	{
		if ((2 * (i + 1) - 1 < N)&&(kucha[i] > kucha[2 * (i + 1) - 1]))
		{
			minheap = false;
			break;
		}
		if ((2 * (i + 1) < N)&&(kucha[i] > kucha[2 * (i + 1)]))
		{
			minheap = false;
			break;
		}
	}
	outStream << ((minheap == true)? "Yes" : "No");
	outStream << "\n";
	outStream.close();
	return 0;
}

