#include<iostream>
#include<fstream>
#include<algorithm>
#include<list>
#include<map>
using namespace std;

int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n, m;
	in >> n >> m;

	int a, b;
	map<int, list<int>> mas;
	for (int i = 0;i < m;i++) {
		in >> a >> b;
		mas[a].push_back(b);
		mas[b].push_back(a);
	}
	for (int i = 1;i < n+1;i++) {
		out << mas[i].size() << " ";
		for (auto j : mas[i]) {
			out << j << " ";
		}
		out << endl;
	}
}