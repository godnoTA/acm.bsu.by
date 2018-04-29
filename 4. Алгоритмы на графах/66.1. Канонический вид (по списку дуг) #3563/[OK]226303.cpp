#include<iostream>
#include<fstream>
#include<algorithm>
#include<map>
using namespace std;

int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n;
	in >> n;
	int a, b;
	map<int, int> mas;
	for (int i = 0;i < n;i++) {
		mas[i] = 0;
	}
	for (int i = 0;i < n-1;i++) {
		in >> a >> b;
		mas[b-1] = a;
	}
	for (int i = 0;i < n;i++) {
		out << mas[i] << " ";
	}
}