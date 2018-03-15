#include <fstream>
#include <algorithm>

std::ifstream fin("input.txt");
std::ofstream fout("output.txt");

int main() {
	int N, M, D;
	fin >> N >> M >> D;

	int *l = new int[N+1];
	for (int i = 1; i < N+1; i++) {
		fin >> l[i];
	}
	fin.close();

	int len = M*D;
	int *rec = new int[(len+1)*(N+1)];;
	int pos;

	for (int i = 0; i < N + 1; i++)
		rec[i*(len+1)] = 0;

	for (int j = 0; j < len + 1; j++)
		rec[j] = 0;

	for (int i = 1; i < N + 1; i++) {
		for (int j = 1; j < len + 1; j++) {
			pos = j % D;
			if (pos == 0)
				pos = D;
			if (l[i] <= pos)
				rec[i*(len + 1) + j] = std::max(rec[(i - 1)*(len + 1) + j - l[i]] + 1, rec[(i - 1)*(len + 1) + j]);
			else
				rec[i*(len + 1) + j] = std::max(rec[i*(len + 1) + j - 1], rec[(i - 1)*(len + 1) + j]);
		}
	}

	fout << rec[N*(len + 1) + len];
	return 0;
}


