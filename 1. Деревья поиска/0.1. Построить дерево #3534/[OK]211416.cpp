#include <iostream>
#include <fstream>
#include <map>

using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

class Tree
{
private:
	struct treeinfo
	{
		treeinfo *l, *r, *f;
		int n;
	};
	treeinfo *root;
public:
	Tree()
	{
		root = NULL;
	}
	bool insert(int c)
	{
		treeinfo *t = root;
		treeinfo *tmp = new treeinfo;
		tmp->n = c;
		tmp->l = NULL;
		tmp->r = NULL;
		tmp->f = NULL;
		if (!root)
		{
			root = tmp;
			return true;
		}
		while ((t->l != NULL && t->n > c) || (t->r != NULL && t->n < c) || t->n == c)
		{
			if (t->n == c)
				return false;
			if (t->n < c)
				t = t->r;
			else t = t->l;
		}
		if (t->l == NULL && t->n > c)
		{
			t->l = tmp;
			tmp->f = t;
		}
		if (t->r == NULL && t->n < c)
		{
			t->r = tmp;
			tmp->f = t;
		}
	}
	~Tree()
	{
		Erase();
	}
	void Erase()
	{
		deletetr(root);
		root = NULL;
	}
	void deletetr(treeinfo *tr)
	{
		if (!tr)
			return;
		deletetr(tr->l);
		deletetr(tr->r); 
	}
	bool deleteleaf(treeinfo* t)
	{
		if (!t->r)
		{
			t->l->f = t;
			if (!t->f)
			{
				if (t->f->l = t)
					t->f->l = t->l;
				else t->f->r = t->l;
			}
			delete t;
		}	
	}
	void leftObh(treeinfo *t)
	{
		if (t)
			fout << t->n << "\n";
		if (t->l)
			leftObh(t->l);
		if (t->r)
			leftObh(t->r);
	}
	void left()
	{
		treeinfo* t = root;
		leftObh(t);
	}
};

int main()
{
	if (!fin)
		return 0;
	long s = 0, c;
	int i = 0;
	Tree tree;
	while (!fin.eof())
	{
		fin >> c;
		tree.insert(c);
	}
	tree.left();
	//system("pause");
	return 0;
}