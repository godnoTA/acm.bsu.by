#include <vector>
#include<algorithm>
#include<set>
#include<iostream>
#include <cstdio>
using namespace std;
const unsigned int INF = UINT_MAX;
int main() {
#ifndef _MSC_VER
freopen("input.txt", "r", stdin);
freopen("output.txt", "w", stdout);
#endif
unsigned int n, m;cin >> n >> m;
vector <vector<pair<unsigned int, unsigned int> > > g(n);
for (unsigned int i = 0; i < m; i++) {
unsigned int a, b, c;scanf("%d%d%d", &a, &b, &c);
g[a - 1].push_back(make_pair(b - 1, c));
g[b - 1].push_back(make_pair(a - 1, c));}
unsigned int s = 0; vector<unsigned int> d(n, INF), p(n);
d[s] = 0;set < pair<unsigned int, unsigned int> > q;
q.insert(make_pair(d[s], s));while (!q.empty()) {
unsigned int v = q.begin()->second;q.erase(q.begin());
for (size_t j = 0; j<g[v].size(); ++j) {
unsigned int to = g[v][j].first,len = g[v][j].second;
if (d[v] + len < d[to]) {
q.erase(make_pair(d[to], to));d[to] = d[v] + len;
p[to] = v;q.insert(make_pair(d[to], to));}}}
cout << d[n - 1];return 0;}