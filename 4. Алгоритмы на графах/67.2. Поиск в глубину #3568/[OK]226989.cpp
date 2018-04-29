#include<iostream>
#include<fstream>
#include<stack>
using namespace std;

void dfs(bool**adjacency, int*marks, int v) {
	int num_of_visited = 0;
	int cur_mark = 1;
	stack<int> visited;

	while (num_of_visited != v) {
		int first_not_visited;
		for (int i = 0; i < v; ++i) {
			if (marks[i] == 0) {
				first_not_visited = i + 1;
				break;
			}
		}
		visited.push(first_not_visited);
		marks[first_not_visited - 1] = cur_mark;
		cur_mark++;
		num_of_visited++;
		while (!visited.empty()) {
			int cur_v = visited.top();
			bool added_someone = false;
			for (int i = 0; i < v; ++i) {
				if (adjacency[cur_v - 1][i] && marks[i] == 0) {
					visited.push(i + 1);
					marks[i] = cur_mark;
					cur_mark++;
					num_of_visited++;
					added_someone = true;
					break;
				}
			}
			if (!added_someone) {
				visited.pop();
			}
		}
	}

}

int main() {

	ifstream in("input.txt");
	ofstream out("output.txt");
	int v;
	in >> v;
	bool**adjacency = new bool*[v];
	for (int i = 0; i < v; ++i) {
		adjacency[i] = new bool[v];
		for (int j = 0; j < v; ++j) {
			in >> adjacency[i][j];
		}
	}

	int*marks = new int[v];
	for (int i = 0; i < v; ++i) {
		marks[i] = 0;
	}

	dfs(adjacency, marks, v);
	for (int i = 0; i < v; ++i) {
		out << marks[i] << ' ';
	}
	return 0;
}