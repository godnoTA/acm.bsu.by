#include<fstream>
#include<vector>
#include<set>
#include <iostream>
#include<ctime>
#include<algorithm>
const int INF = 1000000000;

using namespace std;
int main(){
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int n, k, first, second, cost, a,b;
	cin >> n >> k >> a >> b;
	vector<vector<int>> gi(n, vector<int>(n));
	for (int i = 0; i < k; i++){
		cin >> first >> second >> cost;
		gi[first - 1][second-1]=cost;
		gi[second - 1][first-1] = cost;
	}
	vector<int> wait(n);
	for (int i = 0; i < n; i++)
	for (int j = 0; j < n; j++)
	if (gi[i][j] != 0) wait[j]++;
	
	for (int i = 0; i < n; i++)
	for (int j = 0; j < n; j++)
	if (gi[i][j] != 0) gi[i][j] += wait[j];

	
	vector<vector<pair<int, int>>>g(n);
	for (int i = 0; i < n; i++)
	for (int j = 0; j < n; j++)
		if (gi[i][j] != 0) g[i].push_back(make_pair(j, gi[i][j]));

	int s = a-1;
	vector<int> d(n, INF), p(n);
	d[s] = 0;
	vector<char> u(n);
	for (int i = 0; i<n; ++i) {
		int v = -1;
		for (int j = 0; j<n; ++j)
		if (!u[j] && (v == -1 || d[j] < d[v]))
			v = j;
		if (d[v] == INF)
			break;
		u[v] = true;

		for (size_t j = 0; j<g[v].size(); ++j) {
			int to = g[v][j].first,
				len = g[v][j].second;
			if (d[v] + len < d[to]){
				d[to] = d[v] + len;
				p[to] = v;
			}
		}
	}


	cout << d[b - 1]-wait[b-1] << endl;

	vector<int> path;
	for (int v = b-1; v != s; v = p[v])
		path.push_back(v);
	path.push_back(s);
	reverse(path.begin(), path.end());
	for (int i : path) cout << i +1<< " ";
	return 0;
}