#include <iostream>
#include <fstream>
#include <set>
#include <algorithm>

using namespace std;
int segment_tree[1000000];

struct Participant {
	int first_competition;
	int second_competition;
	int third_сompetition;
	Participant(int c1, int c2, int c3) {
		first_competition = c1;
		second_competition = c2;
		third_сompetition = c3;
	}
	bool operator <(const Participant &p) const {
		return (this->first_competition < p.first_competition);
	}
};

void build(int current_node, int left_bound, int right_bound){
	if (left_bound == right_bound) {
		segment_tree[current_node] = 100001;
	}
	else {
		int middle = (left_bound + right_bound) / 2;
		build(2 * current_node, left_bound, middle);
		build(2 * current_node + 1, middle + 1, right_bound);
		segment_tree[current_node] = min(segment_tree[2 * current_node], 
											segment_tree[2 * current_node + 1]);
	}
}
void update(int current_node, int left_bound, int right_bound, int pos, int new_value) {
	if (left_bound == right_bound) {
		segment_tree[current_node] = new_value;
	}	
	else {
		int middle = (left_bound + right_bound) / 2;
		if (pos <= middle)
			update(current_node * 2, left_bound, middle, pos, new_value);
		else
			update(current_node * 2 + 1, middle + 1, right_bound, pos, new_value);
		segment_tree[current_node] = min(segment_tree[current_node * 2], segment_tree[current_node * 2 + 1]);
	}
}
int minimum(int current_node, int left_bound, int right_bound, int left_position, int right_position) {
	if (left_position > right_position)
		return 100001;
	if (left_position == left_bound && right_position == right_bound)
		return segment_tree[current_node];
	int middle = (left_bound + right_bound) / 2;
	return min(minimum(current_node * 2, left_bound, middle, left_position, min(right_position, middle)),
		minimum(current_node * 2 + 1, middle + 1, right_bound, max(left_position, middle + 1), right_position));
}
int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	int n;
	fin >> n;
	
	set<Participant> participants;
	for (int i = 0; i < n; i++) {
		int res1, res2, res3;
		fin >> res1 >> res2 >> res3;
		participants.insert(Participant(res1, res2, res3));
	}
	build(1, 0, n - 1);
	update(1, 0, n - 1,(*participants.begin()).second_competition - 1, (*participants.begin()).third_сompetition);
	int  kol = 1;
	set<Participant>::iterator p = participants.begin();
	p++;
	while (p != participants.end()) {
		int res2 = (*p).second_competition;
		int res3 = (*p).third_сompetition;
		if (res3 < minimum(1, 0, n - 1, 0, res2 - 1))
			kol++;
		update(1, 0, n - 1, res2 - 1, res3);
		p++;
	}
	fout << kol;
	return 0;
}