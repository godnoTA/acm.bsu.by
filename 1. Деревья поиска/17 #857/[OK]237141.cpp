#include <fstream>
#include <math.h>
#include <ostream>

#include <iostream>

using namespace std;

class CNode
{
public:
	CNode();
	virtual ~CNode();
public:
	CNode* Left;
	CNode* Right;
	int LeftCount;
	int RightCount;
	int LeftLength;
	int RightLength;
	int Key;
	bool Selected;
};


CNode::CNode()
: Left(0)
, Right(0)
, LeftCount(0)
, RightCount(0)
, LeftLength(0)
, RightLength(0)
, Selected(false)
{
}

CNode::~CNode()
{
	delete Left;
	delete Right;
}


class CBinTree
{
public:
	CBinTree();
	virtual ~CBinTree();
	bool Add(int Key);
	bool Delete(int Key);
	bool Find(int Key);
	bool Solve(bool Flg);
	int GetDelKey();
	void LoadFromFile(const char* FileName);
	void SaveToFile(const char* FileName);
	void Clear();
protected:
	void FindDel(CNode* Cur);
	void Going(CNode* Cur, int &Count, int &Length);
	void OutPut(std::ofstream& fout, CNode* Node);
protected:
	CNode* Root;
	int Max;
	int CountMax;
	int KeyMax;
};




CBinTree::CBinTree()
: Root(0)
{

}

CBinTree::~CBinTree()
{
	Clear();
}

bool CBinTree::Add(int Key)
{
	if (Find(Key))
		return false;
	CNode* Cur = Root;
	CNode* Prev = 0;
	CNode* New = new CNode;
	New->Key = Key;
	while (Cur != 0)
	{
		Prev = Cur;
		if (Cur->Key < Key)
			Cur = Cur->Right;
		else
			Cur = Cur->Left;
	}
	if (Prev == 0)
		Root = New;
	else
	if (Prev->Key < Key)
		Prev->Right = New;
	else
		Prev->Left = New;
	return true;
}

bool CBinTree::Find(int Key)
{
	CNode* Cur = Root;
	while (Cur != 0)
	if (Cur->Key < Key)
		Cur = Cur->Right;
	else
	if (Cur->Key > Key)
		Cur = Cur->Left;
	else
		return true;
	return false;
}

bool CBinTree::Solve(bool Flg)
{
	int c, l;
	Max = -1;
	KeyMax = -1;
	Going(Root, c, l);
	if (Flg)
	{
		bool Fl = (CountMax % 2 == 0);
		CountMax = (CountMax + 1) / 2;
		FindDel(Root);
		if (Fl)
		{
			KeyMax = -1;
			return false;
		}
	}
	return true;
}

void CBinTree::Going(CNode* Cur, int &Count, int &Length)
{
	if (Cur == 0)
	{
		Count = 0;
		Length = 0;
		return;
	}

	Cur->Selected = false;

	Going(Cur->Left, Cur->LeftCount, Cur->LeftLength);
	Going(Cur->Right, Cur->RightCount, Cur->RightLength);

	Count = Cur->LeftCount + Cur->RightCount + 1;

	Length = Cur->LeftLength;
	if (Cur->RightLength > Length)
		Length = Cur->RightLength;
	Length++;

	int m = abs(Cur->LeftLength - Cur->RightLength);

	if (m > Max)
	{
		Max = m;
		CountMax = 0;
	}

	if (m == Max)
		CountMax++;
}

bool CBinTree::Delete(int Key)
{
	if (!Find(Key))
		return false;
	CNode* Cur = Root;
	CNode* Prev = 0;
	while (Cur->Key != Key)
	{
		Prev = Cur;
		if (Cur->Key < Key)
			Cur = Cur->Right;
		else
			Cur = Cur->Left;
	}
	if (Cur->Left == 0 && Cur->Right != 0)
	{
		if (Prev == 0)
			Root = Cur->Right;
		else
		if (Prev->Key < Key)
			Prev->Right = Cur->Right;
		else
			Prev->Left = Cur->Right;
	}
	else
	if (Cur->Right == 0)
	{
		if (Prev == 0)
			Root = Cur->Left;
		else
		if (Prev->Key < Key)
			Prev->Right = Cur->Left;
		else
			Prev->Left = Cur->Left;
	}
	else
	{
		CNode* Del = Cur;
		Prev = Cur;
		Cur = Cur->Right;
		while (Cur->Left != 0)
		{
			Prev = Cur;
			Cur = Cur->Left;
		}
		if (Prev->Key < Cur->Key)
			Prev->Right = Cur->Right;
		else
			Prev->Left = Cur->Right;
		Del->Key = Cur->Key;
	}
	Cur->Left = 0;
	Cur->Right = 0;
	delete Cur;
	return true;
}

void CBinTree::FindDel(CNode* Cur)
{
	if (Cur == 0)
		return;
	FindDel(Cur->Left);
	if (abs(Cur->LeftLength - Cur->RightLength) == Max)
	{
		CountMax--;
		Cur->Selected = true;
	}
	if (CountMax == 0)
	{
		KeyMax = Cur->Key;
		CountMax--;
	}
	FindDel(Cur->Right);
}

int CBinTree::GetDelKey()
{
	return KeyMax;
}

void CBinTree::LoadFromFile(const char* FileName)
{
	Clear();
	ifstream fin(FileName);
	int k;
	while (!fin.eof())
	{
		fin >> k;
		Add(k);
	}
	fin.close();
}

void CBinTree::SaveToFile(const char* FileName)
{
	ofstream fout(FileName);
	OutPut(fout, Root);
	fout.close();
}

void CBinTree::Clear()
{
	delete Root;
	Root = 0;
}

void CBinTree::OutPut(std::ofstream& fout, CNode* Node)
{
	if (Node == 0)
		return;
	fout << Node->Key << std::endl;
	OutPut(fout, Node->Left);
	OutPut(fout, Node->Right);
}


int main()
{
	CBinTree Tree;

	Tree.LoadFromFile("In.txt");
	Tree.Solve(true);
	Tree.Delete(Tree.GetDelKey());
	Tree.SaveToFile("Out.txt");

	return 0;
}

