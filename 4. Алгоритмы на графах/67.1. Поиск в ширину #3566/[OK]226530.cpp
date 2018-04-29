#include <iostream>
#include <fstream>
#include <queue>
using namespace std;

int main()
{
	int n = 0;
	int node = 0;
	int* arr;
	fstream input("input.txt");
	fstream output("output.txt", ios::out);
	input >> n;
	arr = new int[n];
	int **mtr = new int *[n];
	for (int i = 0; i < n; i++)
	{
		mtr[i] = new int[n];
	}
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			input >> mtr[i][j];
		}
	}
	bool *visited = new bool[n];
	for (int i = 0; i < n; i++)
	{
		visited[i] = false;
	}
	queue<int> q;
	int count = 1;
	for (int i = 0; i < n; i++)
	{
		if (visited[i] == false)
		{
			q.push(i);
			arr[i] = count;
			count++;
			visited[i] = true;
			while (!q.empty())
			{
				node = q.front();
				q.pop();
				for (int i = 0; i < n; i++)
				{
					if (mtr[node][i] == 1)
					{
						if (visited[i] == false)
						{
							q.push(i);
							arr[i] = count;
							count++;
							visited[i] = true;
						}
					}
				}
			}
		}
	}
	for (int i = 0; i < n; i++)
	{
		output << arr[i] << " ";
	}
	
	system("pause");
	return 0;
}

