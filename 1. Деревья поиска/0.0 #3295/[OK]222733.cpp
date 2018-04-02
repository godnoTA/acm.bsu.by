#include <iostream>
#include <fstream>
using namespace std;

long long int sum;

struct high {
	int key,sum;
	high* left;
	high* right;
};

void add(high *&tree, long int key){
	if (tree == NULL){
		tree = new high;
		tree->key = key;
		tree->left = NULL;
		tree->right = NULL;
		sum = key;
	}

	if (key < tree->key){
		if (tree->left != NULL)
		{
			add(tree->left, key);
		}
		else{
			tree->left = new high;
			tree->left->key = key;
			tree->left->left = tree->left->right = NULL;
			sum = sum + key;
		}
	}

	if (key > tree->key){
		if (tree->right != NULL)
		{
			add(tree->right, key);
		}
		else{
			tree->right = new high;
			tree->right->key = key;
			tree->right->left = NULL;
			tree->right->right = NULL;
			sum = sum + key;
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
		if(!fin.eof())
			add(tree, n);
	}
	ofstream fout("output.txt");
	fout<<sum;
	fin.close();
	fout.close();
}