#include <iostream>
#include <iomanip>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

struct Node {
	Node(long long newValue = 0) : left(0), right(0), value(newValue) {};
	Node* left;
	Node* right;
	long long value;
};

void paste(Node* &root, long long newValue) {
	if (!root)
		root = new Node(newValue);
	else if (newValue > root->value)
		if (!root->right)
			root->right = new Node(newValue);
		else
			paste(root->right, newValue);
	else if (newValue < root->value)
		if (!root->left)
			root->left = new Node(newValue);
		else
			paste(root->left, newValue);
}

Node* min(Node* root){
	while (root->left)
	{
		root = root->left;
	}
	return root;
}

Node* deleteNode(Node* root, long long value){
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

int main() {
	ifstream fin("input.txt");
	ofstream fout("output.txt");

	Node* root = NULL;
	long long a;
	long long value;
	//char space;

	fin >> value;
	//fin >> space;

	while (!fin.eof())
	{
		fin >> a;
		paste(root, a);
	}

	//root = deleteNode(root, value);

	/*Node* parent = NULL;
	Node* dest = findNode(root, parent, value);
	removeNode(dest, parent, root, value);*/


	root = deleteNode(root, value);

	show(root, fout);
}