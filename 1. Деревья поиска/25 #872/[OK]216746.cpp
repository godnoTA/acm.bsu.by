#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>
#include <fstream>
#include<iostream>
#include<stdio.h>
#include<fstream>
#include <string>
#include<list>
#include<iterator>
using namespace std;
int m = 1, max = 0, min = -99999, tekmax;
class Tree
{
	
	struct TreeItem {
		int Info;
		TreeItem *RSon;
		TreeItem *LSon;
		TreeItem *Father;
		int metka;
		TreeItem() { LSon = RSon = Father = NULL; }
	};
	TreeItem *Root;
public:
	Tree()
	{
		Root = NULL;
	}
	bool NormRoot()
	{
		if (Root != NULL)
			return true;
		else
			return false;
	}
	bool Find(int, TreeItem*&);
	
	bool InsertEl(int);
	void walk() const;
	void walk2() const;
	void walk1(void Func(int&)) const;
	bool DeleteEl(int);
	void walk(TreeItem *Root) const;
	void walk2(TreeItem *Root) const;
	void Fuu(TreeItem *Root) const;
	void Fuuu(TreeItem *Root) const;
	void walk1(TreeItem *Root) const;
	
	void EraseNode(TreeItem* &);
	void Erase();
	virtual ~Tree()
	{
		Erase();
	}
protected:
	void DeleteTree(TreeItem *);
};
ifstream InputFile("tst.in", ios::in);
ofstream OutFile("tst.out", ios::out);

void Tree::EraseNode(TreeItem* &s)
{
	TreeItem *q;
	if (s->LSon != NULL)
		q = s->LSon;
	else
		q = s->RSon;
	if (q != NULL)
		q->Father = s->Father;
	if (s->Father == NULL)
		Root = q;
	else
		if ((s->Father)->LSon == s)
			(s->Father)->LSon = q;
		else
			(s->Father)->RSon = q;
	delete s; s = NULL;
}
void Print(int & a)
{
	OutFile<<a<<endl;
}




bool Tree::DeleteEl(int e)
{
	TreeItem*s;
	TreeItem*q;
	if (!Find(e, s))
		return false;
	else
	{
		if ((s->LSon != NULL) && (s->RSon != NULL))
		{
			q = s->RSon;
			while (q->LSon != NULL)
				q = q->LSon;
			s->Info = q->Info;
			EraseNode(q);
		}
		else
			EraseNode(s);
		return true;
	}
}
void Tree::Erase()
{
	DeleteTree(Root);
	Root = NULL;
}

void Tree::DeleteTree(TreeItem *t)
{
	if (t != NULL)
	{
		DeleteTree(t->LSon);
		DeleteTree(t->RSon);
		delete t;
	}
}
bool Tree::InsertEl(int e)
{
	int ch = 0;
	TreeItem* s = new TreeItem;
	if (Find(e, s))
		return false;
	if (Root == NULL)
	{
		TreeItem *p = new TreeItem;
		Root = p;
		p->Info = e;
		p->Father = NULL;
		return true;
	}
	else
	{
		TreeItem *p = new TreeItem;
		p = Root;
		TreeItem *q = new TreeItem;
		q->Info = e;
		for (; ; )
		{
			if (p->Info>e)
			{
				if (p->LSon == NULL)
				{
					p->LSon = q;
					q->Father = p;
					return true;
				}
				else
				{
					p = p->LSon;
				}
			}
			else
			{
				if (p->RSon == NULL)
				{
					q->Father = p;
					p->RSon = q;
					return true;
				}
				else
				{
					p = p->RSon;
				}
			}
		}
	}
}
bool Tree::Find(int e, TreeItem* &t)
{
	if (Root == NULL)
	{
		t = NULL;
		return false;
	}
	t = Root;
	for (;;)
	{
		if (t->Info == e)
		{
			return true;
		}
		if (t->Info>e)
		{
			if (t->LSon == NULL)
				return false;
			t = t->LSon;
		}
		else
		{
			if (t->RSon == NULL)
				return false;
			t = t->RSon;
		}
	}
}
void Tree::walk() const
{

	if (Root != NULL)
	{
		Print(Root->Info);
		walk(Root->LSon);
		walk(Root->RSon);
	}
}
void Tree::walk(TreeItem *Root) const
{
	if (Root == NULL)
	{
		return;
	}
	Print(Root->Info);
	walk(Root->LSon); 
	walk(Root->RSon);
}
list<int> l1;
void Tree::walk2() const
{

	if (Root != NULL)
	{
		
		walk2(Root->LSon);
		walk2(Root->RSon);
		Fuuu(Root);
	}
}
void Tree::walk2(TreeItem *Root) const
{
	if (Root == NULL)
	{
		return;
	}
	
	walk2(Root->LSon);
	walk2(Root->RSon);
	Fuuu(Root);
}

