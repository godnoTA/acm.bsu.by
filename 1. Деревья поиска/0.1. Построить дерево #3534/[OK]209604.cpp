#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <queue>

struct node {
	int value;
	node* left = nullptr;
	node* right = nullptr;
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
			}
		}
		else {
			if (r->right) {
				push(x, r->right);
			} else {
				r->right = x;
			}
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
	int value;
	in >> value;
	Tree tree(value);
	while (in >> value) {
		node* x = new node;
		x->value = value;
		tree.push(x, tree.getRoot());
	}
	std::ofstream out("output.txt");
	tree.leftOrder(tree.getRoot(), out);
	in.close();
}
