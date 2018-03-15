#pragma comment(linker, "/STACK:30000000")
#include <cstdio>
#include <vector>
#include <utility>
#include <map>
#include <algorithm>
#include <cmath>

using namespace std;

int n, m;
vector<pair<int, int>> ins;
vector<vector<int>> g;
vector<int> ans;
int s, f;

map< pair<int, int>, pair<int, int> > used;


bool Right(long long x1, long long y1, long long x2, long long y2, long long x3, long long y3)
{
	return (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1) < 0;
}


bool Straight(long long x1, long long y1, long long x2, long long y2, long long x3, long long y3)
{
	long long A = y1 - y2;
	long long B = x2 - x1;
	long long C = -A * x1 - B * y1;
	if(A * x3 + B * y3 + C == 0) 
	{
		if(x1 < x2 && x2 < x3)
			return true;
		if(x1 > x2 && x2 > x3)
			return true;
		if(x1 == x2)
		{
			if(y1 < y2 && y2 < y3)
				return true;
			if(y1 > y2 && y2 > y3)
				return true;
		}
		return false;
	}
	return false;
}


bool gotOne = false;
void DFS(int v, int p, int pp)
{
	if(used.find(make_pair(v, p)) != used.end())
		return;
	used[make_pair(v, p)] = make_pair(p, pp);
	if(v == f)
		gotOne = true;
	if(gotOne)
		return;

	int x1 = ins[v].first, y1 = ins[v].second;
	int x0 = ins[p].first, y0 = ins[p].second;
	for(int i = 0; i < g[v].size(); ++i)
	{
		int x2 = ins[g[v][i]].first, y2 = ins[g[v][i]].second;
		if(Straight(x0, y0, x1, y1, x2, y2) || Right(x0, y0, x1, y1, x2, y2))
			DFS(g[v][i], v, p);
	}
}


int main()
{
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);

	scanf("%d %d", &n, &m);
	ins.resize(n + 1);
	g.resize(n + 1);
	for(int i = 0; i < m; ++i)
	{
		int x1, y1, x2, y2, a, b;
		scanf("%d %d %d %d %d %d", &x1, &y1, &x2, &y2, &a, &b);
		ins[a] = make_pair(x1, y1);
		ins[b] = make_pair(x2, y2);
		g[a].push_back(b);
		g[b].push_back(a);
	}
	scanf("%d %d", &s, &f);

	ins[0] = make_pair(ins[s].first, ins[s].second - 1);
	DFS(s, 0, 0);

	map< pair<int, int>, pair<int, int> >::iterator it = used.end();
	for(int i = 1; i <= n; ++i)
	{
		it = used.find(make_pair(f, i));
		if(it != used.end())
			break;
	}

	if(it == used.end())
		printf("No");
	else
	{
		printf("Yes\n");
		while(true)
		{
			ans.push_back(it->first.first);
			if(it->second.first == 0)
				break;
			it = used.find(it->second);
		}
		reverse(ans.begin(), ans.end());
		for(int i = 0; i < ans.size() - 1; ++i)
			printf("%d ", ans[i]);
		printf("%d", ans[ans.size() - 1]);
	}

	return 0;
}