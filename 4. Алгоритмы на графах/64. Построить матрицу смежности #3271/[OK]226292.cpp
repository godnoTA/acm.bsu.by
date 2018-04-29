#include<iostream>
#include<fstream>
#include<algorithm>
using namespace std;

int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	int n, m;
	in >> n >> m;
	int **mas = new int*[n];
	for (int i = 0;i < n;i++) {
		mas[i] = new int[n];
	}
	for (int i = 0;i < n;i++)
		for (int j = 0;j < n;j++)
			mas[i][j] = 0;
	int a, b;
	for (int i = 0;i < m;i++) {
		in >> a >> b;
		cout << a << " " << b << endl;
		mas[a-1][b-1] = 1;
		mas[b-1][a-1] = 1;
	}
	for (int i = 0;i < n;i++) {
		for (int j = 0;j < n;j++) {
			out << mas[i][j] << " ";
		}
		out << endl;
	}
}