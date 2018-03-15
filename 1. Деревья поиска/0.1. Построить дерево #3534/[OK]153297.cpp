#include <fstream>
#include <iostream>

using namespace std;

ofstream fout("output.txt");

class TreeNode
{
public:
	int value;
	TreeNode* lSon;
	TreeNode* rSon;
	TreeNode* father;
	TreeNode(int x);
};
TreeNode::TreeNode(int x)
{
	value = x;
	lSon = NULL;
	rSon = NULL;
	father = NULL;
}

class Tree
{
public:
	TreeNode* root;
	Tree();
	void insert(int x);
	void Delete(TreeNode* x);
	void Delete(int a);
	TreeNode* find(int x);
	void Print();
private:
	void Print(TreeNode* x);
};

Tree::Tree()
{
	root = NULL;
}

TreeNode* Tree::find(int x)
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

void Tree::insert(int x)
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
			x->rSon = root;
		}
	}
	else if (x->rSon == NULL)						//нет правого поддерева
	{
		if (x != root)
		{
			x->lSon->father = x->father;
			if (x->father->rSon == x)
				x->father->lSon = x->lSon;
			else
				x->father->rSon = x->lSon;
		}
		else
		{
			x->lSon->father = NULL;
			x->lSon = root;
		}
	}
	else											//всё есть, блин
	{
		TreeNode* a = x->rSon;
		while (a->lSon != NULL)
			a = a->lSon;
		x->value = a->value;
		Delete(a);
		return;
	}		
}

void Tree::Delete(int a)
{
	TreeNode* x = find(a);
	if (x != NULL)
		Delete(x);
}

int main()
{
	ifstream fin("input.txt");
	Tree *t = new Tree();
	if (fin.is_open())
	{
		int n;
		while (fin >> n)
		{
			t->insert(n);
		}
		t->Print();
	}
	else
		cout << "File can't be opened\n";
	fin.close();
	fout.close();
	return 0;
}