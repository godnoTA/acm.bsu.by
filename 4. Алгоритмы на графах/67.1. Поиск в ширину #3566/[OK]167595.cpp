#include <iostream>
#include <fstream>
#include <queue>


using namespace std;

ifstream inStream("input.txt", ios::in);
ofstream outStream("output.txt", ios::out);
class Node
{
	public:
		int priority = 0;
		bool visited = false;
};
int main()
{
	int N, t = 0;
	inStream >> N;
	int **tree = new int*[N];
	queue <int> qu;
	for (int i = 0; i < N; i++)
	{
		tree[i] = new int[N];
	}

	for (int i = 0;i < N;i++)
	{
		for (int j = 0;j < N;j++)
		{
			inStream >> tree[i][j];
		}
	}
	inStream.close();

	Node * bfs = new Node[N];
		
	for (int i = 0;i < N;i++)
	{
		if (bfs[i].visited == false)
		{
			qu.push(i);
			bfs[i].visited = true;
			bfs[i].priority = ++t;
			while (!qu.empty())
			{
				int a = qu.front();
				for (int j = 0;j < N;j++)
				{
					if (tree[a][j] == 1 && bfs[j].visited == false)
					{
						qu.push(j);
						bfs[j].visited = true;
						bfs[j].priority = ++t;
					}
				}
				qu.pop();
			}
		}
	}	
	
	for (int i = 0; i < N; i++)
	{
		outStream << bfs[i].priority << " ";
	}
	outStream << "\n";
	outStream.close();
	return 0;
}
