#include <iostream>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <vector>
using namespace std;
int schech;
struct  KNode
{
	KNode(int Info = 0, KNode * Left = 0, KNode * Right = 0, KNode * Parent = 0, bool Visit = false) : Info(Info), Left(Left), Right(Right), Parent(Parent), Visit(Visit) {}
	KNode(const KNode& other) : Info(other.Info), Left(other.Left), Right(other.Right), Parent(other.Parent), Visit(other.Visit) {}
	int Info;
	KNode * Left;
	KNode * Right;
	KNode * Parent;
	bool    Visit;
};

class Drevo
{
public:
	Drevo() { fTree = 0;  schech = 0; }
	~Drevo() { Erase(fTree); }
	void Add(int Inf);
	KNode *& Root() { return fTree; }
	void Inorder(KNode *);
	void Preorder(KNode *);
	void Postorder(KNode *);
	int SubTreeRec(KNode *);
	KNode * SredElem(KNode *&);
	int Maxim(int a, int b) { return (a>b) ? a : b; }
	KNode * TreeMinimum(KNode * x);
	void WriteToFile(ofstream & str, const vector<KNode>& array);
	void ReadFromFile(ifstream & str);
	void SecPreorder(KNode *& fTree, vector<KNode>& array);
	bool Delete(int node);
	int Nodes(KNode * fTree);
private:
	void _Add(KNode *& fTree, int Inf);
	bool _Delete(KNode *& fTree, int node);
	void _FillLabels(KNode *);
	void Erase(KNode *);
	KNode * fTree;
	static int count;
	static KNode * rec;
};



int Drevo::count = 0;
KNode * Drevo::rec = 0;

int Drevo::Nodes(KNode * fTree)
{
	int l, r;
	if (fTree == 0)
		return 0;
	else
	{
		l = (fTree->Left == 0) ? 0 : Nodes(fTree->Left);
		r = (fTree->Right == 0) ? 0 : Nodes(fTree->Right);
	}
	return l + r + 1;
}

void Drevo::_Add(KNode *& fTree, int Inf)
{
	if (fTree == 0)
	{
		fTree = new KNode;
		fTree->Left = 0;
		fTree->Right = 0;
		fTree->Info = Inf;
		fTree->Visit = false;
	}
	else
		if (Inf < fTree->Info) _Add(fTree->Left, Inf);
		else
			if (Inf > fTree->Info) _Add(fTree->Right, Inf);
}

void Drevo::Add(int Inf)
{
	_Add(fTree, Inf);
}

void Drevo::Inorder(KNode * fTree)
{
	if (fTree != 0)
	{
		Inorder(fTree->Left);
		cout << fTree->Info << ' ';
		Inorder(fTree->Right);
	}
}

void Drevo::Preorder(KNode * fTree)
{
	if (fTree != 0)
	{
		cout << fTree->Info << ' ';
		Preorder(fTree->Left);
		Preorder(fTree->Right);
	}
}

void Drevo::Postorder(KNode * fTree)
{
	if (fTree != 0)
	{
		Postorder(fTree->Left);
		Postorder(fTree->Right);
		cout << fTree->Info << ' ';
	}
}

void Drevo::SecPreorder(KNode *& fTree, vector<KNode>& array)
{
	if (fTree != 0)
	{
		array.push_back(fTree->Info);
		SecPreorder(fTree->Left, array);
		SecPreorder(fTree->Right, array);
	}
}

KNode * Drevo::SredElem(KNode *& fTree)
{
	if (fTree == 0)
	{
		return 0;
		cout << "Tree is empty" << endl;
	}
	else
	{
		if (fTree->Left != 0) SredElem(fTree->Left);
		if (fTree->Visit == true) { count++; }
		if ((fTree->Visit == true) && (count == (schech / 2) + 1)) { rec = fTree; }
		if (fTree->Right != 0) SredElem(fTree->Right);
	}
	return rec;
}

int Drevo::SubTreeRec(KNode * fTree)
{
	int l, r;
	if (fTree == 0)
	{
		cout << "Tree is empty" << endl;
		return 0;
	}
	else
	{
		l = (fTree->Left == 0) ? 0 : SubTreeRec(fTree->Left);
		r = (fTree->Right == 0) ? 0 : SubTreeRec(fTree->Right);
		if (l - r != 0) { ++schech; /*cout << fTree -> Info <<' ';*/ fTree->Visit = true; }
	}
	return Maxim(l, r) + 1;
}

KNode * Drevo::TreeMinimum(KNode * x)
{
	while (x->Left)
		x = x->Left;

	return x;
}

bool Drevo::_Delete(KNode *& fTree, int node)
{
	KNode * temp;
	if (fTree == 0) return false; //element no found
	else
		if (node < fTree->Info) _Delete(fTree->Left, node);
		else
			if (node > fTree->Info) _Delete(fTree->Right, node);
			else  //we've found the element!!!
			{
				if (fTree->Left == 0) //tree have only a rigth child
				{
					temp = fTree;
					fTree = fTree->Right;
					delete temp;
					return true;
				}
				else
					if (fTree->Right == 0) //tree have only a left child
					{
						temp = fTree;
						fTree = fTree->Left;
						delete temp;
						return true;
					}
					else //tree have two children
					{
						temp = TreeMinimum(fTree->Right);
						fTree->Info = temp->Info;
						_Delete(fTree->Right, fTree->Info);
					}
			}
}

bool Drevo::Delete(int node)
{
	//	_FillLabels(fTree);
	return _Delete(fTree, node);
}

void Drevo::_FillLabels(KNode * fTree)
{
	if (fTree != 0) fTree->Visit = false;
	if (fTree->Left != 0) _FillLabels(fTree->Left);
	if (fTree->Right != 0) _FillLabels(fTree->Right);
}

void Drevo::Erase(KNode * fTree)
{
	if (fTree != 0)
	{
		Erase(fTree->Left);
		Erase(fTree->Right);
		delete fTree;
		fTree = 0;
	}
}

void Drevo::ReadFromFile(ifstream & str)
{
	int elem;
	// str >> elem; Add(elem); 
	while (!str.fail())
	{
		str >> elem;
		if (!str.fail())
			Add(elem);
	}
	str.clear();
}

void Drevo::WriteToFile(ofstream & str, const vector<KNode>& array)
{
	if (fTree == 0) { exit(0); }
	else
	{
		for (vector<KNode>::const_iterator i = array.begin(); i != array.end(); i++)
		{
			str << i->Info << endl;
		}
		str.flush();
	}
}
//***********************************************************************

int main(int argc, char* argv[])
{
	vector<KNode> V; Drevo MyTree; KNode * el;	vector<KNode> array; int n;
	ifstream fin("in.txt");
	MyTree.ReadFromFile(fin);
	n = MyTree.Nodes(MyTree.Root());
	cout << n << endl;
	if (MyTree.Root() != 0)
	{
		MyTree.SubTreeRec(MyTree.Root()); cout << endl;
		el = MyTree.SredElem(MyTree.Root());
		if (schech % 2 == 0) cout << "There is no middle leaf" << endl;
		else { cout << el->Info << endl; }
		if (schech % 2 == 0) { cout << "Can't delete" << endl; }
		else { MyTree.Delete(el->Info); }
		MyTree.SecPreorder(MyTree.Root(), V);
		ofstream fout("out.txt");
		MyTree.WriteToFile(fout, V);
		return 0;
	}
	else { ofstream fout("out.txt"); MyTree.WriteToFile(fout, V); return 0; }
}

