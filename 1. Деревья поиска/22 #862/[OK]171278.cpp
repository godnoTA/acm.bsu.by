#include <iostream>
#include <fstream>
using namespace std;
class Binary_Trees {
public:
	struct KTNode
	{
		int Info;
		KTNode *Left;
		KTNode *Right;
		KTNode *parent;
		int Flag;
		int ColVer;
		int Height;

	};
	typedef KTNode *PNode;
	typedef PNode *PPNode;
public:
	Binary_Trees();
	~Binary_Trees();
	void  Add(int);
	void  PreorderRecur(ofstream &);
	void  InorderRecur(ofstream &);
	void  PostorderRecur(ofstream &);
	void  FindNode();
	void  WriteFile();
	int   ReadTree(char*);
	int   DelNode(PNode, int);
	int   DelSNode(int);
private:
	PNode SearchingNode;
	PNode Tree;
	ofstream* fout;
	bool  first;
	int   HeightLeft;
	int   HeightRight;
	int   ColLeft;
	int   ColRight;
	int   Count;
	//tools function 
	void  FindFlag(PNode);
	int   _FindNode(PNode);
	void  Erase(PNode);
	void  Preorder(PNode, ofstream &);
	void  Inorder(PNode, ofstream &);
	void  Postorder(PNode, ofstream &);
	void  Solve(PNode, ofstream &);
};

//----------------Implementations------------------
Binary_Trees::Binary_Trees()
{
	Tree = 0;
	SearchingNode = 0;
}

//Полное удаление дерева
void Binary_Trees::Erase(PNode T)
{
	if (T != 0)
	{
		Erase(T->Left);
		Erase(T->Right);
		delete T;
		T = 0;
	}
}


Binary_Trees::~Binary_Trees()
{
	Erase(Tree);
}


void Binary_Trees::Add(int nItem)
{
	PNode p;
	PNode predp;
	PPNode pp;
	pp = &Tree;
	p = Tree;
	predp = 0;
	while (p != 0)
	{
		predp = p;
		if (nItem>p->Info)
		{
			pp = &p->Right;
			p = p->Right;
		}
		else
			if (nItem<p->Info)
			{
				pp = &p->Left;
				p = p->Left;
			}
			else
				return;
	}
	p = new KTNode;
	p->Info = nItem;
	p->Left = 0;
	p->Right = 0;
	p->parent = predp;
	p->Flag = 0;
	p->ColVer = 0;
	p->Height = -1;
	*pp = p;
}

//Удаляет вершину node левым удалением,
//если Right<>0, иначе правым.
int Binary_Trees::DelNode(PNode node, int Right)
{
	int key = node->Info;
	if (node->Left && node->Right)//оба поддерева не пусты
	{
		PNode repnode;
		if (Right)
		{
			repnode = node->Right;
			while (repnode->Left)
				repnode = repnode->Left;
		}
		else //(!Right)
		{
			repnode = node->Left;
			while (repnode->Right)
				repnode = repnode->Right;
		}
		node->Info = repnode->Info;
		repnode->Info = key;
		return DelNode(repnode, Right);
	}
	else
		if (!node->Left && !node->Right)//лист
		{
			if (node->parent)
			{
				if (node->parent->Left && (node->parent->Left->Info == key))
					node->parent->Left = 0;
				else node->parent->Right = 0;
				delete node;
			}
			else //дерево состоит из одного элемента
				Tree = 0;
		}
		else
			if (node->Left && !node->Right)//правое поддерево пусто
			{
				if (node->parent->Left->Info == key)
					node->parent->Left = node->Left;
				else node->parent->Right = node->Left;
				delete node;
			}
			else
				if (!node->Left && node->Right)//левое поддерево пусто
				{
					if (node->parent->Left->Info == key)
						node->parent->Left = node->Right;
					else node->parent->Right = node->Right;
					delete node;
				}
	return 1;
}

int  Binary_Trees::DelSNode(int Right)
{
	if (SearchingNode)
		return DelNode(SearchingNode, Right);
	else return 0;
}

void Binary_Trees::Preorder(PNode T, ofstream &str)
{
	if (T != 0)
	{
		Solve(T, str);
		//		cout<<T->Info<<"апр ";
		Preorder(T->Left, str);
		Preorder(T->Right, str);
	}
}

