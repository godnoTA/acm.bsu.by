#include <iostream>
#include <fstream>

using namespace std;
typedef long long ULL;


int main() {
	ifstream in("input.txt");
	int m, c, n;
	in >> m >> c >> n;
	ULL num;
	int j = 0,place;
	ULL *map = new ULL[m];
	for (int i = 0; i < m; i++)
		map[i] = -1;
	
	for (int i = 0; i < n; i++) {
		in >> num;
		j = 0;
		while (true) {
			place = ((num%m) + c*(j++)) % m;
			if (map[place] == num)
				break;
			if (map[place] == -1) {
				map[place] = num;
				break;
			}
		}
	}
	in.close();

	ofstream out("output.txt");
	for (int i = 0; i < m; i++) {
		out << map[i] << " ";
	}
	out.close();
	delete[] map;

	return 0;
}

