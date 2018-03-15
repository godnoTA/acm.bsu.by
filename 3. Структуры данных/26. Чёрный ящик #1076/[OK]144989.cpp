#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <functional>

using namespace std;

priority_queue <int, vector <int>, less<int> > wait_get;
priority_queue <int, vector <int>, greater<int> > overflowInWaiting;

void needToGet(int* dataOfBB, int &currentSizeOfBB)
{
	if (!overflowInWaiting.empty())
	{
		wait_get.push(overflowInWaiting.top());
		overflowInWaiting.pop();
	}
	else
	{
		wait_get.push(dataOfBB[currentSizeOfBB]);
		currentSizeOfBB++;
	}
}

void justADD(int* dataOfBB, int &currentSizeOfBB)
{
	if (wait_get.top() > dataOfBB[currentSizeOfBB])
	{
		overflowInWaiting.push(wait_get.top());
		wait_get.pop();
		wait_get.push(dataOfBB[currentSizeOfBB]);
	}
	else
		overflowInWaiting.push(dataOfBB[currentSizeOfBB]);
	currentSizeOfBB++;
}

void main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int n, m;
	fin >> n >> m;

	int* dataOfBB = new int[n];
	for (int i = 0; i < n; i++)
		fin >> dataOfBB[i];

	int* inGet = new int[m];
	for (int i = 0; i < m; i++)
		fin >> inGet[i];

	int i = 0;
	int currentSizeOfBB = 1;
	wait_get.push(dataOfBB[0]);

	while (i < m)
	{
		if (currentSizeOfBB == inGet[i])
		{
			i++;

			if (i == m)
				fout << wait_get.top();
			else
				fout << wait_get.top() << " ";

			needToGet(dataOfBB, currentSizeOfBB);
		}
		else
			justADD(dataOfBB, currentSizeOfBB);
	}
}