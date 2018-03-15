#define _CRT_SECURE_NO_WARNINGS 
#include <iostream> 
#include <fstream> 
using namespace std;

struct Node{
	int key;
	Node *left;
	Node *right;
};

Node* findMin(Node *node) {
	if (node->left != NULL) {
		return findMin(node->left);
	}
	else {
	return node;
	}
}

void keyDelete(int delKey, Node*& node){
	if (node == NULL){
		return;
	}
	if (delKey > node->key){
		keyDelete(delKey, node->right);
		return;
	}
	else if (delKey < node->key)
	{
		keyDelete(delKey, node->left);
		return;
	}
	if (node->left == NULL)
	{
		node = node->right;
		return;
	}
	else if (node->right == NULL)
	{
		node = node->left;
		return;
	}
	else {
		int min = findMin(node->right)->key;
		node->key = min;
		keyDelete(min, node->right);
		return;
	}
}

void inOrder(Node *head, ofstream &out)
{
	if (head == NULL)
	{
		return;
	}
	out << head->key << endl;
	inOrder(head->left, out);
	inOrder(head->right, out);
}

void add(int value, Node *&node)
{
	if (node != NULL)
	{
		if (value > node->key)
		{
			add(value, node->right);
		}
		if (value < node->key)
		{
			add(value, node->left);
		}

	}
	else
	{
		node = new Node;
		node->key = value;
		node->left = node->right = NULL;

	}
}

void main()
{
	Node *tree = NULL;
	int key = 0;
	int delKey = 0;
	ofstream out("output.txt");
	ifstream in("input.txt");
	in >> delKey;
	while (in >> key)
	{
		add(key, tree);
	}
	keyDelete(delKey, tree);
	inOrder(tree, out);
	out.close();
	in.close();
	system("pause");
}