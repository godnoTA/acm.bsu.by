#include <iostream>
#include <fstream>

std::ofstream fout("output.txt");

template<class T>
class bin_tree {
	struct node {
		T key;
		node* left, *right;
	};
	node* root;
public:

	node* clear(node* t) {
		if (t == NULL)
			return NULL;
		clear(t->left);
		clear(t->right);
		delete t;
		return NULL;
	}

	bin_tree() {
		root = NULL;
	}

	~bin_tree() {
		root = clear(root);
	}

	void insert(const T&a) {
		root = insert(a, root);
	}

	node* insert(const T&a, node*t) {
		if (t == NULL) {
			t = new node;
			t->left = t->right = NULL;
			t->key = a;
		}
		else if (a < t->key) {
			t->left = insert(a, t->left);
		}
		else if (a > t->key) {
			t->right = insert(a, t->right);
		}
		return t;
	}

	void traverse() {
		traverse(root);
	}

	void traverse(node*t) {
		if (t == NULL) {
			return;
		}
		fout << t->key << '\n';
		if (t->left)
			traverse(t->left);
		if (t->right)
			traverse(t->right);
	}

	void remove(const T& a) {
		root = remove(a, root);
	}

	node* findMin(node* t) {
		if (t == NULL) {
			return NULL;
		}
		if (t->left == NULL) {
			return t;
		}
		return findMin(t->left);
	}

	node* remove(const T& a, node* t) {
		if (t == NULL) {
			return NULL;
		}
		if (a < t->key) {
			t->left = remove(a, t->left);
		}
		else if (a > t->key) {
			t->right = remove(a, t->right);
		}
		else if (t->left && t->right) {
			node* tmp = findMin(t->right);
			t->key = tmp->key;
			t->right = remove(t->key, t->right);
		}
		else {
			node* tmp = t;
			if (t->left == NULL) {
				t = t->right;
			} 
			else if (t->right == NULL) {
				t = t->left;
			}
			delete tmp;
		}
		return t;
	}

};

int main() {
	std::ifstream fin("input.txt");
	int key, remove;
	fin >> remove;
	bin_tree<int> bst;
	while (fin >> key){
		bst.insert(key);
	}
	bst.remove(remove);
	bst.traverse();
	return 0;
}