#include <iostream>
#include <fstream>

using namespace std;
typedef long long ULL;


int main() {
	ifstream in("input.txt");
	int m;
	in >> m;
	ULL num;
	int j = 0, place;
	ULL *map = new ULL[m+1];
	in >> num;
	map[1] = num;
	ofstream out("output.txt");

	for (int i = 2; i < m+1; i++) {
		in >> num;
		j = i;
		if (i % 2 != 0) {
			j -= 1;
		}
		j /= 2;
		if (map[j] <= num)
			map[i] = num;
		else{
			out << "No";
			out.close();
			in.close();
			delete[] map;
			return 0;
		}
	}
	out << "Yes";
	out.close();
	in.close();
	delete[] map;
	return 0;
}
