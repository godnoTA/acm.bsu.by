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

int main(){
	

	TreeNode* rt = NULL;

	while (!fin.eof()){
		int q;
		fin >> q;
		rt=add_tree(rt, q);
	}
	preorderPrint(rt);
	return 0;
}