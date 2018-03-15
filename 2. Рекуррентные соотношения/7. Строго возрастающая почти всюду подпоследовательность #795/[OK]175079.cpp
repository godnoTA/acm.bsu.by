#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <cmath>
using namespace std;

ifstream inStream("input.txt");
ofstream outStream("output.txt");

int main()
{
	int N;
	inStream >> N;
	if(N ==1)
	{
		outStream << 1 << "\n";
		return 0; 
	}
	long long element;
	int *X = new int[N];
	int *P = new int[N];
	int *M = new int[N + 1];

	int *up = new int[N + 1];
	up[0] = 0;
	int *X2 = new int[N];
	int L = 0, low, high, mid = 0, newL = 0;
	int B, max = 0;
	for (int i = 0; i < N; i++)
	{
		inStream >> element;
		X[i] = element;
		X2[N - 1 - i] = element;
	}

	L = 0;
	for (int i = 0; i < N - 1; i++)//до нашего элемента
	{
		low = 1;
		high = L;
		while (low <= high)
		{
			mid = ceil((double)(low + high) / 2);
			if (X[M[mid]] < X[i])
			{
				low = mid + 1;
			}
			else
			{
				high = mid - 1;
			}

		}
		newL = low;
		P[i] = M[newL - 1];
		M[newL] = i;
		if (newL > L)
		{
			L = newL;
		}
		up[i + 1] = L;
	}

	L = 0;
	for (int i = 0; i < N - 1; i++)
	{
		low = 1;

		high = L;
		while (low <= high)
		{
			mid = ceil((double)(low + high) / 2);
			if (X2[M[mid]] > X2[i])
			{
				low = mid + 1;
			}
			else
			{
				high = mid - 1;
			}

		}
		newL = low;
		P[i] = M[newL - 1];
		M[newL] = i;
		if (newL > L)
		{
			L = newL;
		}
		up[N - i - 1] += L;
	}

	for (int i = 0; i< N; i++)
		if (max<up[i])
			max = up[i];
	outStream << max << "\n";
	return 0;
}