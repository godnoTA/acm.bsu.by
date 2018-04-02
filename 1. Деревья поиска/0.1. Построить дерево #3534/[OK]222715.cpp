#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

struct Node
{
	int x;
	Node *l, *r;
};

void show(Node *&Tree, vector <int>& vector) {
	if (Tree != NULL)
	{
		vector.push_back(Tree->x);
		show(Tree->l, vector);
		show(Tree->r, vector);
	}
}


void del(Node *&Tree) {
	if (Tree != NULL)
	{
		del(Tree->l);
		del(Tree->r);
		delete Tree;
		Tree = NULL;
	}

}

void add_node(int x, Node *&MyTree)
{
	if (NULL == MyTree)
	{
		MyTree = new Node;
		MyTree->x = x;
		MyTree->l = MyTree->r = NULL;
	}

	if (x<MyTree->x)
	{
		if (MyTree->l != NULL) add_node(x, MyTree->l);
		else
		{
			MyTree->l = new Node;
			MyTree->l->l = MyTree->l->r = NULL;
			MyTree->l->x = x;
		}
	}

	if (x>MyTree->x)
	{
		if (MyTree->r != NULL)
			add_node(x, MyTree->r);
		else
		{
			MyTree->r = new Node;
			MyTree->r->l = MyTree->r->r = NULL;
			MyTree->r->x = x;
		}
	}
}

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	Node *Tree = NULL;
	int i;
	while (fin >> i)
		add_node(i, Tree);
	vector<int> vector;
	show(Tree, vector);
	for (int i = 0; i < vector.size(); i++)
		if (i == vector.size() - 1)
			fout << vector[i];
		else
			fout << vector[i] << endl;
	del(Tree);
	fin.close();
	fout.close();
	return 0;
}