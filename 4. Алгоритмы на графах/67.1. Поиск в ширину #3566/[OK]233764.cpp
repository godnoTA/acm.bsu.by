#include <fstream>
#include <vector>
#include <queue>
#include <algorithm>
#include <map>

using namespace std;

vector<int> round(int first, const vector<vector<int>> &matrix, vector<bool> &used) {
	vector<int> in_order_by_labels;

	queue<int> round;
	round.push(first);
	used[first] = true;

	in_order_by_labels.push_back(first);

	while (!round.empty()) {
		int current = round.front();
		round.pop();
		for (int i = 0; i < matrix.size(); ++i) {
			if (matrix[current][i] == 1 && !used[i]) {
				round.push(i);
				used[i] = true;
				in_order_by_labels.push_back(i);
			}
		}
	}
	return in_order_by_labels;
}

vector<int> round(const vector<vector<int>> &matrix) {
	unsigned long n = matrix.size();

	vector<bool> used(n, false);
	vector<int> all_rounds;

	for (int i = 0; i < n; ++i) {
		if (!used[i]) {
			vector<int> new_round = round(i, matrix, used);
			all_rounds.insert(all_rounds.end(), new_round.begin(), new_round.end());
		}
	}

	map<int, int> vertex_and_label;
	for (int i = 0; i < n; ++i) {
		vertex_and_label.insert(make_pair(all_rounds[i], i));
	}

	vector<int> in_order_by_verties;
	for (auto element : vertex_and_label) {
		in_order_by_verties.push_back(element.second);
	}

	return in_order_by_verties;
}



int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	int n;
	fin >> n;

	vector<vector<int>> matrix((unsigned long)n);

	for (int i = 0; i < n; ++i) {
		int number;
		for (int j = 0; j < n; ++j) {
			fin >> number;
			matrix[i].push_back(number);
		}
	}

	vector<int> res = round(matrix);

	for (auto num : res) {
		fout << num + 1 << " ";
	}

	return 0;
}




