#include <fstream>
#include <iostream>

using namespace std;

ofstream fout("out.txt");

class TreeNode
{
public:
	long long value;
	TreeNode* lSon;
	TreeNode* rSon;
	TreeNode* father;
	long h;
	long long p;
	TreeNode(long long x);
};
TreeNode::TreeNode(long long x)
{
	value = x;
	lSon = NULL;
	rSon = NULL;
	father = NULL;
	h = 0;
	p = 0;
}

class Tree
{
public:
	TreeNode* root;
	Tree();
	void insert(long long x);
	void Delete(long long a);
	TreeNode* find(long long x);
	void ReverseBypass();
	void Print();
	void delRootP();
private:
	TreeNode* rootP = NULL;
	long long maxP = LONG_MIN;
	long maxN = 0;
	void Delete(TreeNode* x);
	void ReverseBypass(TreeNode* x);
	void Print(TreeNode* x);
};

Tree::Tree()
{
	root = NULL;
}

TreeNode* Tree::find(long long x)
{
	TreeNode* a = root;
	while (a != NULL)
	{
		if (a->value == x)
			return a;
		if (x < a->value)
			a = a->lSon;
		else
			a = a->rSon;
	}
	return a;
}

void Tree::insert(long long x)
{
	TreeNode* n = new TreeNode(x);
	if (root == NULL)
		root = n;
	else
	{
		if (find(x) == NULL)
		{
			TreeNode* a = root;
			TreeNode* b = a;
			while (a != NULL)
			{
				b = a;
				if (x < a->value)
					a = a->lSon;
				else
					a = a->rSon;
			}
			n->father = b;
			if (x < b->value)
				b->lSon = n;
			else
				b->rSon = n;
		}
	}
}

void Tree::Print(TreeNode* x)
{
	if (x != NULL)
	{
		fout << x->value << endl;
		Print(x->lSon);
		Print(x->rSon);
	}
}

void Tree::Print()
{
	Print(root);
}

void Tree::Delete(TreeNode* x)
{
	if (x->lSon == NULL && x->rSon == NULL)			//лист
		if (x != root)
		{
			if (x->father->lSon == x)
				x->father->lSon = NULL;
			else
				x->father->rSon = NULL;
		}
		else
			root = NULL;
	else if (x->lSon == NULL)						//нет левого поддерева
	{
		if (x != root)
		{
			x->rSon->father = x->father;
			if (x->father->lSon == x)
				x->father->lSon = x->rSon;
			else
				x->father->rSon = x->rSon;
		}
		else
		{
			x->rSon->father = NULL;
			root = x->rSon;
		}
	}
	else if (x->rSon == NULL)						//нет правого поддерева
	{
		if (x != root)
		{
			x->lSon->father = x->father;
			if (x->father->rSon == x)
				x->father->rSon = x->lSon;
			else
				x->father->lSon = x->lSon;
		}
		else
		{
			x->lSon->father = NULL;
			root = x->lSon;
		}
	}
	else											//всё есть
	{
		TreeNode* a = x->rSon;
		while (a->lSon != NULL)
			a = a->lSon;
		x->value = a->value;
		Delete(a);
		return;
	}		
}

void Tree::Delete(long long a)
{
	TreeNode* x = find(a);
	if (x != NULL)
		Delete(x);
}

int max(long long a, long long b)
{
	if (a >= b)
		return a;
	else
		return b;
}

void Tree::ReverseBypass(TreeNode* x)
{
	if (x != NULL)
	{
		ReverseBypass(x->lSon);
		ReverseBypass(x->rSon);
		long long N, P;
		if (x->rSon == NULL && x->lSon == NULL)
		{
			x->h = 0;
			x->p = x->value;
			N = x->h;
			P = x->p;
		}
		else if (x->rSon == NULL)
		{
			x->h = x->lSon->h + 1;
			x->p = x->lSon->p + x->value;
			N = x->h;
			P = x->p;
		}
		else if (x->lSon == NULL)
		{
			x->h = x->rSon->h + 1;
			x->p = x->rSon->p + x->value;
			N = x->h;
			P = x->p;
		}
		else
		{
			if (x->rSon->h > x->lSon->h)
			{
				x->h = x->rSon->h + 1;
				x->p = x->rSon->p + x->value;
			}
			else if (x->rSon->h < x->lSon->h)
			{
				x->h = x->lSon->h + 1;
				x->p = x->lSon->p + x->value;
			}
			else
			{
				x->h = x->lSon->h + 1;
				x->p = max(x->rSon->p, x->lSon->p) + x->value;
			}
			N = x->lSon->h + x->rSon->h + 2;
			P = x->rSon->p + x->lSon->p + x->value;
		}
		if (N > maxN)
		{
			maxN = N;
			maxP = P;
			rootP = x;
		}
		else if (N == maxN)
			if (P >= maxP)
			{
				maxP = P;
				rootP = x;
			}
	}
}

void Tree::ReverseBypass()
{
	ReverseBypass(root);
}

void Tree::delRootP()
{
	ReverseBypass();
	if (maxN % 2 != 0)
		Delete(rootP);
	else
	{
		TreeNode* a = rootP;
		long dH = maxN / 2;
		while (a->h != dH)
		{
			if (a->rSon != NULL && a->lSon != NULL)
			{
				if (a->lSon->h > a->rSon->h)
					a = a->lSon;
				else
					a = a->rSon;
			}
			else if (a->lSon == NULL)
				a = a->rSon;
			else if (a->rSon == NULL)
				a = a->lSon;
		}
		if (a != rootP)
			Delete(a);
		Delete(rootP);
	}
}

int main()
{
	ifstream fin("in.txt");
	Tree *t = new Tree();
	if (fin.is_open())
	{
		long long n;
		while (fin >> n)
		{
			t->insert(n);
		}
		t->delRootP();
		t->Print();
	}
	else
		cout << "File can't be opened\n";
	fin.close();
	fout.close();
	return 0;
} 