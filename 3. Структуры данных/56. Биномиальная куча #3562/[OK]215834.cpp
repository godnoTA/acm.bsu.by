#include <iostream>
#include <fstream>

using namespace std;

int main() 
{
	ofstream out("output.txt");
	ifstream in("input.txt");
	long long n;
	in >> n;
	int exp = 0;
	while (n != 0) {
		if (n % 2 == 1) {
			out << exp << endl;
		}
		n /= 2;
		exp++;
	}
	return 0;
}