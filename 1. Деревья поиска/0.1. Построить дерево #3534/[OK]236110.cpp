#include <fstream> 

using namespace std;

class Node
{
public:
	int key;
	Node *Left = NULL, *Right = NULL;

	Node(int t)
	{
		key = t;
	}

};

class BinarySearchTree
{
	Node *Root;
public:
	BinarySearchTree()
	{
		Root = NULL;
	}


	void insert(int key)
	{
		Node **current = &Root;
		while (*current)
		{
			Node &node = **current;
			if (key < node.key)
			{
				current = &node.Left;
			}
			else if (key > node.key)
			{
				current = &node.Right;
			}
			else
			{
				return;
			}
		}
		*current = new Node(key);
	}

	Node* getRoot( )
	{
		return Root;
	}

	void LeftWay(Node *n, ofstream &s)
		{
			if (n == NULL)
			return;
			s << n->key<< endl;
			LeftWay(n->Left, s);
			LeftWay(n->Right, s);
		}
};

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	BinarySearchTree t;
	int temp;
	fin >> temp;
	while (!fin.eof())
	{
		t.insert(temp);
		fin >> temp;
	}
	t.insert(temp);
	t.LeftWay(t.getRoot(), fout);
	return 0;
}