
#include <iostream>
#include <fstream>
#include <queue>
#include <algorithm>
#include <string>
using namespace std;

int mas[101][101];
bool visit[101];
int path[101];
queue<int> myQueue;

int main()
{
	ifstream fin("input.txt", ios_base::in);
	ofstream fout("output.txt", ios_base::out);
	int n;
	fin >> n;

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			fin >> mas[i][j];
			path[i] = 0;
			visit[i] = false;
		}

	}

	int label =0;

	for (int j = 0; j < n; j++)
	{
		if (visit[j] == 0) {
			++label;
			path[j] = label;
			visit[j] = true;
			myQueue.push(j+1);
			while (!myQueue.empty())
			{
				int element = myQueue.front();
				myQueue.pop();
				for (int i = 0; i < n; i++)
				{
					if (mas[element - 1][i] == 1 && visit[i] == 0)
					{
						myQueue.push(i + 1);
						visit[i] = true;
						path[i] = label + 1;
						++label;
					}
				}
			}
		}
	}

	for (int i = 0; i < n; i++)
	{
		fout << path[i] << " ";

	}

	fout.close();
	return 0;
}