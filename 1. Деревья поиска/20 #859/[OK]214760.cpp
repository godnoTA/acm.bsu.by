#include <iostream>
#include <fstream>
#include <algorithm>
#include <set>
#include <vector>
#include <iterator>
#include <utility>
#include <map>
using namespace std;
struct vertex {
	int value;
	int left = -1;
	int right = -1;
	int ancestor = -1;
	vector <int> level_right;
	vector <int> level_left;
};
void add(vector <vertex>& tree, int number) {
	if (tree.empty()) {
		vertex a;
		a.value = number;
		a.level_left.push_back(0);
		a.level_right.push_back(0);
		tree.push_back(a);
	}
	else {
		vertex a;
		a.level_left.push_back(tree.size());
		a.level_right.push_back(tree.size());
		a.value = number;
		tree.push_back(a);
		int cur = 0;
		while (true) {
			if (number > tree[cur].value) {
				if (tree[cur].right == -1) {
					tree.back().ancestor = cur;
					tree[cur].right = tree.size() - 1;
					break;
				}
				else {
					cur = tree[cur].right;
				}
			}
			else {
				if (tree[cur].left == -1) {
					tree.back().ancestor = cur;
					tree[cur].left = tree.size() - 1;
					break;
				}
				else {
					cur = tree[cur].left;
				}
			}
		}
		int anc = tree.back().ancestor;
		int num = tree.size() - 1;
		int kol = 1;
		while (anc != -1) {
			if (tree[anc].right == num) {
				if (tree[anc].level_right.size() == kol) {
					tree[anc].level_right.push_back(tree.size() - 1);
				}
				else if (tree[tree[anc].level_right[kol]].value > tree.back().value) {
					tree[anc].level_right[kol] = tree.size() - 1;
				}
			}
			else {
				if (tree[anc].level_left.size() == kol) {
					tree[anc].level_left.push_back(tree.size() - 1);
				}
				else if (tree[tree[anc].level_left[kol]].value > tree.back().value) {
					tree[anc].level_left[kol] = tree.size() - 1;
				}
			}
			num = anc;
			anc = tree[anc].ancestor;
			kol++;
		}
	}
};

