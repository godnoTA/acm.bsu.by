#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

ifstream inStream("input.txt");
ofstream outStream("output.txt");

int main()
{
	int N;
	inStream >> N;
	
	long long element;
	int *X = new int[N];
	int *P = new int[N];
	int *M = new int[N+1];
	int L = 0, low, high, mid = 0, newL = 0;
	//M[0] = 0;

	for (int i = 0; i < N; i++)
	{
		inStream >> element;
		/*P[i] = element;
		M[i] = element;*/
		X[i] = element;
	}

	for (int i = 0; i < N; i++)
	{
		low = 1;
		high = L;
		while (low <= high)
		{
			mid = ceil((low + high) / 2);
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
		
	}
	outStream << L;
	return 0;
}