#include<iostream>
#include<fstream>
#include<stack>
using namespace std;

int minPower(long long n, long long*pow) {
	int k = 0;
	*pow = 1;
	while (*pow <= n) {
		*pow *= 2;
		k++;
	}
	if (*pow > 1) {
		*pow /= 2;
	}
	return k - 1;
}
int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");

	long long*pow = new long long;
	long long n;
	in >> n;
	stack<int> height;
	while (n != 0) {
		height.push(minPower(n,pow));
		n -= *pow;
	}
	while (!height.empty()) {
		out << height.top() << endl;
		height.pop();
	}
	return 0;
}
