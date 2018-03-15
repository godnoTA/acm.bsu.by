#include <iostream>
#include <fstream>
#include <math.h>
#include <vector>

using namespace std;

ifstream inStream("input.txt", ios::in);
ofstream outStream("output.txt", ios::out);

struct TreeItem
{
	int data;
	TreeItem *father, *lson, *rson;
	int info = 1;
};

typedef TreeItem *PTreeItem;

class Tree
{
private:
	PTreeItem Root;
public:
	Tree() : Root(NULL) {}

	void InsertItem(int inf)
	{
		if (findItem(inf))
		{
			return;
		}
		PTreeItem q = new TreeItem;
		q->data = inf;
		PTreeItem y = NULL, m = Root;
		
		while (m != NULL)
		{
			y = m;
			if ((q->data) < (m->data))
			{
				m = m->lson;
			}
			else
			{
				m = m->rson;
			}
		}
		q->father = y;
		if (y == NULL)
		{
			Root = q;
		}
		else
		{
			if ((q->data) < (y->data))
			{
				y->lson = q;
			}
			else
			{
				y->rson = q;
			}
		}
		q->lson = NULL;
		q->rson = NULL;
	}

	PTreeItem findItem(int inf)
	{
		PTreeItem m = Root;

		while ((m != NULL) && (m->data != inf))
		{
			if (inf < (m->data))
			{
				m = m->lson;
			}
			else
			{
				m = m->rson;
			}
		}
		return m;
	}
	PTreeItem GetRoot()
	{
		return Root;
	}

	void Show(PTreeItem m)
	{
		if (m == NULL)
		{
			return;
		}
		outStream << (m->data) << "\n";
		Show(m->lson);
		Show(m->rson);
	}

};

int main()
{
	Tree tree;
	int ch;
	while (inStream >> ch)
	{
		tree.InsertItem(ch);
	}
	inStream.close();
	tree.Show(tree.GetRoot());
	outStream.close();
	return 0;
}