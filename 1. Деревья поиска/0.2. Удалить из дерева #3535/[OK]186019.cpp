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
		Father = NULL;
	}
	TNode(int key)
	{
		Key = key;
		Left = NULL;
		Right = NULL;
		Father = NULL;

	}
	int Key;
	TNode* Left;
	TNode* Right;
	TNode* Father;
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
				node->Left->Father = node;
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
				node->Right->Father = node;
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
	void SwitchRight(int x, TNode *node, TNode *node1)
	{
		if (node->Left == NULL)//Нашли нужный для замены элемент в правом относительно удаляемого узла поддереве
		{
			if (node->Father->Key == x)
			{
				///////////
				TNode *Buff = node;
				node1->Key = node->Key;
				node->Father->Right = node->Right;
				if (node->Right != NULL)
				{
					node->Right->Father = node->Father;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!TUT
				}
				
				delete Buff;
				////////////
			}
			else
			{
				TNode *Buff = node;
				node1->Key = node->Key;
				node->Father->Left = node->Right;
				if (node->Right != NULL)
				{
					node->Right->Father = node->Father;
				}

				delete Buff;
			}
		}
		else
		{
			SwitchRight(x, node->Left, node1);
		}

	}
	int PDelete(int x, TNode *node)
	{
		if (x == node->Key)
		{
			if (node->Right != NULL && node->Left != NULL)
			{
				SwitchRight(x, node->Right, node);
				return 0;
			}
			if (node->Left != NULL && node->Right == NULL)
			{
				TNode *Buff = node->Left;
				node->Key = node->Left->Key;
				node->Right = node->Left->Right;
				node->Left = node->Left->Left;
				delete Buff;
				return 0;
			}
			if (node->Right != NULL && node->Left == NULL)
			{
				TNode *Buff = node->Right;
				node->Key = node->Right->Key;
				node->Left = node->Right->Left;
				node->Right = node->Right->Right;
				delete Buff;
				return 0;
			}
			if (node->Right == NULL && node->Left == NULL)
			{
				if (node->Father != NULL)
				{
					if (node->Father->Left == node)
					{
						TNode *Buff = node;
						node->Father->Left = NULL;
						delete Buff;
						return 0;
					}
					else
					{
						TNode *Buff = node;
						node->Father->Right = NULL;
						delete Buff;
						return 0;
					}
				}
				else
				{
					TNode *Buff = node;
					delete Buff;
					empty = true;
					return 0;
				}
			}

		}
		if (x < node->Key && node->Left != NULL)
		{
			PDelete(x, node->Left);
		}
		if (x > node->Key && node->Right != NULL)
		{
			PDelete(x, node->Right);
		}
	}
public:
	TNode *root;
	bool empty = true;
	bool Insert(int x)
	{
		empty = false;
		return PInsert(x, root);
	}
	void Show(ofstream &fout)
	{
		if (!empty)
		{
			PShow(root, fout);
		}
	}
	void Delete(int x)
	{
		PDelete(x, root);
	}
	Tree();
	~Tree();
};

Tree::Tree()
{
	root = new TNode();
}

Tree::~Tree()
{
}
int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	Tree tree;
	int S;
	int D;//Переменная для хранения удаляемого значения
	fin >> D;
	if (fin >> S)
	{
		tree.root->Key = S;
		tree.empty = false;
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
	tree.Delete(D);
	tree.Show(fout);
	fout.close();
}