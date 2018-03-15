#include <iostream>
#include <fstream>
#include <stack>

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
	stack <int> queue;
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
			queue.push(i);
			bfs[i].visited = true;
			bfs[i].priority = ++t;
			while (!queue.empty())
			{
				int a = queue.top(), j;
				for (j = 0;j < N;j++)
				{
					if (tree[a][j] == 1 && bfs[j].visited == false)
					{
						queue.push(j);
						bfs[j].visited = true;
						bfs[j].priority = ++t;
						break;
					}
				}
				if (j == N)
					queue.pop();
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