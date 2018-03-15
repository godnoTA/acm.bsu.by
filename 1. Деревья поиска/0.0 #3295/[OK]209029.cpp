#include<iostream>
#include <set>
#include<fstream>
using namespace std;
class Node {
public:
	int key;
	int level;
	Node* left;
	Node* right;
	Node(int key) {
		this->key = key;
		this->right = NULL;
		this->left = NULL;
	}
};
class BST {
private:
	Node* root;
	int height;
	void deleteNode(Node* node) {
		if (node) {
			deleteNode(node->left);
			deleteNode(node->right);
			delete node;
		}
	}
public:
	BST() {
		this->root = NULL;
		this->height = 0;
	}
	~BST() {
		deleteNode(root);
	}
	void push(int value);
	int getHeight();
	void deleteByKey(int key);

};
void BST::deleteByKey(int key) {
	if (this->root != NULL) {
		Node* tp = NULL;
		if (this->root->key == key) {
			tp = this->root->right;
			if (tp == NULL) {
				this->root = this->root->left;
				return;
			}
			else {
				if (tp->left == NULL) {
					this->root->right->left = this->root->left;
					this->root = this->root->right;
					return;
				}
				else {
					while (tp->left->left != NULL) {
						tp = tp->left;
					}
					Node* tmp = tp->left;
					tp->left = tp->left->right;
					tmp->right = this->root->right;
					tmp->left = this->root->left;
					this->root = tmp;
					return;
				}
			}
		}
		Node* pointer = this->root;
		while (pointer->left != NULL || pointer->right != NULL) {
			if (key<pointer->key) {
				if (pointer->left != NULL) {
					if (pointer->left->key == key) {
						Node* tpl = pointer->left->right;
						if (tpl == NULL) {
							pointer->left = pointer->left->left;
							return;
						}
						else {
							if (tpl->left == NULL) {
								pointer->left->right->left = pointer->left->left;
								pointer->left = pointer->left->right;
								return;
							}
							else {
								while (tpl->left->left != NULL) {
									tpl = tpl->left;
								}
								Node* tmp = tp->left;
								tp->left = tp->left->right;
								tmp->right = pointer->left->right;
								tmp->left = pointer->left->left;
								pointer->left = tmp;
								return;
							}
						}
					}
					else {
						pointer = pointer->left;
					}
				}
			}
			if (key>pointer->key) {
				if (pointer->right != NULL) {
					if (pointer->right->key == key) {
						Node* tpr = pointer->right->right;
						if (tpr == NULL) {
							pointer->right = pointer->right->left;
							return;
						}
						else {
							if (tpr->left == NULL) {
								pointer->right->right->left = pointer->right->left;
								pointer->right = pointer->right->right;
								return;
							}
							else {
								while (tpr->left->left != NULL) {
									tpr = tpr->left;
								}
								Node* tmpr = tpr->left;
								tpr->left = tpr->left->right;
								tmpr->right = pointer->right->right;
								tmpr->left = pointer->right->left;
								pointer->right = tmpr;
								return;
							}
						}
					}
					else {
						pointer = pointer->right;
					}
				}
			}
		}
	}
}
int BST::getHeight() {
	return this->height;
}
void BST::push(int value) {
	Node* newNode = new Node(value);
	if (this->root == NULL) {
		root = newNode;
		root->level = 0;
		return;
	}
	Node* pointer = root;
	int i = 1;
	while (pointer != NULL) {
		if (value < pointer->key) {
			if (pointer->left == NULL) {
				newNode->level = i;
				pointer->left = newNode;
				if (i > this->height)
					this->height++;
				return;
			}
			else {
				pointer = pointer->left;
			}
		}
		else {
			if (pointer->right == NULL) {
				newNode->level = i;
				pointer->right = newNode;
				if (i > this->height)
					this->height++;
				return;
			}
			else {
				pointer = pointer->right;
			}
		}
		i++;
	}
}
void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	set<int> s;
	int tmp;
	while (in) {
		in >> tmp;
		s.insert(tmp);
	}
	long long sum = 0;
	for (int var : s)
	{
		sum += var;
	}
	out << sum;
	/*
	BST* t = new BST();
	t->push(10);
	t->push(15);
	t->push(5);
	t->push(9);
	t->push(3);
	t->push(8);
	t->push(7);
	t->push(16);
	t->push(1);*/
}