#include <fstream>
#include <set>

using namespace std;

void main() {
	setlocale(LC_ALL, "Rus");
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	long long int a; 
	set <long long int>::iterator it_set;
	long long int sum = 0;
	set <long long int> W;
	while (fin.peek() != EOF) {
		fin >> a;
		W.insert(a);
	}
	for (it_set = W.begin(); it_set != W.end(); it_set++) {
		sum += (*it_set);
	}
	fout << sum;
	fout.close();
	fin.close();
}
