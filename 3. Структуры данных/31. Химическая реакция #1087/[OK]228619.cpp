#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

bool reacts(int first, int second, int** table) {
	int a = table[first][second];
	return(table[first][second] != 0);
}

int react(int first, int second, int** table) {
	return(table[first][second]);
}

void main() {
	ifstream in("in.txt");
	ofstream out("out.txt");

	int n;
	int m;
	int buff;

	in >> n;
	in >> m;

	vector<int> que;

	int **tableOfSubs = new int*[n];
	for (int i = 0; i < n; i++) {
		tableOfSubs[i] = new int[n];
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			in >> tableOfSubs[i][j];
		}
	}

	for (int i = 0; i < m; i++) {
		in >> buff;
		que.insert(que.end(),buff);
	}

	for (int i = 0; i < m-1; i++) {
		if (que.size() == 1) {
			break;
		}
		if (reacts(que[i] - 1, que[i + 1] - 1, tableOfSubs)) {
			que[i + 1] = react(que[i] - 1, que[i + 1] - 1, tableOfSubs);
			que.erase(que.begin() + i);
			int j = i;
			while (j > 0 && reacts(que[j] - 1, que[j - 1] - 1, tableOfSubs)) {
				que[j] = react(que[j] - 1, que[j - 1] - 1, tableOfSubs);
				que.erase(que.begin() + j-1);
				j--;
				i--;
				m--;
			}
			i--;
			m--;
		}
	}

	int i = 0;
	int j = que.size() - 1;
	while (!que.empty()) {
		if (i != 0) {
			out << " ";
		}
		out<<que[j];
		que.erase(que.begin() + j);
		j -= 1;
		i++;
	}

	in.close();
	out.close();

	delete[] tableOfSubs;
}