#include <iostream>
#include <fstream>
#include <algorithm>
#include <set>
#include <vector>
#include <iterator>
#include <map>
using namespace std;
struct vertex {
	int value;
	int left = -1;
	int right = -1;
	int ancestor = -1;
};
void add(vector <vertex>& tree, int number) {
	if (tree.empty()) {
		vertex a;
		a.value = number;
		tree.push_back(a);
	}
	else {
		vertex a;
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
	}
};
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
void main() {
	ifstream in;
	in.open("input.txt");
	char x[100];
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
	out.open("output.txt");
	for (int item : value) {
		add(tree, item);
	}
	boolean.assign(tree.size(), false);
	pref_tr(tree, 0);
	for (int item : order) {
		out << item << endl;
	}
	out.close();
}