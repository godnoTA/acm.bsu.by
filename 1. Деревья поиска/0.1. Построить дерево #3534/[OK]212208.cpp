#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <fstream>

using namespace std;

class TTree {

	struct TNode {
		long Key;
		TNode* Left = nullptr;
		TNode* Right = nullptr;
		TNode* Father = nullptr;
	};
	TNode* Root;

public:
	TTree() : Root(0) {}

	~TTree() {
		DestroyNode(Root);
	}
	void Insert(long key);
	void walk(ostream&);
	void post_walk(TNode*, ostream &);
	static void DestroyNode(TNode* node) {
		if (node) {
			DestroyNode(node->Left);
			DestroyNode(node->Right);
			delete node;
		}
	}

};

void TTree::Insert(long n)
{
	TNode *t = Root;
	TNode *tmp = new TNode;
	tmp->Key = n;
	tmp->Father = nullptr;
	tmp->Left = nullptr;
	tmp->Right = nullptr;
	if (!Root)
	{
		Root = tmp;
		return;
	}
	while ((t->Left != nullptr && t->Key > n) || (t->Right != NULL && t->Key < n) || t->Key == n)
	{
		if (t->Key == n)
			return;
		if (t->Key < n)
			t = t->Right;
		else
			t = t->Left;
	}
	if (t->Left == NULL && t->Key > n)
	{
		t->Left = tmp;
		tmp->Father = t;
	}
	if (t->Right == NULL && t->Key < n)
	{
		t->Right = tmp;
		tmp->Father = t;
	}
}

void TTree::walk(ostream &out)
{
	post_walk(Root, out);
}

void TTree::post_walk(TTree::TNode *t, ostream &out)
{
	if (!t)
		return;
	out << t->Key << "\n";
	post_walk(t->Left, out);
	post_walk(t->Right, out);
}

ifstream fin("input.txt");
ofstream fout("output.txt");

int main()
{
	TTree MyTree;
	long x;
	fin >> x;
	while (!fin.eof())
	{
		MyTree.Insert(x);
		fin >> x;
	}

	MyTree.walk(fout);

	return(0);
}