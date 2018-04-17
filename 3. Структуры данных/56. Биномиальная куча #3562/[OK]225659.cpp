#include <algorithm>
#include <vector>
#include <iostream>
#include <fstream> 

using namespace std;


ofstream out("output.txt");
long long n;

int main() {
	ifstream in("input.txt");
	in >> n;
	long long count = 0;
	while (n!=0)
	{
		if (n % 2 == 1) {
			out << count << " ";
			n--;
		}
		else
		{
			n = n / 2;
			count++;
		}
	}
	return 0;
}