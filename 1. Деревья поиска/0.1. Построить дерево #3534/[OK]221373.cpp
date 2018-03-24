//#include "stdafx.h"
#include <iostream>
#pragma warning (disable:4996)
using namespace std;

FILE *fin, *fout;
class Tree 
{
	struct TreeItem{
		int info;
		TreeItem *Father, *rSon, *lSon;
		TreeItem() {
			lSon = rSon = Father = NULL;
		}
	};
	TreeItem *Root;
public:
	bool NormRoot()
	{
		if (Root != NULL)
			return true;
		else
			return false;
	}
	bool InsertEl(int);
	void Erase();
	void walk(void Func(int&)) const;
	void walk(TreeItem *) const;
	bool Find(int, TreeItem*&);

	Tree() {
		Root = NULL;
	}
	Tree(const Tree& L);
	virtual ~Tree() {
		Erase();
	}

private:
	TreeItem* Clone(TreeItem*);
	void DeleteTree(TreeItem*);
};
Tree::TreeItem* Tree::Clone(TreeItem* node)
{

	if (node == NULL)
		return NULL;
	TreeItem* newnode = new TreeItem;
	newnode->info = node->info;
	newnode->lSon = Clone(node->lSon);
	newnode->rSon = Clone(node->rSon);
	return newnode;

}
Tree::Tree(const Tree& L)
{
	Root = Clone(L.Root);
}
void Tree::Erase() {
	DeleteTree(Root);
	Root = NULL;
}
void Tree::DeleteTree(TreeItem *t)
{
	if (t != NULL)
	{
		DeleteTree(t->lSon);
		DeleteTree(t->rSon);
		delete t;
	}
}
int find(char * str)
{
	char * nstr;
	char * token = strtok(str, " \n");
	while (token != NULL)
	{
		int a = strtol(token, &nstr, 10);
		if (!strlen(nstr))
		{
			return a;
		}
		token = strtok(NULL, " \n");
	}
	return 10000000;
}
void print(int & a)
{
	fprintf(fout, "%i\n", a);
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
		if (t->info == e)
		{
			return true;
		}
		if (t->info>e)
		{
			if (t->lSon == NULL)
				return false;
			t = t->lSon;
		}
		else
		{
			if (t->rSon == NULL)
				return false;
			t = t->rSon;
		}
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
		p->info = e;
		p->Father = NULL;
		return true;
	}
	else
	{
		TreeItem *p = new TreeItem;
		p = Root;
		TreeItem *q = new TreeItem;
		q->info = e;
		for (; ; )
		{
			if (p->info>e)
			{
				if (p->lSon == NULL)
				{
					p->lSon = q;
					q->Father = p;
					return true;
				}
				else
				{
					p = p->lSon;
				}
			}
			else
			{
				if (p->rSon == NULL)
				{
					q->Father = p;
					p->rSon = q;
					return true;
				}
				else
				{
					p = p->rSon;
				}
			}
		}
	}
}
void Tree::walk(TreeItem *Root) const
{
	if (Root == NULL)
	{
		return;
	}
	print(Root->info);
	walk(Root->lSon);
	
	walk(Root->rSon);
}

void Tree::walk(void Func(int&)) const
{

	if (Root != NULL)
	{
		Func(Root->info);
		walk(Root->lSon);
		
		walk(Root->rSon);
	}
}
Tree a;
void ins(char in, char *str)
{
	int val = find(str);
	if (val != 10000000)
	{
		int rval = val;
		a.InsertEl(rval);/*
			fprintf(fout, "%i:inserted.\n", rval);
		else
			fprintf(fout, "%i: not inserted.\n", rval);*/
	
	}
	else
		fprintf(fout, "Uncorrect input.\n");
}


int main()
{

	
	const int N = 1000;
	try {
		if(!(fin = fopen("input.txt", "r")))
			throw "Infile can not be open";
		if (!(fout = fopen("output.txt", "w")))
			throw "Outfile can not be open";
		char cur_str[N];
		if (fgets(cur_str, N - 1, fin) == NULL)
			throw "File is empty";
		while (!feof(fin))
		{
			char check = cur_str[0];

			ins(check, cur_str);

			fgets(cur_str, N - 1, fin);
		}
		if (a.NormRoot())
		{
			a.walk(print);
		}
		else
			fprintf(fout, "Your tree is empty.\n");
	}
	catch (char *c)
	{
		cout << c << endl;
	}
	
    return 0;
}