#include <fstream>


int main() {
	std::ifstream fin("input.txt");
	int n;
	fin >> n;
	int* mas = new int[n];
	for (int i = 0; i < n; i++)
		fin >> mas[i];
	fin.close();
	bool flag = true;
	std::ofstream fout("output.txt");
	for (int i = 0; i <= n / 2; i++) {
		if (2 * i + 1 > n-1)
			break;
		if (2 * i + 2 > n-1) {
			if (mas[2 * i + 1] >= mas[i])
				continue;
			else {
				flag = false;
				break;
			}
		}
		if (mas[2 * i + 1] >= mas[i] && mas[2 * i + 2] >= mas[i]) {
			continue;
		}
		else {
			flag = false;
			break;
		}
	}
	if (flag)
		fout << "Yes";
	else
		fout << "No";
	delete[] mas;
	return 0;
}