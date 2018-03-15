#include <iostream>
#include <iomanip>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

struct Node {
	Node(Node* parnt,long long newValue) {
		left = 0;
		right = 0;
		parent = parnt;
		value = newValue;
	} 
	Node* left;
	Node* right;
	Node* parent;
	long long value;
	bool isLeaf() {
		return (left == nullptr && right == nullptr);
	}
};

void paste(Node* &root, long long newValue) {
	if (!root)
		root = new Node(NULL,newValue);
	else if (newValue > root->value)
		if (!root->right)
			root->right = new Node(root,newValue);
		else
			paste(root->right, newValue);
	else if (newValue < root->value)
		if (!root->left)
			root->left = new Node(root,newValue);
		else
			paste(root->left, newValue);
}

Node* min(Node* root) {
	while (root->left){
		root = root->left;
	}
	return root;
}

Node* deleteNode(Node* root, long long value) {
	if (!root)
		return NULL;
	if (value < root->value) {
		root->left = deleteNode(root->left, value);
	}
	else if (value > root->value) {
		root->right = deleteNode(root->right, value);
	}
	else if (root->left  && root->right) {
		root->value = min(root->right)->value;
		root->right = deleteNode(root->right, min(root->right)->value);
	}
	else if (root->left)
		root = root->left;
	else if (root->right)
		root = root->right;
	else if (!root->left && !root->right)
		root = NULL;
	return root;
}

void show(Node* root, ofstream & fout) {
	if (!root) return;
	fout << root->value << endl;
	show(root->left, fout);
	show(root->right, fout);
}


void algorithm(Node* node, vector<Node>& vector) {
	if (!node)
		return;
	algorithm(node->left, vector);
	algorithm(node->right, vector);
	if (node->isLeaf()) {
		vector.push_back(*node);
	}
	return;
}

int main() {
	ifstream fin("tst.in");
	ofstream fout("tst.out");

	Node* root = NULL;
	long long a;
	long long value;
	vector <Node> vector;

	while (!fin.eof()){
		fin >> a;
		paste(root, a);
	}

	algorithm(root, vector);
	
	if (vector.size() % 2 == 0) {
		show(root, fout);
	}
	else {
		if (root->isLeaf()) {
			root = deleteNode(root, root->value);
			show(root, fout);
		}else {
			root = deleteNode(root, vector.at(vector.size() / 2).parent->value);
			show(root, fout);
		}
	}

}