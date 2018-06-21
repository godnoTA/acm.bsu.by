#include <iostream>
#include <fstream>
#include <queue>

using namespace std;

int n, m, k=0;
int **A;
int dx[4] = { 1, 1, -1, -1 };
int dy[4] = { 1, -1, 1, -1 };

void bfs(int ix, int iy) {
	queue<pair<int, int>> q;
	int x, y;
	A[ix][iy] = -1;
	q.push(make_pair(ix,iy));
	while (!q.empty())
	{
		int vx = q.front().first;
		int vy = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++)
		{
			x = vx + dx[i];
			y = vy + dy[i];
			if(A[x][y]==0)
			{
				A[x][y] = -1;
				q.push(make_pair(x, y));
			}
		}
	}
}

int main()
{
	ifstream f("in.txt");
	f >> n >> m;
	A = new int*[n+2];
	for (int i = 0; i < n+2; i++) {
		A[i] = new int[m+2];
	}
	for (int i = 0; i < n + 2; i++) {
		for (int j = 0; j < m + 2; j++)
		{
			if (j == 0 || i == 0 || i == n + 1 || j == m + 1)
				A[i][j] = -1;
			else
			f >> A[i][j];
			//cout << A[i][j]<<"   ";
		}
		//cout << endl;
	}
	f.close();
	for (int i = 1; i < n+1; i++)
		for (int j = 1; j < m+1; j++)
			if (A[i][j] == 0)
			{
				bfs(i, j);
				k++;
			}	
	ofstream of("out.txt");
	of << k;
	of.close();
	return 0;
}