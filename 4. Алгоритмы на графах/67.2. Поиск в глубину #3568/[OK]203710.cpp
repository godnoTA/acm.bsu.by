#include <fstream>
#include <queue>

using namespace std;


void dfs(int v, bool *visited, int* counts, int &count, int N, int**adj_matrix)
{
	visited[v] = true;
	counts[v] = ++count;
	for (int i = 1; i <= N; i++)
		if (adj_matrix[v][i] && !visited[i])
			dfs(i, visited, counts, count, N, adj_matrix);
}

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
			dfs(i, visited, counts, counter, N, adj_matrix);
	}
	ofstream out("output.txt");

	for (int i = 1; i <= N; i++)
		out << counts[i] << " ";
	out.close();
	return 0;
}