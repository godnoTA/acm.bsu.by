#include <iostream>
#include <fstream>
#include <string>
#include <vector>
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
		LeftHeight = 0;
		LeftAmount = 0;
		RightHeight = 0;
		RightAmount = 0;
	}
	TNode(int key)
	{
		Key = key;
		Left = NULL;
		Right = NULL;
		Father = NULL;
		LeftHeight = 0;
		LeftAmount = 0;
		RightHeight = 0;
		RightAmount = 0;
	}
	int Key;
	int LeftHeight;
	int LeftAmount;
	int RightHeight;
	int RightAmount;
	TNode* Left;
	TNode* Right;
	TNode* Father;
};
class Tree
{
private:
	void TaskR(TNode *node, vector <int> &B)
	{
		if (node->Left != NULL)
		{
			TaskR(node->Left, B);
		}
		if ((node->LeftAmount != node->RightAmount) && (node->LeftHeight == node->RightHeight))
		{
			B.push_back(node->Key);
		}
		if (node->Right != NULL)
		{
			TaskR(node->Right, B);
		}

	}
	void Initialize(TNode *node)
	{
		if (node->Left != NULL)
		{
			Initialize(node->Left);
		}
		if (node->Right != NULL)
		{
			Initialize(node->Right);
		}
		if (node->Left == NULL && node->Right == NULL)//Листовая вершина(они будут инициализированы первыми)
		{
			node->LeftHeight = -1;
			node->RightHeight = -1;
			node->LeftAmount = 0;
			node->RightAmount = 0;
		}
		if (node->Left != NULL && node->Right == NULL)//Левый есть, правого нет
		{
			int MaxHeight;
			if (node->Left->LeftHeight > node->Left->RightHeight)
			{
				MaxHeight = node->Left->LeftHeight;
			}
			else
			{
				MaxHeight = node->Left->RightHeight;
			}
			node->LeftHeight = 1 + MaxHeight;
			node->LeftAmount = 1 + node->Left->LeftAmount + node->Left->RightAmount;
			node->RightHeight = -1;
			node->RightAmount = 0;
		}
		if (node->Right != NULL && node->Left == NULL)//Правый есть, левого нет
		{
			int MaxHeight;
			if (node->Right->LeftHeight > node->Right->RightHeight)
			{
				MaxHeight = node->Right->LeftHeight;
			}
			else
			{
				MaxHeight = node->Right->RightHeight;
			}
			node->RightHeight = 1 + MaxHeight;
			node->RightAmount = 1 + node->Right->LeftAmount + node->Right->RightAmount;
			node->LeftHeight = -1;
			node->LeftAmount = 0;
		}
		if (node->Left != NULL && node->Right != NULL)//Оба сына есть
		{
			int MaxHeight1;
			if (node->Left->LeftHeight > node->Left->RightHeight)
			{
				MaxHeight1 = node->Left->LeftHeight;
			}
			else
			{
				MaxHeight1 = node->Left->RightHeight;
			}
			int MaxHeight2;
			if (node->Right->LeftHeight > node->Right->RightHeight)
			{
				MaxHeight2 = node->Right->LeftHeight;
			}
			else
			{
				MaxHeight2 = node->Right->RightHeight;
			}
			node->LeftHeight = 1 + MaxHeight1;
			node->LeftAmount = 1 + node->Left->LeftAmount + node->Left->RightAmount;
			node->RightHeight = 1 + MaxHeight2;
			node->RightAmount = 1 + node->Right->LeftAmount + node->Right->RightAmount;
		}
	}
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
	void DeleteMed()
	{
		vector <int> B;
		TaskR(root, B);
		int Buff;
		if (B.size() % 2 == 1)
		{
			Buff = B.size() / 2;
//			cout << Buff << endl;
//			cout << B[Buff] << endl;
/*			cout << "For 20: LeftHeight" << root->LeftHeight << endl;
			cout << "For 20: RightHeight" << root->RightHeight << endl;
			cout << "For 20: LeftAmount" << root->LeftAmount << endl;
			cout << "For 20: RightAmount" << root->RightAmount << endl;*/
			Delete(B[Buff]);
		}
	}
	void Init()
	{
		if (!empty)
		{
			Initialize(root);
		}
	}
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
	ifstream fin("in.txt");
	ofstream fout("out.txt");
	Tree tree;
	int S;
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
	tree.Init();
	tree.DeleteMed();
//	tree.Delete(20);
	tree.Show(fout);
	fout.close();
}