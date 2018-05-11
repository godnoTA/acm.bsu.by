#include <iostream>
#include <fstream>
#include <cmath>
#include <vector>
#include <queue>
#include <stack>

using namespace std;
int n, m;
int square;
int **castle;
bool *used;
int room;
vector<vector<int>> list;

void dfs(int from) {
	stack<int> st;
	st.push(from);
	square++;
	while (!st.empty()) {
		int v = st.top();
		used[v] = true;
		castle[v / m][v%m] = room;
		bool flag = false;
		for (int i = 0; i < list[v].size(); ++i) {
			if (!used[list[v][i]]) {
				st.push(list[v][i]);
				square++;
				flag = true;
				break;
			}
		}
		if (!flag) {
			st.pop();
		}
	}
}

vector <int> neig(int v) {
	vector <int> ans;
	int k = v / m;
	int l = v % m;
	if (l > 0) {
		ans.push_back(k*m + l - 1);
	}
	if (k > 0) {
		ans.push_back((k - 1)*m + l);
	}
	return ans;
}

int main() {
	ifstream fin("in");
	fin >> n >> m;
	list.resize(n*m);
	used = new bool[n*m];
	castle = new int*[n];
	for (int i = 0; i < n; ++i) {
		castle[i] = new int[m];
	}

	cout << n << " " << m << endl;
	for (int i = 0; i < n*m; ++i) {
		int num;
		fin >> num;
		/*cout << num << "\t";*/
		used[i] = false;
		for (int j = 0; j < 4; j++) {
			int tmp = num % 2;
			if (tmp == 0) {
				switch (j) {
				case 0:
					list[i].push_back(i - 1);
					break;
				case 1:
					list[i].push_back(i - m);
					break;
				case 2:
					list[i].push_back(i + 1);
					break;
				case 3:
					list[i].push_back(i + m);
					break;
				}
			}
			num = num / 2;
		}
	}
	vector<int> rooms;
	int max_room = 0;
	room = 0;
	square = 0;
	int j = 0;
	int k = 0;
	dfs(0);
	while (true) {
		if (square > max_room)
			max_room = square;
		rooms.push_back(square);
		k += square;
		if (k < n * m) {
			for (j; j < n*m; j++) {
				if (!used[j]) {
					room++;
					square = 0;
					dfs(j);
					break;
				}
			}
		}
		else {
			break;
		}
	}
	/*for (int i = 0; i < n; ++i) {
		for (int j = 0; j < m; j++) {
			cout << castle[i][j] << " ";
		}
		cout << endl;
	}*/
	int maxi = max_room;
	for (int i = 0; i < n*m; ++i) {
		vector <int> neigb = neig(i);
		for (int j : neigb) {
			if (castle[j / m][j%m] != castle[i / m][i%m]) {
				maxi = std::max(maxi, rooms[castle[j / m][j%m]] + rooms[castle[i / m][i%m]]);
			}
		}
	}
	ofstream fout("out");
	fout << room + 1 << endl << max_room << endl;
	fout << maxi << endl;
	system("pause");
}