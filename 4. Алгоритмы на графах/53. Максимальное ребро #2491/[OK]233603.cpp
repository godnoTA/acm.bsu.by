// 53cpp.cpp: определяет точку входа для консольного приложения.
//
#pragma comment(linker, "/STACK:67108864")
//#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

int max_edge;
int n;
int logn;
void initialize(int, int, int, int);
vector<int> path[200001];
vector<int> curr_max[200001];
vector<int> adj_list[200001];
vector<int> fines[200001];
int levels[200001];

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	fin >> n;
	logn = 1;
	int pow = 1;
	while (pow <= n) {
		pow *= 2;
		logn++;
	}
	//cout << logn;
	int s;
	int f;
	int w;
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < logn + 1; j++) {
			path[i].push_back(0);
			curr_max[i].push_back(-INT32_MAX);
		}
	}
	
	for (int i = 0; i < n - 1; i++) {
		fin >> s;
		fin >> f;
		s--;
		f--;
		fin >> w;
		adj_list[s].push_back(f);
		fines[s].push_back(w);
		adj_list[f].push_back(s);
		fines[f].push_back(w);
	}
	initialize(0, 0, 0, -INT32_MAX);
	//cout << "ho";
	int m;
	fin >> m;
	for (int i = 0; i < m; i++) {
		fin >> s;
		fin >> f;
		if (s == f)
			fout << 0 << endl;
		else {
			s--;
			f--;
			max_edge = INT32_MIN;
			if (levels[f] > levels[s]) {
				int pow = 1 << logn;
				int ind = logn;
				int diff = levels[f] - levels[s];
				for (; ind >= 0;) {
					if (pow <= diff) {
						max_edge = max(max_edge, curr_max[f][ind]);
						f = path[f][ind];
						diff = levels[f] - levels[s];
					}
					pow = pow >> 1;
					ind--;
				}
				if (f != s) {
					int j = logn;
					while (j >= 0) {
						if (path[s][j] != path[f][j]) {
							max_edge = max(curr_max[s][j], max_edge);
							max_edge = max(max_edge, curr_max[f][j]);
							s = path[s][j];
							f = path[f][j];
						}
						j--;
					}
					if (f != s) {
						max_edge = max(curr_max[s][0], max_edge);
						max_edge = max(curr_max[f][0], max_edge);
					}
				}
			}
			else {
				int pow = 1 << logn;
				int ind = logn;
				int diff = levels[s] - levels[f];
				for (; ind >= 0;) {
					if (pow <= diff) {
						max_edge = max(max_edge, curr_max[s][ind]);
						s = path[s][ind];
						diff = levels[s] - levels[f];
					}
					pow = pow >> 1;
					ind--;
				}
				if (f != s) {
					int j = logn;
					while (j >= 0) {
						if (path[s][j] != path[f][j]) {
							max_edge = max(curr_max[s][j], max_edge);
							max_edge = max(max_edge, curr_max[f][j]);
							s = path[s][j];
							f = path[f][j];
						}
						j--;
					}
					if (f != s) {
						max_edge = max(curr_max[s][0], max_edge);
						max_edge = max(curr_max[f][0], max_edge);
					}
				}
			}
			fout << max_edge << endl;
		}
	}
	
	return 0;
}

void initialize(int curr, int prev, int lev, int fine) {
	path[curr][0] = prev;
	curr_max[curr][0] = fine;
	levels[curr] = lev;
	for (int i = 1; i <= logn; i++) {
		int prev_step = path[curr][i - 1];
		path[curr][i] = path[prev_step][i - 1];
	}
	for (int i = 1; i <= logn; i++) {
		int prev_step = path[curr][i - 1];
		curr_max[curr][i] = max(curr_max[curr][i - 1], curr_max[prev_step][i - 1]);
	}
	for (int i = 0; i < adj_list[curr].size(); i++) {
		if (adj_list[curr][i] != prev) {
			initialize(adj_list[curr][i], curr, lev + 1, fines[curr][i]);
		}
	}
}