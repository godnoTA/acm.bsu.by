#include <iostream>
#include <fstream>
using namespace std;

struct high {
	int key;
	high* left;
	high* right;
};

void out(high *&tree, ofstream& fout){
	if (tree != NULL) {
		fout << tree->key << endl;
		out(tree->left, fout);
		out(tree->right, fout);
	}
}

void add(high *&tree, int key){
	if (tree == NULL){
		tree = new high;
		tree->key = key;
		tree->left = NULL;
		tree->right = NULL;
	}

	if (key < tree->key){
		if (tree->left != NULL)
			add(tree->left, key);
		else{
			tree->left = new high;
			tree->left->key = key;
			tree->left->left = tree->left->right = NULL;
		}
	}

	if (key > tree->key){
		if (tree->right != NULL)
			add(tree->right, key);
		else{
			tree->right = new high;
			tree->right->key = key;
			tree->right->left = NULL;
			tree->right->right = NULL;
		}
	}
}

void main()
{
	high *tree = NULL; 
	int n;
	ifstream fin("input.txt");
	while (!fin.eof()){
		fin >> n;
		add(tree, n);
	}
	ofstream fout("output.txt");
	out(tree, fout);
	fin.close();
	fout.close();
}