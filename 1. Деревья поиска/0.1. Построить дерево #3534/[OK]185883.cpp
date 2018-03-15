#include <iostream>
#include <fstream>
#include <string>
#include <set>
using namespace std;
class TNode //узел дерева хранящий информацию о значении и т. д.
{
public:
	TNode()
	{
		Key = 0;
		Left = NULL;
		Right = NULL;
	}
	TNode(int key)
	{
		Key = key;
		Left = NULL;
		Right = NULL;

	}
	int Key;
	TNode* Left;
	TNode* Right;
};
class Tree
{
private:
	/*
	Рекурентная функция вставки нового ключа в ПБД
	*/
	bool PInsert(int i, TNode *node)
	{
		if (node->Key == i)
		{
			return false;
		}
		if (i < node->Key)
		{
			if (node->Left != NULL)
			{
				PInsert(i, (node->Left));
			}
			else
			{
				node->Left = new TNode(i);
				return true;
			}
		}
		if (i > node->Key)
		{
			if (node->Right != NULL)
			{
				PInsert(i, (node->Right));
			}
			else
			{
				node->Right = new TNode(i);
				return true;
			}
		}
	}
	/*
	Рекурентная функция прямого левого обхода ПБД
	*/
	void PShow(TNode *node, ofstream &fout)
	{
		fout << node->Key << endl;
		if (node->Left != NULL)
		{
			PShow(node->Left, fout);
		}
		if (node->Right != NULL)
		{
			PShow(node->Right, fout);
		}
		
	}
public:
	TNode root;
	bool Insert(int x)
	{
		return PInsert(x, &root);
	}
	void Show(ofstream &fout)
	{
		PShow(&root, fout);
	}
	Tree();
	~Tree();
};

Tree::Tree()
{
}

Tree::~Tree()
{
}
int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	Tree tree;
	int S = 0;
	if (fin >> S)
	{
		tree.root.Key = S;
	}
	else
	{
		return 0;
	}
	while (fin >> S)
	{
		tree.Insert(S);
	}
	fin.close();
	tree.Show(fout);
	fout.close();
}