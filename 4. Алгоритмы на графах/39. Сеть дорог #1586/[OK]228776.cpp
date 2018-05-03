#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
using namespace std;
 
struct Edge {
	int x;
	int y;
	long long c;
	long long p;
	long long f = 0;
	Edge *r;
	Edge(int x, int y, long long c, long long p) : x(x), y(y), c(c), p(p) {};
};
 
vector<Edge*> a[111], b[111], res;
int ms[111][111];
long long d[111];
Edge* p[111];
int n, m, sv, tv, x, y, c;
Edge *edge;
 
long long bfs(int s, int t) {
	long long mn = LLONG_MAX / 10;
	queue<int> q;
	Edge* path[111];
	for (int i = 1; i <= n; i++) path[i] = 0;
	q.push(s);
	while (!q.empty()) {
		int v = q.front();
		q.pop();
		if (v == t) {
			res.clear();
			while (v != s) {
				res.push_back(path[v]);
				mn = min(mn, path[v]->c - path[v]->f);
				v = path[v]->x;
			}
			for (Edge* edge : res) {
				edge->f += mn;
				edge->r->f -= mn;
			}
			return mn;
		}
		for (int i = 0; i < a[v].size(); i++) {
			edge = a[v][i];
			if (!path[edge->y] && edge->c - edge->f > 0) {
				path[edge->y] = edge;
				q.push(edge->y);
			}
		}
	}
	return 0;
}
 
long long findFlow(int s, int t) {
	long long flow = 0;
	long long mn = bfs(s, t);
	while (mn) {
		flow += mn;
		mn = bfs(s, t);
	}
	return flow;
}
 
bool findCycle(int s) {
	for (int i = 1; i <= n; i++) {
		d[i] = 0;
		p[i] = 0;
	}
	int f;
	Edge* edge;
	for (int z = 1; z <= n; z++) {
		f = -1;
		for (int i = 1; i <= n; i++)
			for (int j = 0; j < a[i].size(); j++) {
				edge = a[i][j];
				if (d[edge->y] > d[edge->x] + edge->p && edge->c - edge->f > 0) {
					d[edge->y] = max(d[edge->x] + edge->p, LLONG_MIN / 10);
					p[edge->y] = edge;
					f = edge->y;
				}
			}
	}
	res.clear();
	if (f != -1) {
		int y = f;
		for (int i = 1; i <= n; i++) y = p[y]->x;
		for (int cur = y; ; cur = p[cur]->x) {
			res.push_back(p[cur]);
			if (cur == y && res.size() > 1) break;
		}
		if (res.size() > 0) res.pop_back();
		long long addFlow = LLONG_MAX / 10;
		for (int i = 0; i < res.size(); i++)
			addFlow = min(addFlow, res[i]->c - res[i]->f);
		for (int i = 0; i < res.size(); i++) {
			res[i]->f += addFlow;
			res[i]->r->f -= addFlow;
		}
		return true;
	}
	return false;
}
 
long long minCostFlow(int s, int t) {
	while (findCycle(s)) {}
 
	long long minCost = 0;
	for (int i = 1; i <= n; i++)
		for (int j = 0; j < a[i].size(); j++)
			minCost += a[i][j]->p * a[i][j]->f;
	return minCost / 2;
}
 
int main() {
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	int p;
	cin >> n >> m >> sv >> tv;
	for (int i = 0; i < m; i++) {
		cin >> x >> y >> c >> p;
		Edge *e1 = new Edge(x, y, c, p);
		Edge *e2 = new Edge(y, x, 0, -p);
		a[x].push_back(e1);
		a[y].push_back(e2);
		e1->r = e2;
		e2->r = e1;
	}
	long long flow = findFlow(sv, tv);
	long long minCost = minCostFlow(sv, tv);
	for (int i = 1; i <= n; i++) {
		for (int j = 0; j < a[i].size(); j++) {
			a[i][j]->f = 0;
			b[i].push_back(a[i][j]);
		}
		a[i].clear();
	}
	int mm = m;
	m = 0;
	while (!cin.eof()) {
		cin >> x >> y;
		m++;
		ms[x][y] = 1;
	}
	for (int i = 1; i <= n; i++) {
		for (int j = 0; j < b[i].size(); j++)
			if (ms[i][b[i][j]->y] == 1 || ms[b[i][j]->y][i] == 1) {
				edge = b[i][j];
				a[i].push_back(edge);
			}
	}
	long long sugFlow = findFlow(sv, tv);
	long long sugCost = minCostFlow(sv, tv);
 
	if (sugCost > minCost || sugFlow != flow) {
		cout << "No" << '\n' << flow << '\n' << minCost << '\n' << sugFlow << '\n' << sugCost << endl;
	}
	else {
		cout << "Yes" << '\n' << flow << '\n' << minCost << endl;
	}
    return 0;
}
