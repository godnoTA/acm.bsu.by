#include <fstream>

using namespace std;

int n;
int graph[1000][1000];
bool visited[1000];

void input() {
	ifstream fin("input.in");
	fin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++)
			fin >> graph[i][j];
	}
	for (int i = 0; i < n; i++)
		visited[i] = false;
}

void DFS(int st)
{
	int r;
	visited[st] = true;
	for (r = 0; r <= n; r++)
		if ((graph[st][r] != 0) && (!visited[r]))
			DFS(r);
}

int main() {
	input();
	ofstream fout("output.out");
	DFS(0);
	for (int i = 0; i < n; i++) {
		if (visited[i] == false) {
			fout << "NO";
			return 0;
		}
	}
	fout << "YES";
	system("pause");
	return 0;
}