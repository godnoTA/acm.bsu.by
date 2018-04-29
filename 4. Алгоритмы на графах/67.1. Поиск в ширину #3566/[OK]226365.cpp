#include<iostream>
#include<fstream>
#include<algorithm>
#include<map>
#include<queue>
#include<list>
using namespace std;

int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n, a;
	in >> n;
	map<int, list<int>> mas1;
	map<int, int> met;

	for (int i = 0;i < n;i++) {
		for (int j = 0;j < n;j++) {
			in >> a;
			if (a == 1) {
				mas1[i + 1].push_back(j + 1);
			}
		}
	}
	queue<int>queue;
	int l = 1;
	int num;
	for (int i = 1;i < n+1;i++) {
		if (!met[i]) {
			queue.push(i);
			met[i] = l++;
			while (!queue.empty()) {
				num = queue.front();
				for (auto i : mas1[num]) {
					if (!met[i]) {
						queue.push(i);
						met[i] = l++;
					}
				}
				queue.pop();
			}
		}
	} 
	for (int i = 1;i < n + 1;i++) {
		out << met[i] << " ";
	}
}