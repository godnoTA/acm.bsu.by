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
		this->right = nullptr;
		this->left = nullptr;
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
		this->root = nullptr;
		this->height = 0;
	}
	~BST() {
		deleteNode(root);
	}
	Node* getRoot() { return root; }
	void preorder(ofstream& out,Node* node);
	void push(int value);
	int getHeight();
	void deleteByKey(int key);

};
void BST::deleteByKey(int key) {
	if (this->root != nullptr) {
		Node* tp = nullptr;
		if (this->root->key == key) {
			if (this->root->left == nullptr&&this->root->right != nullptr)
			{
				this->root = this->root->right;
				return;
			}
			if (this->root->right == nullptr&&this->root->left != nullptr)
			{
				this->root = this->root->left;
				return;
			}
			tp = this->root->right;
			if (tp == nullptr) {
				this->root = this->root->left;
				return;
			}
			else {
				if (tp->left == nullptr) {
					this->root->right->left = this->root->left;
					this->root = this->root->right;
					return;
				}
				else {
					while (tp->left->left != nullptr) {
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
		while (pointer->left != nullptr || pointer->right != nullptr) {
			if (key<pointer->key) {
				if (pointer->left != nullptr) {
					if (pointer->left->key == key) {
						if (pointer->left->left == nullptr&&pointer->left->right != nullptr)
						{
							pointer->left = pointer->left->right;
							return;
						}
						if (pointer->left->right == nullptr&&pointer->left->left != nullptr)
						{
							pointer->left = pointer->left->left;
							return;
						}
						Node* tpl = pointer->left->right;
						if (tpl == nullptr) {
							pointer->left = pointer->left->left;
							return;
						}
						else {
							if (tpl->left == nullptr) {
								pointer->left->right->left = pointer->left->left;
								pointer->left = pointer->left->right;
								return;
							}
							else {
								while (tpl->left->left != nullptr) {
									tpl = tpl->left;
								}
								Node* tmp = tpl->left;
								tpl->left = tpl->left->right;
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
				if (pointer->right != nullptr) {
					if (pointer->right->key == key) {
						if (pointer->right->left == nullptr&&pointer->right->right != nullptr)
						{
							pointer->right = pointer->right->right;
							return;
						}
						if (pointer->right->right == nullptr&&pointer->right->left != nullptr)
						{
							pointer->right = pointer->right->left;
							return;
						}
						Node* tpr = pointer->right->right;
						if (tpr == nullptr) {
							pointer->right = pointer->right->left;
							return;
						}
						else {
							if (tpr->left == nullptr) {
								pointer->right->right->left = pointer->right->left;
								pointer->right = pointer->right->right;
								return;
							}
							else {
								while (tpr->left->left != nullptr) {
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
	if (this->root == nullptr) {
		root = newNode;
		root->level = 0;
		return;
	}
	Node* pointer = root;
	int i = 1;
	while (pointer != nullptr) {
		if (pointer->key == value)
			return;
		if (value < pointer->key) {
			if (pointer->left == nullptr) {
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
			if (pointer->right == nullptr) {
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
void BST::preorder(ofstream& out,Node* node) {
	if (node == nullptr)
		return;
	out << node->key << endl;
	preorder(out, node->left);
	preorder(out, node->right);
}
void main() {
	ifstream in("input.txt");
	ofstream out("output.txt");
	set<int> s;
	int tmp;
	BST t;
	int del;
	in >> del;
	while (in >> tmp) {
		t.push(tmp);
	}
	t.deleteByKey(del);
	t.preorder(out, t.getRoot());
}