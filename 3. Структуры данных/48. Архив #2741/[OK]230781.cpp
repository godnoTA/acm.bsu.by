#include<iostream>
#include<fstream>
#include<time.h>

using namespace std;

ifstream fin("archive.in");
ofstream fout("archive.out");

struct vertex {
	int subtree_size, priority, value;
	vertex *left = nullptr, *right = nullptr, *parent = nullptr;
	vertex(int priority, int value) : priority(priority), value(value) {}
};

void update_subtree_size(vertex* v) {
	if (v == nullptr)
		return;
	else if (v->left == nullptr && v->right != nullptr)
		v->subtree_size = 1 + v->right->subtree_size;
	else if (v->left != nullptr && v->right == nullptr)
		v->subtree_size = 1 + v->left->subtree_size;
	else if (v->left == nullptr && v->right == nullptr)
		v->subtree_size = 1;
	else v->subtree_size = 1 + v->left->subtree_size + v->right->subtree_size;
}

void set_subtree_sizes(vertex *v) {
	if (v == nullptr)
		return;
	set_subtree_sizes(v->left);
	set_subtree_sizes(v->right);
	update_subtree_size(v);
}

vertex* make_tree(int size) {
	srand(time(0));
	vertex *v = new vertex(rand(), 1);
	for (int i = 2; i <= size; i++) {
		vertex *vTmp = new vertex(rand(), i);
		while (v->parent != nullptr && v->priority < vTmp->priority)
			v = v->parent;
		if (v->priority < vTmp->priority) {
			vTmp->left = v;
			v->parent = vTmp;
			v = vTmp;
		}
		else {
			vTmp->left = v->right;
			if (v->right != nullptr)
				v->right->parent = vTmp;
			v->right = vTmp;
			vTmp->parent = v;
			v = vTmp;
		}
	}
	while (v->parent != nullptr)
		v = v->parent;
	set_subtree_sizes(v);
	return v;
}

vertex* merge(vertex* left, vertex *right) {
	if (left == nullptr || right == nullptr)
		return (left == nullptr ? right : left);
	else if (left->priority > right->priority) {
		left->right = merge(left->right, right);
		update_subtree_size(left);
		return left;
	}
	else {
		right->left = merge(left, right->left);
		update_subtree_size(right);
		return right;
	}
}

void split(vertex *source, vertex* &left, vertex* &right, int left_cnt) {
	if (source == nullptr) {
		left = right = nullptr;
		return;
	}
	int left_size;
	if (source->left != nullptr)
		left_size = source->left->subtree_size;
	else left_size = 0;
	if (left_cnt <= left_size) {
		split(source->left, left, source->left, left_cnt);
		right = source;
	}
	else {
		split(source->right, source->right, right, left_cnt - left_size - 1);
		left = source;
	}
	update_subtree_size(source);
}

void print(vertex *v) {
	if (v->left != nullptr) {
		print(v->left);
		fout << " ";
	}
	fout << v->value;
	if (v->right != nullptr) {
		fout << " ";
		print(v->right);
	}
}

int main() {
	int n, q;
	fin >> n >> q;
	vertex *root = make_tree(n), *tmp, *left, *middle_right, *middle, *right;
	for (int i = 0; i < q; i++) {
		int a, b;
		fin >> a >> b;
		split(root, left, middle_right, a - 1);
		split(middle_right, middle, right, b - a + 1);
		tmp = merge(middle, left);
		root = merge(tmp, right);
	}
	print(root);
}