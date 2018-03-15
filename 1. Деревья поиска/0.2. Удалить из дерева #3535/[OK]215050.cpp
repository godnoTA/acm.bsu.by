#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
	Node* left;
	Node* right;
	int value;
	Node(int _value) : left(NULL), right(NULL), value(_value) {}
};

Node* root = NULL;
Node* tmp;

void add(int value) {
	tmp = root;
	if (root == NULL) {
		root = new Node(value);
		return;
	}
	Node* parent = NULL;
	while (tmp != NULL) {
		parent = tmp;
		if (tmp->value > value) {
			tmp = tmp->left;
		}
		else {
			tmp = tmp->right;
		}
	}
	if (parent->value > value) {
		parent->left = new Node(value);
	}
	else {
		parent->right = new Node(value);
	}
}

void print(Node* vertex) {
	if (vertex == NULL) {
		return;
	}
	cout << vertex->value << endl;
	print(vertex->left);
	print(vertex->right);
}

vector<int> ans;

int getHeight(Node* vertex) {
	if (vertex == NULL) {
		return -1;
	}
	int l = getHeight(vertex->left);
	int r = getHeight(vertex->right);
	if (l != r) {
		ans.push_back(vertex->value);
	}
	return max(l, r) + 1;
}

int getMin(Node* vertex) {
	if (vertex->left == NULL) {
		return  vertex->value;
	}
	return getMin(vertex->left);
}

void remove(Node* vertex, Node* parent, int val) {
	if (vertex == NULL) {
		return;
	}
	if (vertex->value == val) {
		if (vertex->right == NULL && vertex->left == NULL) {
			if (parent->left == vertex) {
				parent->left = NULL;
			}
			else {
				parent->right = NULL;
			}
			return;
		}
		if (vertex->right == NULL) {
			if (parent->left == vertex) {
				parent->left = vertex->left;
			}
			else {
				parent->right = vertex->left;
			}
			return;
		}
		if (vertex->left == NULL) {
			if (parent->left == vertex) {
				parent->left = vertex->right;
			}
			else {
				parent->right = vertex->right;
			}
			return;
		}
		int mini = getMin(vertex->right);
		vertex->value = mini;
		remove(vertex->right, vertex, mini);
		return;
	}
	if (vertex->value > val) {
		remove(vertex->left, vertex, val);
	}
	else {
		remove(vertex->right, vertex, val);
	}
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	int del;
	cin >> del;
	int x;
	while (cin >> x) {
		if (x == -1) {
			break;
		}
		add(x);
	}
	Node* vertex = root;
	if (vertex == NULL) {
		return 0;
	}
	//getHeight(vertex);
	//if (ans.size() % 2 != 0) {
		//sort(ans.begin(), ans.end());
		//int del = ans[ans.size() / 2];
		vertex = new Node(0);
		vertex->left = root;
		remove(vertex->left, vertex, del);
		if (vertex->left != NULL) {
			cout << vertex->left->value << endl;
			print(vertex->left->left);
			print(vertex->left->right);
		}
		else {
			return 0;
		}
	//}
	//else {
		//print(vertex);
	//}
	return 0;
}