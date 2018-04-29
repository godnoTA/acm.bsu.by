#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

int n, m, k;
int ax, ay, bx, by;
int **dist;
int **a;
int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

int main()
{
	ifstream f("in.txt");
	ofstream of("out.txt");
	f >> n >> m;
	a = new int*[n];
	dist = new int*[n];
	for (int i = 0; i < n; i++) {
		a[i] = new int[m];
		dist[i] = new int[m];
	}
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			f >> a[i][j];
	f >> k;
	f >> ax >> ay >> bx >> by;
	queue<pair<int, int>> q;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			dist[i][j] = 1000000000;
	dist[ax - 1][ay - 1] = 0;
	q.push(make_pair(ax - 1, ay - 1));
	while (!q.empty())
	{
		int vx = q.front().first;
		int vy = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++)
		{
			int x = vx + dx[i];
			int y = vy + dy[i];
			if (x >= 0 && x < n && y >= 0 && y < m && abs(a[vx][vy] - a[x][y]) <= k && dist[x][y] > dist[vx][vy] + 1)
			{
				dist[x][y] = dist[vx][vy] + 1;
				q.push(make_pair(x, y));
			}
		}
	}
	if (dist[bx - 1][by - 1] < 1000000000)
		of << "Yes" << endl << dist[bx - 1][by - 1] << endl;
	else
		of << "No" << endl;
}