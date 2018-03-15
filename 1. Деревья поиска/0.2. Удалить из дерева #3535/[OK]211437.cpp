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
		if (t == root && !t->l && !t->r)
		{
			delete t;
			root = NULL;
			return true;
		}
		if (!t->r)
		{
			if (t != root)
			{
				if (t->f->l == t)
				{
					t->f->l = t->l;
					if (t->l){
						t->l->f = t->f;
					}
				}
				else
				{
					t->f->r = t->l;
					if (t->l)
						t->l->f = t->f;
				}
			}
			else
			{
				root = t->l;
				t->l->f = NULL;
			}
			delete t;
			return true;
		}
		if (!t->l)
		{
			if (t != root)
			{
				if (t->f->l == t)
				{
					t->f->l = t->r;
					if (t->r)
						t->r->f = t->f;
				}
				else
				{
					t->f->r = t->r;
					if (t->r)
						t->r->f = t->f;
				}
			}
			else
			{
				root = t->r;
				t->r->f = NULL;
			}
			delete t;
			return true;
		}
		treeinfo *s = t->r;
		while (s->l)
			s = s->l;
		t->n = s->n;
		if (s->f->l == s)
			if (!s->r)
				s->f->l = NULL;
			else
			{
				s->f->l = s->r;
				s->r->f = s->f;
			}
		else
			if (!s->r)
				s->f->r = NULL;
			else
			{
				s->f->r = s->r;
				s->r->f = s->f;
			}
		delete s;
		return true;
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
	treeinfo* find(int c)
	{
		treeinfo* t = root;
		while (t)
		{
			if (t->n == c)
				break;
			if (t->n > c)
				t = t->l;
			else
				t = t->r;
		}
		return t;
	}

	bool deleteKey(int c)
	{
		treeinfo *t = find(c);
		if (!t)
			return false;
		return deleteleaf(t);
	}
};

int main()
{
	if (!fin)
		return 0;
	long delk, c;
	Tree tree;
	fin >> delk;
	while (!fin.eof())
	{
		fin >> c;
		tree.insert(c);
	}
	tree.deleteKey(delk);
	tree.left();
	//system("pause");
	return 0;
}