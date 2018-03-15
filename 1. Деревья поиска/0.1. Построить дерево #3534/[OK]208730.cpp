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

void lineLeft(Node *&node)
{
	out << node->key<<endl;
	if (node->left != NULL)
	{
		lineLeft(node->left);
	}
	if (node->right != NULL)
	{
		lineLeft(node->right);
	}
}

int main()
{
	ifstream in("input.txt");
	int c = 0;
	in >> c;
	Node *node = new Node;
	node->key = c;
	node->left = NULL;
	node->right = NULL;
	while (in >> c) {
		AddElement(c, node);
	}
	lineLeft(node);
	in.close();
	out.close();
}