#include <fstream>
#include <queue>

using namespace std;

int main()
{
	int N;
	ifstream in("input.txt");
	in >> N;


	int** adj_matrix = new int*[N + 1];
	for (int i = 1; i <= N; ++i)
		adj_matrix[i] = new int[N + 1];

	for (int i = 1; i <= N; ++i)
		for (int j = 1; j <= N; j++)
			in >> adj_matrix[i][j];

	queue<int> q;
	bool *visited = new bool[N + 1];

	for (int i = 1; i <= N; i++)
		visited[i] = false;

	int *counts = new int[N + 1];

	for (int i = 1; i <= N; i++)
		counts[i] = 0;

	int counter;
	counter = 0;
	for (int i = 1; i <= N; i++)
	{
		if (!visited[i])
		{
			q.push(i);
			counter++;
			counts[i] = counter;

			while (!q.empty())
			{
				int current = q.front();
				for (int i = 1; i <= N; i++)
				{
					if (adj_matrix[current][i] && !counts[i])
					{
						q.push(i);
						counter++;
						counts[i] = counter;
					}
				}
				visited[current] = true;
				q.pop();
			}
		}
	}
	ofstream out("output.txt");

	for (int i = 1; i <= N; i++)
		out << counts[i] << " ";
	out.close();
	return 0;
}