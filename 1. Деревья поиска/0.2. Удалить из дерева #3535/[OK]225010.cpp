#include<iostream>
#include<windows.h>
#include <process.h>
#include<fstream>
#include<stack>
#include<string>
#define null NULL
using namespace std;
class Node {
	public: 
		int key;
		Node* left = null;
		Node* right=null;
		Node* parent=null;
	Node(int key) {
		this->key = key;
	}
};
class Tree {
public :
	Node* root;
	void replace(Node* a, Node* b) {
		if (a->parent == null)
			root = b;
		else if (a == a->parent->left)
			a->parent->left = b;
		else
			a->parent->right = b;
		if (b != null)
			b->parent = a->parent;
	}
	void remove(Node* t, int key) {
		if (t == null)
			return;
		if (key < t->key)
			remove(t->left, key);
		else if (key > t->key)
			remove(t->right, key);
		else if (t->left != null && t->right != null) {
			Node* m = t->right;
			while (m->left != null)
				m = m->left;
			t->key = m->key;
			replace(m, m->right);
		}
		else if (t->left != null) {
			replace(t, t->left);
		}
		else if (t->right != null) {
			replace(t, t->right);
		}
		else {
			replace(t, null);
		}
	}
public:
	void remove(int key) {
		remove(root, key);
	}
	Node* doInsert(Node* node, int x) {
		if (node == null) {
			Node* test = new Node(x);
			return test;
		}
		if (x < node->key) {
			node->left = doInsert(node->left, x);
			node->left->parent = node;
		}
		else if (x > node->key) {
			node->right = doInsert(node->right, x);
			node->right->parent = node;
		}
		return node;
	}
	void insert(int x) {
		root = doInsert(root, x);
	}	
};
void leftDirect(Tree* binaryTree) {
	ofstream out("output.txt");
	stack <Node*> tresh;
	string str="";
	Node* top = binaryTree->root;
	while (top != null || !tresh.empty()) {
		if (!tresh.empty()) {
			top = tresh.top();
			tresh.pop();
		}
		while (top != null) {
			str+=to_string(top->key) + "\n";
			if (top->right != null)
				tresh.push(top->right);
			top = top->left;
		}
	}
	out << str.c_str();
	out.close();
}
UINT WINAPI myStat(void* arg)
{
	ifstream in("input.txt");
	Tree* binaryTree = new Tree();
	int g;
	int delNumber;
	in >> delNumber;
	while (!in.eof()) {
		in >> g;
		binaryTree->insert(g);
	}
	in.close();
	binaryTree->remove(delNumber);
	leftDirect(binaryTree);
	return 0;
}
int main() {
	HANDLE stat = (HANDLE)_beginthreadex(NULL, 1024 * 64, myStat, NULL, 0, NULL);
	WaitForSingleObject(stat, INFINITE);
	CloseHandle(stat);
	return 0;
}