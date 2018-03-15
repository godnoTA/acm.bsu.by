#include <iostream>
#include <ostream>
#include <fstream>
#include <set>
#include <iterator>
using namespace std;
void main() {
	ifstream fin;
	ofstream fout;
	fin.open("input.txt");
	char x[100];
	set <long long> s;
	while (fin) {
		fin.getline(x, 100);
		s.insert(atoi(x));
	}
	fin.close();
	long long a = 0;
	set <long long>::iterator it;
	for (it=s.begin();it!=s.end();it++){
		a += *it;
	}
	fout.open("output.txt");
	fout << a;
	fout.close();
}