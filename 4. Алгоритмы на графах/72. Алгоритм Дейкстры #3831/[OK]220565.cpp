#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <set>
using namespace std;

int main() {

	FILE *pFile = fopen("input.txt", "r");
	FILE *output = fopen("output.txt", "w");

	int n = 0, m = 0;
	fscanf(pFile, "%d", &n);
	fscanf(pFile, "%d", &m);

	vector<vector<pair<int, int>>> g(n + 1, vector<pair<int, int>>());
	set <pair<long long, int>> binaryHeap;
	long long * d = new long long[n + 1];
	bool * met = new bool[n + 1];
	int metCounter = n;

	for (int i = 2; i <= n; ++i) {
		binaryHeap.insert(make_pair(LLONG_MAX, i));
		met[i] = false;
		d[i] = LLONG_MAX;
	}
	d[1] = 0;
	binaryHeap.insert(make_pair(0, 1));
	

	int v1 = 0, v2 = 0, distance = 0;
	for (int i = 0; i < m; ++i) {
		fscanf(pFile, "%d", &v1);
		fscanf(pFile, "%d", &v2);
		fscanf(pFile, "%d", &distance);
		g[v1].push_back(make_pair(v2, distance));
		g[v2].push_back(make_pair(v1, distance));
	}

	while (metCounter != 0) {
		std::set<pair<long long, int>>::iterator it = binaryHeap.begin();
		int minNode = (*it).second;
		binaryHeap.erase(it);
		met[minNode] = true;

		if (g[minNode].size() != 0) {
			for (auto i: g[minNode]) {
				if (!met[i.first] && d[minNode] + i.second <= d[i.first]) {
					binaryHeap.erase(binaryHeap.find(make_pair(d[i.first], i.first)));
					d[i.first] = d[minNode] + i.second;
					
					int temp = d[i.first];
					binaryHeap.insert(make_pair(d[i.first], i.first));
				}
			}
		}
		--metCounter;
	}
	
	fprintf(output, "%lld", d[n]);

	return 0;
}