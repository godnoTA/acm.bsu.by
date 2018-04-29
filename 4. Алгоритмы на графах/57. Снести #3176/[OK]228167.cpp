#include<iostream>
#include<fstream>
#include<stack>
#include<algorithm>
#include<vector>
using namespace std;


long long add_null = 0;
long long add_null_squares = 0;
long long add_one = 0;
int isolated = 0;
int last_visited_vertex = -1;


void dfs(vector<vector<int>> adjacency, int*marks, int*earliest_marks, int v, int* num_of_bad_sons, int*last_marked) {
	int num_of_visited = 0;
	int cur_mark = 1;
	stack<int> visited;

	long long add_null_cur_component = 0;
	int sons_of_root = 0;
	bool not_first_dfs = false;

	while (num_of_visited != v) {
		if (sons_of_root == 2) {
			add_one++;
		}
		if (sons_of_root == 1) {
			add_null_cur_component += sons_of_root;
		}
		if (sons_of_root == 0 && not_first_dfs) {
			isolated++;
		}

		add_null += add_null_cur_component;
		add_null_squares += add_null_cur_component*add_null_cur_component;
		add_null_cur_component = 0;
		sons_of_root = 0;

		not_first_dfs = true;
		int first_not_visited;
		for (int i = last_visited_vertex+1; i < v; ++i) {
			if (marks[i] == 0) {
				first_not_visited = i + 1;
				last_visited_vertex = i;
				break;
			}
		}
		visited.push(first_not_visited);

		marks[first_not_visited - 1] = cur_mark;
		earliest_marks[first_not_visited - 1] = marks[first_not_visited - 1];
		cur_mark++;
		num_of_visited++;
		while (!visited.empty()) {
			int cur_v = visited.top();
			bool added_someone = false;
			for (int i = last_marked[cur_v - 1] + 1; i < adjacency[cur_v - 1].size(); ++i) {

				if (marks[adjacency[cur_v - 1][i] - 1] == 0) {
					visited.push(adjacency[cur_v - 1][i]);
					marks[adjacency[cur_v - 1][i] - 1] = cur_mark;
					earliest_marks[adjacency[cur_v - 1][i] - 1] = marks[adjacency[cur_v - 1][i] - 1];
					cur_mark++;
					num_of_visited++;
					added_someone = true;
					last_marked[cur_v - 1] = i;
					break;
				}
				else {
					earliest_marks[cur_v - 1] = min(earliest_marks[cur_v - 1], marks[adjacency[cur_v - 1][i] - 1]);
				}

			}
			if (!added_someone) {
				if (visited.size() >= 2) {
					if (num_of_bad_sons[visited.top() - 1] == 0) {
						add_null_cur_component++;
					}
					if (num_of_bad_sons[visited.top() - 1] == 1) {
						add_one++;
					}
				}

				visited.pop();
				if (!visited.empty()) {
					if (visited.size() == 1) {
						sons_of_root++;
					}
					earliest_marks[visited.top() - 1] = min(earliest_marks[cur_v - 1], earliest_marks[visited.top() - 1]);
					if (earliest_marks[cur_v - 1] >= marks[visited.top() - 1]) {
						num_of_bad_sons[visited.top() - 1]++;
					}
					//earliest
				}

			}
		}
	}


	//and criteria for root
	if (sons_of_root == 1) {
		add_null_cur_component += sons_of_root;
	}
	if (sons_of_root == 0) {
		isolated++;
	}
	add_null += add_null_cur_component;
	add_null_squares += add_null_cur_component*add_null_cur_component;

}

int main() {

	ifstream in("destroy.in");
	ofstream out("destroy.out");
	int v, e;
	in >> v >> e;

	vector<vector<int>>adjacency(v);


	/*	bool**adjacency = new bool*[v];
	for (int i = 0; i < v; ++i) {
	adjacency[i] = new bool[v];
	for (int j = 0; j < v; ++j) {
	adjacency[i][j] = false;
	}
	}*/

	for (int i = 0; i < e; ++i) {
		int v1, v2;
		in >> v1 >> v2;
		adjacency[v1 - 1].push_back(v2);
		adjacency[v2 - 1].push_back(v1);
	}



	int*marks = new int[v];
	for (int i = 0; i < v; ++i) {
		marks[i] = 0;
	}

	int*earliest_marks = new int[v];
	for (int i = 0; i < v; ++i) {
		earliest_marks[i] = 0;
	}
	int*num_of_bad_sons = new int[v];

	for (int i = 0; i < v; ++i) {
		num_of_bad_sons[i] = 0;
	}


	int*last_marked = new int[v];
	for (int i = 0; i < v; ++i) {
		last_marked[i] = -1;
	}
	dfs(adjacency, marks, earliest_marks, v, num_of_bad_sons, last_marked);


	//for (int i = 0; i < v; ++i) {
	//cout<< earliest_marks[i] ;
	//}

	//int*parents = new int[v];

	//cout << add_null <<endl;
	//cout << add_null_squares <<endl;
	//cout << (add_null*add_null - add_null_squares) / 2 << endl;
	//cout << isolated*add_one << endl;
	out << (add_null*add_null - add_null_squares) / 2 + isolated*add_one << endl;
	//system("pause");
	return 0;
}