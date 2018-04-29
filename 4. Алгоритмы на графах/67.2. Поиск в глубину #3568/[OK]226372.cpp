#include<iostream>
#include<fstream>
#include<algorithm>
#include<map>
#include<queue>
#include<list>
using namespace std;

map<int, list<int>> mas1;
map<int, int> met;
int j = 1;

void dfs(int i) {
	met[i] = j++;
	for (auto j: mas1[i])
		if (!met[j])
			dfs(j);
}

int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n, a;
	in >> n;

	for (int i = 0;i < n;i++) {
		for (int j = 0;j < n;j++) {
			in >> a;
			if (a == 1) {
				mas1[i + 1].push_back(j + 1);
			}
		}
	}
	
	for (int h = 1;h <= n;h++)
		if (!met[h])
			dfs(h);

	for (int i = 1;i < n + 1;i++) {
		out << met[i] << " ";
	}
}