void Binary_Trees::Inorder(PNode T, ofstream &str)
{
	if (T != 0)
	{
		Inorder(T->Left, str);
		Solve(T, str);
		//		cout<<T->Info<<" ";
		Inorder(T->Right, str);
	}
}


void Binary_Trees::Postorder(PNode T, ofstream &str)
{
	if (T != 0)
	{
		Postorder(T->Left, str);
		Postorder(T->Right, str);
		Solve(T, str);
		//		cout<<T->Info<<" ";
	}
}


void Binary_Trees::PostorderRecur(ofstream &str)
{
	if (Tree)
		Postorder(Tree, str);
	else str << "Tree is empty" << endl;
}


void Binary_Trees::InorderRecur(ofstream &str)
{
	if (Tree)
		Inorder(Tree, str);
	else str << "Tree is empty" << endl;
}

void Binary_Trees::PreorderRecur(ofstream &str)
{
	if (Tree)
		Preorder(Tree, str);
	else str << "Tree is empty" << endl;
}

void Binary_Trees::Solve(PNode T, ofstream &str)
{
	str << T->Info << endl;
}

int  Binary_Trees::ReadTree(char* FileName)
{

	int a, pa;
	a = -2332323;
	ifstream InFile(FileName);
	if (!InFile)
	{
		cout << FileName << " not found!";
		return 0;//File not found
	}
	else
	{
		while (!InFile.eof())
		{
			pa = a;
			InFile >> a;
			if (a != pa)
				Add(a);
		}
		InFile.close();
		return 1;
	}
}

void Binary_Trees::WriteFile()
{
	ofstream fout("out.txt");
	if (!fout)
	{
		cout << "error" << endl;
		return;
	}
	first = true;
	Preorder(Tree, fout);
	fout.close();
}

/*Найти среднюю по значению из вершин дерева,
у которых высоты поддеревьев не равны,
а количество потомков в правом и левом поддеревьях  равны.
Удалить ее (правым удалением), если такая вершина существует.
Выполнить прямой (левый) обход полученного дерева.
Если у вершины отсутствует некоторое поддерево,
то его высота считается равной -1, а количество потомков
в этом поддереве - 0.
*/
void Binary_Trees::FindNode()
{
	/*	if (!Tree)
	{
	WriteFile();
	return;
	}
	*/	Count = 0;
_FindNode(Tree);
cout << Count << endl;
if (Count % 2)
{
	Count = Count / 2 + 1;
	FindFlag(Tree);
	cout << SearchingNode->Info << endl;
	DelSNode(1);
}
WriteFile();
}

//Находит рекурсивно среднию вершину прямым обходом
void Binary_Trees::FindFlag(PNode node)
{
	if (Count<0)
		return;
	if (node->Left)
		FindFlag(node->Left);
	if (node->Flag)
		--Count;
	if (!Count)
	{
		SearchingNode = node;
		--Count;
	}
	else
		if (node->Right)
			FindFlag(node->Right);
}

int  Binary_Trees::_FindNode(PNode node)
{
	if (node->Left)
		_FindNode(node->Left);
	if (node->Right)
		_FindNode(node->Right);
	if (node->Left)
	{
		HeightLeft = node->Left->Height;
		ColLeft = node->Left->ColVer;
	}
	else
	{
		HeightLeft = -1;
		ColLeft = 0;
	}
	if (node->Right)
	{
		HeightRight = node->Right->Height;
		ColRight = node->Right->ColVer;
	}
	else
	{
		HeightRight = -1;
		ColRight = 0;
	}
	if ((ColLeft == ColRight) && (HeightLeft != HeightRight))
	{
		node->Flag = 1;
		++Count;
	}
	else node->Flag = 0;
	node->ColVer = 1 + ColLeft + ColRight;
	node->Height = (HeightLeft > HeightRight) ? (HeightLeft + 1) : (HeightRight + 1);
	return node->ColVer;
}

int main()
{
	Binary_Trees T;
	if (T.ReadTree("in.txt"))
	{
		// 	T.PreorderRecur(cout);
		T.FindNode();
	}
	return 0;
}