void Tree::Fuuu(TreeItem *Root) const
{
	if (Root->RSon == NULL&& Root->LSon == NULL) {
		if (max == 0) {
			l1.push_back(Root->Info);
		}
	}
	else if (Root->RSon != NULL&& Root->LSon != NULL) {
		if (abs(Root->LSon->metka - Root->RSon->metka )== max) {
			l1.push_back(Root->Info);
		}
	}
	else if (Root->RSon != NULL&& Root->LSon == NULL) {
		if ((Root->RSon->metka) == max) {
			l1.push_back(Root->Info);
		}
	}
	else if (Root->RSon == NULL&& Root->LSon != NULL) {
		if (Root->LSon->metka==max)
		{
			l1.push_back(Root->Info);
		}
	}

}



void Tree::walk1(void Func(int&)) const
{

	if (Root != NULL)
	{
		
		walk1(Root->LSon);
		walk1(Root->RSon);
		Fuu(Root);
		
		//Func(Root->Info);
		//Func(Root->metka);
		
	}
}

void Tree::walk1(TreeItem *Root) const
{
	if (Root == NULL)
	{
		return;
	}
	
	walk1(Root->LSon);
	walk1(Root->RSon);
	Fuu(Root);
	//Print(Root->Info);
	//Print(Root->metka);
}
void Tree::Fuu(TreeItem *Root) const
{
	if (Root->RSon == NULL&& Root->LSon == NULL) {
		Root->metka = 1;
		tekmax = 0;
	}
	else if (Root->RSon == NULL&& Root->LSon != NULL) {
		Root->metka = (Root->LSon->metka + 1);
		tekmax = Root->LSon->metka;
		
	}
	else if (Root->RSon != NULL&& Root->LSon == NULL) {
		Root->metka = (Root->RSon->metka + 1);
		tekmax =Root->RSon->metka;
	}
	else if (Root->RSon != NULL&& Root->LSon != NULL) {
		Root->metka = (Root->RSon->metka + Root->LSon->metka) + 1;
		tekmax =abs( Root->LSon->metka - Root->RSon->metka);
	}
	//cout<<(Root->Info)<<"столько: ";
	//cout << tekmax<<endl;
	if (tekmax > max)
		max = tekmax;

}
Tree a;



int main()
{
	
	string s;
	int v;
	
	if (!InputFile)
	{
		cout << "file does not open" << endl;
		return 1;
	}
	
	while (getline(InputFile,s)) {
		if (s != "") {
			v = atoi(s.c_str());
			a.InsertEl(v);
		}
	}
	
	
	a.walk1(Print);
	a.walk2();
	int size;
	//copy(l1.begin(), l1.end(), ostream_iterator<int>(cout, " "));
	cout << max;
	size = l1.size();
	if(size==1)
		a.DeleteEl(l1.front());
	if (size != 0) {
		if (size % 2 != 0) {
			l1.sort();
			while (l1.size() != 1) {
				l1.pop_front();
				l1.pop_back();
			}
			a.DeleteEl(l1.front());
		}
		
	}
	
	a.walk();
	InputFile.close();
	OutFile.close();
	return 0;
}