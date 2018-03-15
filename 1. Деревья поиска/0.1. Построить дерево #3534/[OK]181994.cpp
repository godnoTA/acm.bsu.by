#include<iostream>
#include<fstream>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

struct Node{
	int key;
	Node *left;
	Node *right;
};
void print(Node *root)
{

	if (root == NULL) {return;}
	fout << root->key << endl;
	print(root->left);
	print(root->right);
}
Node* clrNode(int k) {
	Node* node = new Node;
	node->key = k;
	node->left = NULL;
	node->right = NULL;
	return node;
}
Node* add(Node* root, int k){
	if (NULL == root) root = clrNode(k);
	if (k < root->key){
		if (NULL == root->left) root->left = clrNode(k);
		else add(root->left, k);
	}
	if (k>root->key){
		if (NULL == root->right) root->right = clrNode(k);
		else add(root->right, k);
	}
	return root;
}

int main(){

	Node* node = NULL;
	while (!fin.eof()){
		int new_key;
		fin >> new_key;
		node = add(node, new_key);
	}
	print(node);
	return 0;
}