pair<int, long long> find(vector <vertex>& tree) {
	int n = 0;
	int l;
	int max;
	long long minval;
	int r;
	if (tree[0].level_left.size() == tree[0].level_right.size()) {
		max = tree[0].level_left.size() + tree[0].level_right.size()-2;
		if (tree[tree[0].level_left[tree[0].level_left.size() - 2]].value + tree[tree[0].level_right[tree[0].level_right.size()-1]].value < tree[tree[0].level_right[tree[0].level_right.size() - 2]].value + tree[tree[0].level_left[tree[0].level_left.size()-1]].value) {
			l = tree[0].level_left.size() - 2;
			r = tree[0].level_right.size()-1;
			minval = tree[tree[0].level_left[tree[0].level_left.size() - 2]].value + tree[tree[0].level_right[tree[0].level_right.size()-1]].value;
		}
		else {
			l = tree[0].level_left.size()-1;
			r = tree[0].level_right.size() - 2;
			minval = tree[tree[0].level_right[tree[0].level_right.size() - 2]].value + tree[tree[0].level_left[tree[0].level_left.size()-1]].value;
		}
	}
	else {
		max = tree[0].level_left.size() + tree[0].level_right.size()-1;
		minval = tree[tree[0].level_left[tree[0].level_left.size()-1]].value + tree[tree[0].level_right[tree[0].level_right.size()-1]].value;
		l = tree[0].level_left.size()-1;
		r = tree[0].level_right.size()-1;
	}
	for (int i = 1; i < tree.size(); i++) {
		if (tree[i].level_left.size() + tree[i].level_right.size() - 1 >= max) {
			if (tree[i].level_left.size() == tree[i].level_right.size()) {
				if (tree[i].level_left.size() + tree[i].level_right.size()-2 > max) {
					max = tree[i].level_left.size() + tree[i].level_right.size() - 2;
					if (tree[tree[i].level_left[tree[i].level_left.size() - 2]].value + tree[tree[i].level_right[tree[i].level_right.size() - 1]].value < tree[tree[i].level_left[tree[i].level_left.size() - 1]].value + tree[tree[i].level_right[tree[i].level_right.size() - 2]].value) {
						l = tree[i].level_left.size() - 2;
						r = tree[i].level_right.size()-1;
						minval = tree[tree[i].level_left[tree[i].level_left.size() - 2]].value + tree[tree[i].level_right[tree[i].level_right.size() - 1]].value;
					}
					else {
						l = tree[i].level_left.size()-1;
						r = tree[i].level_right.size() - 2;
						minval = tree[tree[i].level_left[tree[i].level_left.size() - 1]].value + tree[tree[i].level_right[tree[i].level_right.size() - 2]].value;
					}
				}
				else if (tree[i].level_left.size() + tree[i].level_right.size()-2 == max) {
					if (tree[tree[i].level_left[tree[i].level_left.size() - 2]].value + tree[tree[i].level_right[tree[i].level_right.size()-1]].value < minval) {
						l = tree[i].level_left.size() - 2;
						r = tree[i].level_right.size()-1;
						minval = tree[tree[i].level_left[tree[i].level_left.size() - 2]].value + tree[tree[i].level_right[tree[i].level_right.size()-1]].value;
						n = i;
					}
					if (tree[tree[i].level_right[tree[i].level_right.size() - 2]].value + tree[tree[i].level_left[tree[i].level_left.size()-1]].value < minval) {
						minval = tree[tree[i].level_right[tree[i].level_right.size() - 2]].value + tree[tree[i].level_left[tree[i].level_left.size()-1]].value;
						l = tree[i].level_left.size()-1;
						r = tree[i].level_right.size()-2;
						n = i;
					}
				}
			}
			else {
				if (tree[i].level_left.size() + tree[i].level_right.size() - 1 > max) {
					max = tree[i].level_left.size() + tree[i].level_right.size() - 1;
					minval = tree[tree[i].level_right[tree[i].level_right.size() - 1]].value + tree[tree[i].level_left[tree[i].level_left.size() - 1]].value;
					l = tree[i].level_left.size() - 1;
					r = tree[i].level_right.size()-1;
					n = i;
				}
				else if (tree[i].level_left.size() + tree[i].level_right.size() - 1 == max) {
					if (tree[tree[i].level_right[tree[i].level_right.size() - 1]].value + tree[tree[i].level_left[tree[i].level_left.size() - 1]].value < minval) {
						minval = tree[tree[i].level_right[tree[i].level_right.size() - 1]].value + tree[tree[i].level_left[tree[i].level_left.size() - 1]].value;
						l = tree[i].level_left.size() - 1;
						r = tree[i].level_right.size() - 1;
						n = i;
					}
				}
			}
		}
	}
	int y;
	if ((l + r) % 2 == 0) {
		if (l == r) {
			y = n;
		}
		if (l > r) {
			y = tree[n].left;
			int e = (l - r) / 2;
			l--;
			for (int i = 0; i < e - 1; i++) {
				if (tree[y].level_left.size()-1 == l) {
					y = tree[y].left;
				}
				else {
					y = tree[y].right;
				}
				l--;
			}
		}
		if (l < r) {
			y = tree[n].right;
			int e = (r - l) / 2;
			r--;
			for (int i = 0; i < e - 1; i++) {
				if (tree[y].level_left.size()-1 == r) {
					y = tree[y].left;
				}
				else {
					y = tree[y].right;
				}
				r--;
			}
		}
	}
	else {
		y = -1;
	}
	return make_pair(y, minval);
};
void reverse(vector<vertex>& tree, int number) {
	int ind = number;
	while (ind != 0) {
		if (tree[ind].value < tree[tree[ind].ancestor].value) {
			int s = ind;
			ind = tree[ind].ancestor;
			swap(tree[s].value, tree[ind].value);
			if (tree[ind].right != -1) {
				tree[tree[ind].right].ancestor = s;
			}
			if (tree[s].left != -1) {
				tree[tree[s].left].ancestor = ind;
			}
			int f = tree[s].left;
			tree[s].left = tree[s].right;
			tree[s].right = tree[ind].right;
			tree[ind].left = f;
			tree[ind].right = s;
		}
		else {
			int s = ind;
			ind = tree[ind].ancestor;
			swap(tree[s].value, tree[ind].value);
			if (tree[ind].left != -1) {
				tree[tree[ind].left].ancestor = s;
			}
			if (tree[s].right != -1) {
				tree[tree[s].right].ancestor = ind;
			}
			int f = tree[s].right;
			tree[s].right = tree[s].left;
			tree[s].left = tree[ind].left;
			tree[ind].right = f;
			tree[ind].left = s;
		}
	}
}
vector <bool> boolean;
vector <int> order;
void pref_tr(vector <vertex>& tree, int cur) {
	if (boolean[cur] == false) {
		order.push_back(tree[cur].value);
		boolean[cur] = true;
	}
	if (tree[cur].left != -1 && boolean[tree[cur].left] == false) {
		pref_tr(tree, tree[cur].left);
	}
	else if (tree[cur].right != -1 && boolean[tree[cur].right] == false) {
		pref_tr(tree, tree[cur].right);
	}
	else if (tree[cur].ancestor != -1) {
		pref_tr(tree, tree[cur].ancestor);
	}
};
int main() {
	ifstream in;
	in.open("in.txt");
	vector <int> value;
	map <int, int> n;
	while (in) {
		int a; in >> a;
		n[a]++;
		if (n[a] == 1) {
			value.push_back(a);
		}
	}
	in.close();
	ofstream out;
	vector <vertex> tree;
	out.open("out.txt");
	for (int item : value) {
		add(tree, item);
	}
	boolean.assign(tree.size(), false);
	pair<int, long long> k = find(tree);
	if (k.first != -1) {
		reverse(tree, k.first);
	}
	out << k.second << endl;
	pref_tr(tree, 0);
	for (int item : order) {
		out << item << endl;
	}
	out.close();
	return 0;
}