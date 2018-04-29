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

void build(int l_b, int r_b, int root) {
	if (l_b != r_b) {
		build(l_b, (l_b + r_b) / 2, 2 * root + 1);
		build((l_b + r_b) / 2 + 1, r_b, 2 * root + 2);
		segment_tree[root] = min(segment_tree[2 * root + 1], segment_tree[2 * root + 2]);
	}
}

void update(int l_b, int r_b, int ind, int val, int root) {
	if (l_b < r_b) {
		if (ind <= (l_b + r_b) / 2) {
			update(l_b, (l_b + r_b) / 2, ind, val, 2 * root + 1);
		}
		else {
			update((l_b + r_b) / 2 + 1, r_b, ind, val, 2 * root + 2);
		}
		segment_tree[root] = min(segment_tree[2 * root + 1], segment_tree[2 * root + 2]);
	}
	else {
		segment_tree[root] = val;
	}
}

int min_request(int l_b, int r_b, int l_c, int r_c, int root) {
	if (l_b == l_c && r_b == r_c) {
		return segment_tree[root];
	}
	if (r_c <= (l_b + r_b) / 2) {
		return min_request(l_b, (l_b + r_b) / 2, l_c, r_c, root * 2 + 1);
	}
	if (l_c > (l_b + r_b) / 2) {
		return min_request((l_b + r_b) / 2 + 1, r_b, l_c, r_c, root * 2 + 2);
	}
	return min(min_request(l_b, (l_b + r_b) / 2, l_c, (l_b + r_b) / 2, root * 2 + 1), min_request((l_b + r_b) / 2 + 1, r_b, (l_b + r_b) / 2 + 1, r_c, root * 2 + 2));
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
	for (int i = 0; i < 4 * n; i++) {
		segment_tree[i] = 100001;
	}
	build(0, n - 1, 0);
	update(0, n - 1,(*participants.begin()).second_competition - 1, (*participants.begin()).third_сompetition, 0);
	int  kol = 1;
	set<Participant>::iterator p = participants.begin();
	p++;
	while (p != participants.end()) {
		int res2 = (*p).second_competition;
		int res3 = (*p).third_сompetition;
		if (res3 < min_request(0, n - 1, 0, res2 - 1, 0))
			kol++;
		update(0, n - 1, res2 - 1, res3, 0);
		p++;
	}
	fout << kol;
	return 0;
}