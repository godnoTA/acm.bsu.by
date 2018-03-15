#include <iostream>
#include <fstream>
using namespace std;

ofstream fout("output.txt");
ifstream input("input.txt");

struct TreeNode
{
	TreeNode *left, *right;
	long key; 
};

void AddNodeOfTree(long key, TreeNode *&Tree)
{
	if (Tree==NULL)  
	{
		Tree = new TreeNode; 
		Tree->key = key; 
		Tree->left = NULL; 
		Tree->right = NULL;
	}

	if (key < Tree->key)
	{
		if (Tree->left != NULL)
			AddNodeOfTree(key, Tree->left);
		else 
		{
			Tree->left = new TreeNode;  
			Tree->left->left = NULL;
			Tree->left->right = NULL;
			Tree->left->key = key; 
		}
	}

	if (key > Tree->key) 
	{
		if (Tree->right != NULL) 
			AddNodeOfTree(key, Tree->right);
		else 
		{
			Tree->right = new TreeNode;
			Tree->right->left = NULL;
			Tree->right->right = NULL;
			Tree->right->key = key; 
		}
	}
}

void StraightThoughOfTree(TreeNode *&Tree)//pramoj obhod
{
	if (NULL == Tree)
		return;   

	fout << Tree->key<<"\n";

	StraightThoughOfTree(Tree->left);
	StraightThoughOfTree(Tree->right);
}

void main()
{
	long number;

	TreeNode *Tree = NULL;

	if (!input.is_open())
		return;
	while (input >> number)
		AddNodeOfTree(number, Tree);

	StraightThoughOfTree(Tree);
}
