#include<iostream>
#include<fstream>
#include<algorithm>
#include<map>
using namespace std;

int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n, a;
	in >> n;
	map<int, int> mas1;
	for (int i = 0;i < n;i++)
		mas1[i] = 0;

	for (int i = 0;i < n;i++) {
		for (int j = 0;j < n;j++) {
			in >> a;
			if (a == 1) {
				mas1[j] = i+1;
			}
		}
	}
	for (int i = 0;i < n;i++) {
		out << mas1[i] << " ";
	}
}