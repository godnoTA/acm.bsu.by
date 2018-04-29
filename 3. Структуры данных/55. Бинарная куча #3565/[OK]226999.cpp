#include<iostream>
#include<fstream>
#include<stack>
using namespace std;


int main() {
	ifstream in("input.txt");
	ofstream out("output.txt");

	
	int n;
	in >> n;
	int*numbers = new int[n];
	for (int i = 0; i < n; ++i) {
		in >> numbers[i];
	}
	bool res = true;
	for (int i = 1; 2 * i <= n; ++i) {
		if (numbers[2 * i - 1] < numbers[i - 1] || 2*i+1 <=n && numbers[2*i] < numbers[i-1]) {
			res = false;
			break;
		}
	}

	if (res) {
		out << "Yes";
	}
	else {
		out << "No";
	}
	return 0;
}
