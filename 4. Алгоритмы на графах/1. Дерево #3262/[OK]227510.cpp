#include<iostream>
#include<fstream>
#include<algorithm>
#include<map>
#include<queue>
#include<list>
using namespace std;

map<int, int> met;
int k = 0;
int mas[101][101];
bool error = false;

void dfs(int pres, int prev, int n) {
	if (error) return;
	met[pres] = 1;
	k++;
	for (int i = 0; i < n; i++) {
		if (mas[pres][i] && i != prev) {
			if (met[i]) error = true;
			else dfs(i, pres, n);
		}
	}
}

int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n;
	in >> n;
	for (int i = 0;i < n;i++)
		for (int j = 0;j < n;j++)
			in >> mas[i][j];
	dfs(0, -1, n);
	if (k != n || error) out << "No";
	else out << "Yes";
}