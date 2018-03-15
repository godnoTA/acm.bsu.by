#include <fstream>


int main() {
	std::ifstream fin("input.txt");
	int m, c, n;
	fin >> m >> c >> n;

	int *keys = new int[n];
	for (int i = 0; i < n; i++)
		fin >> keys[i];
	fin.close();

	int *hash_table = new int[m];
	for (int i = 0; i < m; i++)
		hash_table[i] = -1;

	int at = 0, index = 0;
	bool ok;
	for (int i = 0; i < n; i++) {
		ok = false;
		at = 0;
		while (!ok) {
			index = ((keys[i] % m) + c*at) % m;
			if (hash_table[index] == keys[i])
				break;
			if (hash_table[index] != -1)
				at++;
			else {
				hash_table[index] = keys[i];
				ok = true;
			}
		}
	}

	std::ofstream fout("output.txt");
	for (int i = 0; i < m; i++)
		fout << hash_table[i] << " ";
	fout.close();
	return 0;
}