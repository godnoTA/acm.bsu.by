#include <vector>
#include <algorithm>
#include <map>
#include <string>
#include <fstream>
#include <iomanip>
#include <cmath>
#include <stdlib.h>
#include <ctime>
#include <queue>
#include <set>
#include <iostream>
#include <stack>
#include <list>
#include <bitset>
#include <unordered_map>
using namespace std;

typedef long long ll;
typedef long double ld;
typedef vector<ll> vc;
typedef unsigned int ui;


int F(const void* l, const void* r) {
	return (*(int*)l - *(int*)r);
}
const ll INF = 1000000000000000003;
const ll MAXN = 50000;
const ll dd = 1000000007;

pair<int, int> a[1000000];
int b[1000000];
vector<vector<pair<int, int> > > g;
int n, m;
vector<int> q;
bool used[1000000];
bool cmp(pair<int, int> f, pair<int, int> s) {
	return b[f.first] < b[s.first];
}
int main()
{
	ifstream in("input.txt");
	ofstream out("output.txt");
	in >> n >> m;
	g.resize(n);
	for (int i = 0; i < m; i++) {
		int x, y;
		in >> x >> y;
		b[--x]++; b[--y]++;
		g[x].push_back(make_pair(y, i + 1));
	}
	for (int i = 0; i < n; i++) {
		a[i].first = b[i];
		a[i].second = i;
	}
	sort(a, a + n);
	for (int i = 0; i < n; i++) {
		sort(g[i].begin(), g[i].end(), cmp);
	}
	for (int i = 0; i < n; i++) {
		if (!used[i]) {
			for (int j = 0; j < g[i].size(); j++) {
				if (!used[g[i][j].first]) {
					q.push_back(g[i][j].second);
					used[i] = true;
					used[g[i][j].first] = true;
					break;
				}
			}
		}
	}
	out << q.size() << endl;
	for (int i = 0; i < q.size(); i++) {
		out << q[i];
		if (i != q.size() - 1)
			out << endl;
	}
	return 0;
}