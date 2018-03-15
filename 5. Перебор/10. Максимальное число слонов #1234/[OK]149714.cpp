#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <stack>
#include <fstream>
#include <string>
#include <algorithm>
#include <sstream>
#include <cmath>
#include <queue>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
using namespace std;


ifstream in("input.txt");
ofstream out("output.txt");
/**/

int chessf[12][12];
int a[12][12];
bool used[12][12];
int n, m, p;
int dx[4] = { 1, 1, -1, -1 };
int dy[4] = { 1, -1, -1, 1 };
bool isInRect(int x, int y)
{
	return (x >= 0 && y >= 0 && x < n && y < m);
}

vector<vector<pair<int, int> > > ans;

void addElef(int i, int j)
{
	for (int k = 0; k < 4; ++k)
	{
		int curx = i;
		int cury = j;
		while (isInRect(curx + dx[k], cury + dy[k]))
		{
			curx += dx[k];
			cury += dy[k];
			if (!used[curx][cury])
				++a[curx][cury];
			else
				break;
		}
	}
	a[i][j] = 1;
}
void removeElef(int i, int j)
{
	for (int k = 0; k < 4; ++k)
	{
		int curx = i;
		int cury = j;
		while (isInRect(curx + dx[k], cury + dy[k]))
		{
			curx += dx[k];
			cury += dy[k];
			if (!used[curx][cury])
				--a[curx][cury];
			else
				break;
		}
	}
	a[i][j] = 0;
}

void dfs(int xx, int yy, vector<pair<int, int> > & curElef)
{
	int x = xx;
	int y = yy;
	for (int x = xx; x < n; ++x)
	{
		for (int y = (x == xx ? yy + 1 : 0); y < m; ++y)
		{
			if (!used[x][y] && !a[x][y])
			{
				curElef.push_back(make_pair(x, y));
				addElef(x, y);
				dfs(x, y, curElef);
				if (curElef.size() > ans.back().size())
				{
					ans.clear();
					ans.push_back(curElef);
				}
				else
					if (curElef.size() == ans.back().size())
						ans.push_back(curElef);
				removeElef(x, y);
				curElef.pop_back();
			}
		}
	}
}

int main()
{
	int x, y;
	in >> n >> m;

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j)
			{
				in >> chessf[i][j];
				if (chessf[i][j])
					used[i][j] = true;
			}
		}
	ans.push_back(vector<pair<int, int> >(0));
	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < m; ++j)
		{
			if (!used[i][j])
			{
				vector<pair<int, int> > curElef(1, make_pair(i, j));
				addElef(i, j);
				dfs(i, j, curElef);
				removeElef(i, j);
			}
		}
	}

	out << ans.size() << "\n";
	out << ans[0].size() << "\n";
	
	for (int i = 0; i < ans.size(); ++i)
	{
		for (int j = 0; j < ans[i].size(); ++j)
		{
			out << ans[i][j].first << " " << ans[i][j].second;
			if (j + 1 != ans[i].size())
				out << " ";
		}
		if (i + 1 != ans.size())
			out << endl;
	}

	return 0;
}