
#include <iostream>
#include <map>
#include <fstream>
#include <vector>
#include <set>

using namespace std;

int main()
{
	ofstream out("output.txt");
	ifstream in("input.txt");
	
	set<pair<long long, int>> queue = set<pair<long long, int>>();
	int n, m;
	in >> n;
	in >> m;
	vector<vector<pair<int, long long>>> vectors(n);
	vector<long long> length(n);
	for (int k = 0; k < n; k++) {
		length[k] = LLONG_MAX;
	}
	int i, j;
	long long a;
	for (int k = 0; k < m; k++) {
		in >> i >> j >> a;
		vectors[i-1].push_back(make_pair(j - 1, a));
		vectors[j-1].push_back(make_pair(i - 1, a));
	}
	queue.insert(make_pair(0,0));
	length[0] = 0;
	int x=0;
	while (x!=n-1) {
		x = queue.begin()->second;
		queue.erase(queue.begin());
		for (int i = 0; i < vectors[x].size(); i++) {
			if (length[vectors[x][i].first] > length[x] + vectors[x][i].second) {
				queue.erase(make_pair(length[vectors[x][i].first], vectors[x][i].first));
				queue.insert(make_pair(length[x] + vectors[x][i].second, vectors[x][i].first));
				length[vectors[x][i].first] = length[x] + vectors[x][i].second;
			}
		}
	}

	out << length[n - 1];
	return 0;
}