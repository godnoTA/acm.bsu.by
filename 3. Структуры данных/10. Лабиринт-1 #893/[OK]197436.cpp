#include <iostream>
#include <string>
#include <fstream>
#include <stack>
#include <vector>

enum type{TOP, LEFT, BOT, RIGHT};
using namespace std;
bool**visited;
int N, M;
char**matrix;

struct ELEM {
	pair<int, int> pr;
	type hand;
};

stack<ELEM> st;

void dfs()
{
	while (!st.empty()) {
		int i = st.top().pr.first;
		int j = st.top().pr.second;
		type side = st.top().hand;

		if (i == N - 1) 
			return;

		if (side == TOP) {
			if (j > 0 && matrix[i][j - 1] == '0' && visited[i][j - 1] == false) {
				visited[i][j - 1] = true;
				ELEM temp;
				temp.pr = make_pair(i, j - 1);
				temp.hand = RIGHT;
				st.push(temp);
				continue;
			}
			if (i < N - 1 && matrix[i + 1][j] == '0' && visited[i + 1][j] == false) {
				visited[i + 1][j] = true;
				ELEM temp;
				temp.pr = make_pair(i + 1, j);
				temp.hand = TOP;
				st.push(temp);
				continue;
			}
			if (j < M - 1 && matrix[i][j + 1] == '0' && visited[i][j + 1] == false) {
				visited[i][j + 1] = true;
				ELEM temp;
				temp.pr = make_pair(i, j + 1);
				temp.hand = LEFT;
				st.push(temp);
				continue;
			}
		}
		if (side == LEFT) {
			if (i < N - 1 && matrix[i + 1][j] == '0' && visited[i + 1][j] == false) {
				visited[i + 1][j] = true;
				ELEM temp;
				temp.pr = make_pair(i + 1, j);
				temp.hand = TOP;
				st.push(temp);
				continue;
			}
			if (j < M - 1 && matrix[i][j + 1] == '0' && visited[i][j + 1] == false) {
				visited[i][j + 1] = true;
				ELEM temp;
				temp.pr = make_pair(i, j + 1);
				temp.hand = LEFT;
				st.push(temp);
				continue;
			}
			if (i > 0 && matrix[i - 1][j] == '0' && visited[i - 1][j] == false) {
				visited[i - 1][j] = true;
				ELEM temp;
				temp.pr = make_pair(i - 1, j);
				temp.hand = BOT;
				st.push(temp);
				continue;
			}
		}
		if (side == RIGHT) {
			if (i > 0 && matrix[i - 1][j] == '0' && visited[i - 1][j] == false) {
				visited[i - 1][j] = true;
				ELEM temp;
				temp.pr = make_pair(i - 1, j);
				temp.hand = BOT;
				st.push(temp);
				continue;
			}
			if (j > 0 && matrix[i][j - 1] == '0' && visited[i][j - 1] == false) {
				visited[i][j - 1] = true;
				ELEM temp;
				temp.pr = make_pair(i, j - 1);
				temp.hand = RIGHT;
				st.push(temp);
				continue;
			}
			if (i < N - 1 && matrix[i + 1][j] == '0' && visited[i + 1][j] == false) {
				visited[i + 1][j] = true;
				ELEM temp;
				temp.pr = make_pair(i + 1, j);
				temp.hand = TOP;
				st.push(temp);
				continue;
			}
		}
		if (side == BOT) {
			if (j < M - 1 && matrix[i][j + 1] == '0' && visited[i][j + 1] == false) {
				visited[i][j + 1] = true;
				ELEM temp;
				temp.pr = make_pair(i, j + 1);
				temp.hand = LEFT;
				st.push(temp);
				continue;
			}
			if (i > 0 && matrix[i - 1][j] == '0' && visited[i - 1][j] == false) {
				visited[i - 1][j] = true;
				ELEM temp;
				temp.pr = make_pair(i - 1, j);
				temp.hand = BOT;
				st.push(temp);
				continue;
			}

			if (j > 0 && matrix[i][j - 1] == '0' && visited[i][j - 1] == false) {
				visited[i][j - 1] = true;
				ELEM temp;
				temp.pr = make_pair(i, j - 1);
				temp.hand = RIGHT;
				st.push(temp);
				continue;
			}
		}
		
		st.pop();
	}
}

int main() {
	
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	fin >> N >> M;
	matrix = new char*[N];
	for (int i = 0; i < N; i++)
		matrix[i] = new char[M];
	visited = new bool*[N];
	for (int i = 0; i < N; i++)
		visited[i] = new bool[M];
	char c;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			fin >> c;
			matrix[i][j] = c;
		}
	}
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			visited[i][j] = false;
	
	int count_enters = 0;
	int count_exits = 0;

	for (int j = 0; j < M; j++)
		if (matrix[0][j] == '0')
			count_enters++;
	for (int j = 0; j < M; j++)
		if (matrix[N-1][j] == '0')
			count_exits++;

	for (int j = 0; j < M; j++)
		if (matrix[0][j] == '0')
			if (matrix[1][j] == '0' && visited[1][j] == false) {
				visited[0][j] = true;
				visited[1][j] = true;
				ELEM temp;
				temp.pr = make_pair(1, j);
				temp.hand = TOP;
				st.push(temp);
				dfs();
				while (!st.empty())
					st.pop();
			}

	int real_exits = 0;
	for (int i = 0; i < M; i++)
		if (visited[N - 1][i] == true)
			real_exits++;
	
	if (real_exits == count_enters && real_exits <= count_exits)
		fout << "Possible" << endl;
	else
		fout << "Impossible" << endl;
	return 0;
}