#include <fstream> 
using namespace std;
class Node
{
public:
	int Key;
	Node *Left = NULL, *Right = NULL;

	Node(int t)
	{
		Key = t;
	}	
};

class BinaryTree
{
	
public:
	Node * Root;
	BinaryTree()
	{
		Root = NULL;
	}
	void addKey(int key)
	{
		Node **current = &Root;
		while (*current)
		{
			Node &node = **current;
			if (key < node.Key)
			{
				current = &node.Left;
			}
			else if (key > node.Key)
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

	Node* get_place(int key)
	{
		Node *current = Root;
		while (current != NULL)
		{
			if (key < current->Key)
				current = current->Left;
			else if (key > current->Key)
				current = current->Right;
			else
				return current;
		}
		return NULL;
	}

	void del(int key)
	{

		if (get_place(key) == NULL)
			return;
		Node **current = &Root;
		while (*current)
		{
			if (key > (*current)->Key)
				current = &(*current)->Right;
			else if (key < (*current)->Key)
				current = &(*current)->Left;
			else
				break;
		}
		if (!(*current)->Left)
		{
			*current = (*current)->Right;
			return;
		}
		else if (!(*current)->Right)
		{
			*current = (*current)->Left;
			return;
		}
		else
		{
			Node **temp = &(*current)->Right;
			if (!(*temp)->Left)
			{
				(*current)->Key = (*temp)->Key;
				(*current)->Right = (*temp)->Right;
			}
			else
			{
				Node **mostleft = &(*temp)->Left;
				while ((*mostleft)->Left)
				{
					temp = mostleft;
					mostleft = &(*mostleft)->Left;
				}
				(*current)->Key = (*mostleft)->Key;
				(**temp).Left = (*mostleft)->Right;
			}
			return;
		}
	}


	void printLeft(Node *n, ofstream &s)
		{
			if (n == NULL)
			return;
			s << n->Key << endl;
			printLeft(n->Left, s);
			printLeft(n->Right, s);
		}
};

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	BinaryTree t;
	int temp, key;
	fin >> key;
	while (!fin.eof())
	{
		fin >> temp;
		t.addKey(temp);
	}
	fin.close();
	t.del(key);
	t.printLeft(t.Root , fout);
	fout.close();
	return 0;
}