#include <iostream> 
#include <fstream> 
#include <string> 
#include <set> 
using namespace std;

ofstream out("tst.out");
int k;

struct Node {
	int key;
	Node *left;
	int lc;
	Node *right;
	int rc;
	Node *parent;
	int pc;
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
			node->left->parent = node;
			node->left->left = NULL;
			node->left->right = NULL;
			node->left->lc = 0;
			node->left->rc = 0;
			node->left->pc = 0;
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
			node->right->parent = node;
			node->right->left = NULL;
			node->right->right = NULL;
			node->right->lc = 0;
			node->right->rc = 0;
			node->right->pc = 0;
		}
	}
}

void lineLeft(int key, Node *&node)
{
	if (key != node->key)
		out << node->key << endl;
	if (node->left != NULL)
	{
		lineLeft(key, node->left);
	}
	if (node->right != NULL)
	{
		lineLeft(key, node->right);
	}
}

int max(int a, int b)
{
	if (a > b)
		return a;
	return b;
}

void Run(Node *&node)
{
	if (node->left != NULL)
	{
		Run(node->left);
	}
	if (node->right != NULL)
	{
		Run(node->right);
	}
	if (node->left != NULL)
	{
		node->lc = max(node->left->lc, node->left->rc) + 1;
	}
	if (node->right != NULL)
	{
		node->rc = max(node->right->lc, node->right->rc) + 1;
	}
}

void Run1(Node *&node)
{
	if (node->parent != NULL)
	{
		if (node->parent->right == node)
		{
			node->pc = max(node->parent->pc, node->parent->lc) + 1;
		}
		if (node->parent->left == node)
		{
			node->pc = max(node->parent->pc, node->parent->rc) + 1;
		}
	}
	if (node->left != NULL)
	{
		Run1(node->left);
	}
	if (node->right != NULL)
	{
		Run1(node->right);
	}
}

int maxsum(Node *&node)
{
	if (node->rc + node->pc >= k)
		return 0;
	if (node->rc + node->lc >= k)
		return 0;
	if (node->lc + node->pc >= k)
		return 0;
	return 1;
}

void Lastfunk(int& p, Node *&node)
{
	int cd = maxsum(node);
	if (cd == 1)
	{
		if (node->key>p)
		{
			p = node->key;
		}
	}
	if (node->left != NULL)
	{
		Lastfunk(p, node->left);
	}
	if (node->right != NULL)
	{
		Lastfunk(p, node->right);
	}
}

void DeleteRight(int key, Node *&node)
{
	if (node->key == key)
	{
		if (node->right != NULL && node->left != NULL) {
			Node *newNode = node->right;
			while (newNode->left != NULL)
				newNode = newNode->left;
			node->key = newNode->key;
			newNode->key = key;
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
	ifstream in("tst.in");
	int t = 0;
	int c = 0, check = 0, p = -1, e = 1;
	in >> k;
	in >> t;
	if (in.eof())
	{
		out << "Empty";
		out.close();
		return 0;
	}

	
	Node *node = new Node;
	node->key = t;
	node->left = NULL;
	node->right = NULL;
	node->parent = NULL;
	node->lc = 0;
	node->rc = 0;
	node->pc = 0;
	while (in >> c) {
		if(t!=c)
		e++;
		AddElement(c, node);
	}
	Run(node);
	Run1(node);
	Lastfunk(p, node);
	if (k > 0 && e == 1)
	{
		out << "Empty";
		out.close();
		return 0;
	}
	DeleteRight(p, node);
	lineLeft(p, node);
	in.close();
	out.close();
	return 0;
}