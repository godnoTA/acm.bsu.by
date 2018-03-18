#include <iostream>
#include <bitset>
#include <fstream>

using namespace std;

int main()
{
	unsigned long long value;
	ifstream("input.txt") >> value;
	bitset<sizeof(value) * 8> bits(value);
	ofstream out("output.txt");
	for (size_t i = 0; i < bits.size(); i++) {
		if (bits[i]) {
			out << i << endl;
		}
	}
	out.close();
	return 0;
}