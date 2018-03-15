#include <iostream>
#include <fstream>
#include <vector>
#include <set>
#include <queue>

struct node {
	int value;
	node* dad = nullptr;
	node* left = nullptr;
	node* right = nullptr;
	int leftNumber = 0;
	int rightNumber = 0;
};

class Tree {
private:
	node* root;
	std::set<int> forDeleting;
	std::queue<node*> forBFS;
	std::vector<node*> lists;

public:
	Tree(int value) {
		root = new node;
		root->value = value;
		forBFS.push(root);
	}
	node* getRoot() const {
		return root;
	}

	void push(node* x, node* r) {
		if (x->value == r->value) return;
		if (x->value < r->value) {
			++x->leftNumber;
			if (r->left) {
				push(x, r->left);
			}
			else {
				r->left = x;
				x->dad = r;
			}
		}
		else {
			++x->rightNumber;
			if (r->right) {
				push(x, r->right);
			}
			else {
				r->right = x;
				x->dad = r;
			}
		}
	}

	void bfs() {
		while (!forBFS.empty()) {
			node* x = forBFS.front();
			forBFS.pop();
			if (!x->left && !x->right) {
				lists.push_back(x);
			}
			else {
				if (x->left)
					forBFS.push(x->left);
				if (x->right)
					forBFS.push(x->right);
			}
		}
	}

	void chooseNodes() {
		int min = lists[0]->leftNumber + lists[0]->rightNumber;
		for (node *x : lists) {
			if (min > x->leftNumber + x->rightNumber) {
				min = x->leftNumber + x->rightNumber;
			}
		}
		for (node *x : lists) {
			if (min == x->leftNumber + x->rightNumber &&
				!(min & 1)) {
				middleNode(x, true, 0);
			}
		}
	}

	void middleNode(node* list, bool wasLeft, int slot) {
		if ((wasLeft && list->leftNumber == list->rightNumber + slot) ||
			(!wasLeft && list->leftNumber + slot == list->rightNumber)) {
			forDeleting.insert(list->value);
		}
		else {
			if (list->value < list->dad->value)
				middleNode(list->dad, true, slot + 1);
			else
				middleNode(list->dad, false, slot + 1);
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

	void deleteMiddleNodes() {
		while (!forDeleting.empty()) {
			node* x = findValue(*forDeleting.begin(), root);
			forDeleting.erase(forDeleting.begin());
			deleteNode(x);
		}
	}

	void deleteNode(node* x) { // if empty
		if (root == x) {
			if (x->right && !x->left) {
				x->right->dad = nullptr;
				root = x->right;
				delete x;
			}
			else if (x->left && !x->right) {
				x->left->dad = nullptr;
				root = x->left;
				delete x;
			} else {
				node* t = x->left;
				while (t->right) {
					t = t->right;
				}
				x->value = t->value;
				deleteNode(t);
			}
		}
		else if (!x->right && !x->left) {
			if (x->value <= x->dad->value) { // =
				x->dad->left = nullptr;
			}
			else {
				x->dad->right = nullptr;
			}
			delete x;
		}
		else if (!x->left) {
			if (x->value < x->dad->value) {  // =
				x->dad->left = x->right;
			}
			else {
				x->dad->right = x->right;
			}
			x->right->dad = x->dad;
			delete x;
		}
		else if (!x->right) { 
			if (x->value <= x->dad->value) { // =
				x->dad->left = x->left;
			}
			else {
				x->dad->right = x->left;
			}
			x->left->dad = x->dad;
			delete x;
		}
		else {
			node* t = x->left;
			while (t->right) {
				t = t->right;
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
	std::ifstream in("tst.in");
	int value;
	in >> value;
	Tree tree(value);
	while (in >> value) {
		node* x = new node;
		x->value = value;
		tree.push(x, tree.getRoot());
	}

	tree.bfs();
	tree.chooseNodes();
	tree.deleteMiddleNodes();

	std::ofstream out("tst.out");
	tree.leftOrder(tree.getRoot(), out);
	in.close();
	out.close();
	return 0;
}
