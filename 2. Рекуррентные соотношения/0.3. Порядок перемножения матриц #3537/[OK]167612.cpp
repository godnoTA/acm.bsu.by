#include <iostream>
#include <fstream>
#include <math.h>
#include <algorithm> 
using namespace std;

ifstream inStream("input.txt", ios::in);
ofstream outStream("output.txt", ios::out);


int countMultiplications(int p[], int N) 
{
	int **matr = new int*[N];
	for (int i = 0; i < N; i++)
	{
		matr[i] = new int[N];
	}

	for (int i = 0; i < N; i++)
	{
		matr[i][i] = 0;
	}

	for (int k = 2; k < N; k++)
	{
		for (int i = 1; i < N - k + 1; i++)
		{
			int j = i + k - 1;
			matr[i][j] = INT_MAX;
			for (int l = i; l < j; l++)
			{
				matr[i][j] = min(matr[i][j], matr[i][l] + matr[l + 1][j] + p[i - 1] * p[l] * p[j]);
			}
		}
	}
	return matr[1][N - 1];
}

int main() 
{
	int ch, N;
	inStream >> N;
	int *a = new int[N + 1];
	for (int i = 0; i<N; i++)
	{
		inStream >> ch;
		a[i] = ch;
		inStream >> ch;
	}
	a[N] = ch;
	outStream << countMultiplications(a, N + 1);
	return 0;
}
