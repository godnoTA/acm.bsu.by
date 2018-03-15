#include <iostream> 
#include <fstream> 
#include <string> 
#include <set> 
using namespace std;

ofstream out("output.txt");

struct Node {
	int key;
	Node *left;
	Node *right;
};

void AddElement(int key, Node *&node)
{
	if (node->key > key)
	{
		if (node->left != NULL)
			AddElement(key, node->left);
		else
		{
			node->left = new Node;
			node->left->key = key;
			node->left->left = NULL;
			node->left->right = NULL;
		}
	}
	if (node->key < key)
	{
		if (node->right != NULL)
			AddElement(key, node->right);
		else
		{
			node->right = new Node;
			node->right->key = key;
			node->right->left = NULL;
			node->right->right = NULL;
		}
	}
}

void lineLeft(int r, Node *&node)
{
	if (node->key != r)
		out << node->key << endl;
	if (node->left != NULL)
	{
		lineLeft(r, node->left);
	}
	if (node->right != NULL)
	{
		lineLeft(r, node->right);
	}
}


void DeleteRight(int key, Node *&node)
{
	if (node->key == key)
	{

		Node *a = new Node;
		if (node->right != NULL && node->left != NULL)
		{
			a = node->right;
			while (a->left != NULL)
			{
				a = a->left;
			}
			node->key = a->key;
			a->key = key;
			key = -241241412412;
		}
	}
	if (node->left != NULL)
	{
		DeleteRight(key, node->left);
	}
	if (node->right != NULL)
	{
		DeleteRight(key, node->right);
	}
}

int main()
{
	ifstream in("input.txt");
	int c = 0, r = 0;
	in >> r >> c;
	Node *node = new Node;
	node->key = c;
	node->left = NULL;
	node->right = NULL;
	while (in >> c) {
		AddElement(c, node);
	}
	DeleteRight(r, node);
	lineLeft(r, node);
	in.close();
	out.close();
}