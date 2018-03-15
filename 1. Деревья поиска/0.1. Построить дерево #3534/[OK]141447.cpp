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

	while (!fin.eof())
	{
		fin >> a;
		paste(root, a);
	}


	show(root, fout);
}