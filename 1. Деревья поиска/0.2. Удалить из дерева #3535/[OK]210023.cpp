#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <queue>
#include <ctime>

struct node {
	int value;
	node* left = nullptr;
	node* right = nullptr;
	node* dad = nullptr;
};

class Tree {
private:
	node* root;

public: 
	Tree(int value) {
		root = new node;
		root->value = value;
	}

	node* getRoot() const {
		return root;
	}

	void push(node* x, node* r) {
		if (x->value == r->value) return;
		if (x->value < r->value) {
			if (r->left) {
				push(x, r->left);
			} else {
				r->left = x;
				x->dad = r;
			}
		}
		else {
			if (r->right) {
				push(x, r->right);
			} else {
				r->right = x;
				x->dad = r;
			}
		}
	}

	node* findValue(int value, node* r) {
		if (r == nullptr) return nullptr;
		else if (r->value == value) {
			return r;
		}
		else if (value < r->value) {
			return findValue(value, r->left);
		}
		else {
			return findValue(value, r->right);
		}
	}

	void deleteNode(node* x) { // if empty
		if (root == x) {
			if (x->right && !x->left) {
				x->right->dad = nullptr;
				root = x->right;
				delete x;
			}
			else if (x->right) {
				node* t = x->right;
				while (t->left) {
					t = t->left;
				}
				x->value = t->value;
				deleteNode(t);
			} else {
				x->left->dad = nullptr;
				root = x->left;
				delete x;
			}
		}
		else if (!x->right && !x->left) {
			if (x->value < x->dad->value) { // =
				x->dad->left = nullptr;
			} else {
				x->dad->right = nullptr;
			}
			delete x;
		}
		else if (!x->left) { // =
			if (x->value < x->dad->value) {
				x->dad->left = x->right;
			} else {
				x->dad->right = x->right;
			}
			x->right->dad = x->dad;
			delete x;
		}
		else if (!x->right) { // =
			if (x->value < x->dad->value) {
				x->dad->left = x->left;
			} else {
				x->dad->right = x->left;
			}
			x->left->dad = x->dad;
			delete x;
		}
		else {
			node* t = x->right;
			while (t->left) {
				t = t->left;
			}
			x->value = t->value;
			deleteNode(t);
		}
	}

	void leftOrder(node* r, std::ofstream& out) {
		out << r->value << "\n";
		if (r->left)
			leftOrder(r->left, out);
		if (r->right)
			leftOrder(r->right, out);
	}
};

int main() {
	std::ifstream in("input.txt");
	std::ofstream out("output.txt");
	int number;
	in >> number;
	int value;
	in >> value;
	Tree tree(value);
	while (in >> value) {
		node* x = new node;
		x->value = value;
		tree.push(x, tree.getRoot());
	}
	node* t = tree.findValue(number, tree.getRoot());
	if (t != nullptr) 
		tree.deleteNode(t);
	tree.leftOrder(tree.getRoot(), out);
	in.close();
	out.close();
	return 0;
}
