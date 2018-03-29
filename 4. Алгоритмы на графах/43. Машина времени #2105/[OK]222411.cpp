#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

struct edge {
	int a, a_time, b, b_time;
};

int main() {

	FILE *pFile = fopen("time.in", "r");
	FILE *output = fopen("time.out", "w");

	int n = 0, startNode = 0, endNode = 0, m = 0;
	fscanf(pFile, "%d", &n);
	fscanf(pFile, "%d", &startNode);
	fscanf(pFile, "%d", &endNode);
	fscanf(pFile, "%d", &m);

	vector<edge> e;

	edge temp;
	int node1 = 0, time1 = 0, node2 = 0, time2 = 0;
	for (int i = 0; i < m; ++i) {
		fscanf(pFile, "%d", &node1);
		fscanf(pFile, "%d", &time1);
		fscanf(pFile, "%d", &node2);
		fscanf(pFile, "%d", &time2);
		temp = { node1, time1, node2, time2 };
		e.push_back(temp);
	}

	vector<long long> d(n + 1, LLONG_MAX);
	d[startNode] = 0;

	bool flag = true;
	while (flag) {
		flag = false;
		for (int j = 0; j < m; ++j) {
			if (d[e[j].a] < LLONG_MAX && e[j].b_time < d[e[j].b] && e[j].a_time >= d[e[j].a]) {
				d[e[j].b] = e[j].b_time;
				flag = true;
			}
		}
	}
	
	
	

	fprintf(output, "%lld", d[endNode]);

	return 0;
	}