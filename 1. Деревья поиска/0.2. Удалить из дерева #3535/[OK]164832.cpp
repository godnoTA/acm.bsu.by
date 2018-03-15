#include<iostream>
#include<fstream>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

struct TreeNode{
	int key; 
	TreeNode *left; 
	TreeNode *right; 
};
void preorderPrint(TreeNode *root)
{
	
	if (root == NULL)   
	{
		return;
	}
	fout << root->key << endl;
	preorderPrint(root->left);   
	preorderPrint(root->right);  
}
TreeNode* clrNode(int val) {
	TreeNode* node = new TreeNode;
	node->key = val;
	node->left = NULL;
	node->right = NULL;
	return node;
}
TreeNode* add_tree(TreeNode* root, int val){
	if (NULL == root) root = clrNode(val);
	if (val < root->key){
		if (NULL == root->left) root->left = clrNode(val);
		else add_tree(root->left, val);
	}
	if (val>root->key){
		if (NULL == root->right) root->right = clrNode(val);
		else add_tree(root->right, val);
	}
	return root;
}
int max_left(TreeNode* root){
	while (root->left != NULL) root = root->left;
	return root->key;
}
TreeNode* del_tree(TreeNode* root, int val){
	if (NULL == root) return NULL;
	if (root->key == val){
		if (NULL == root->left && NULL == root->right){
			free(root);
			return NULL;
		}
		if (NULL == root->left && NULL != root->right){
			TreeNode* temp = root->right;
			free(root);
			return temp;
		}
		if (NULL != root->left && NULL == root->right){
			TreeNode* temp = root->left;
			free(root);
			return temp;
		}
		root->key = max_left(root->right);
		root->right = del_tree(root->right, root->key);
		return root;
	}
	if (root->key > val){
		root->left = del_tree(root->left, val);
		return root;
	}
	if (root->key < val){
		root->right = del_tree(root->right, val);
		return root;
	}
}
int main(){
	

	TreeNode* rt = NULL;
	int key;
	fin >> key;
	while (!fin.eof()){
		int q;
		fin >> q;
		rt=add_tree(rt, q);
	}
	rt=del_tree(rt, key);
	preorderPrint(rt);
	return 0;
}