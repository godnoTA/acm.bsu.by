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
	double Height(PTreeItem m)
	{
		if (m == NULL)
		{
			return 0;
		}
		int leftBrothers = 0, rsonBrothers = 0;
		if (m->lson != NULL)
		{
			leftBrothers = Height(m->lson);
			m->info += m->lson->info;
		}
		if (m->rson != NULL)
		{
			rsonBrothers = Height(m->rson);
			m->info += m->rson->info;
		}
		return(leftBrothers>rsonBrothers ? leftBrothers + 1 : rsonBrothers + 1);
	}
	void DeleteTreeItem(int inf)
	{
		PTreeItem pointer = Root, father = NULL;

		while ((pointer != NULL) && (pointer->data != inf))
		{
			father = pointer;
			if (inf < (pointer->data))
			{
				pointer = pointer->lson;
			}
			else
			{
				pointer = pointer->rson;
			}
		}

		if (pointer != NULL)
		{
			PTreeItem removed = NULL;

			if ((pointer->lson == NULL) || (pointer->rson == NULL))
			{
				PTreeItem child = NULL;
				removed = pointer;

				if ((pointer->lson) != NULL)
				{
					child = pointer->lson;
				}
				else
				{
					if ((pointer->rson) != NULL)
					{
						child = pointer->rson;
					}
				}
				if (father == NULL)
				{
					Root = child;
				}
				else
				{
					if ((father->lson) == pointer)
					{
						father->lson = child;
					}
					else
					{
						father->rson = child;
					}
				}
			}
			else
			{
				PTreeItem mostlson = pointer->rson;
				PTreeItem mostlsonfather = pointer;

				while ((mostlson->lson) != NULL)
				{
					mostlsonfather = mostlson;
					mostlson = mostlson->lson;
				}

				pointer->data = mostlson->data;
				removed = mostlson;

				if ((mostlsonfather->lson) == mostlson && mostlson->rson == NULL)
				{
					mostlsonfather->lson = NULL;
				}
				else
				{
					if ((mostlsonfather->lson) != mostlson)
					{
						mostlsonfather->rson = mostlson->rson;
					}
					else
					{
						mostlsonfather->lson = mostlson->rson;
					}
				}
			}
			delete removed;
		}
	}
	void DeleteTree(PTreeItem m)
	{
		if (m == NULL)
		{
			return;
		}
		if (m->lson != NULL)
		{
			DeleteTree(m->lson);
		}
		DeleteTreeItem(m->data);
		if (m->rson != NULL)
		{
			DeleteTree(m->rson);
		}
	}
};

int main()
{
	Tree tree;
	int ch, forDel;
	if (inStream >> forDel)
	{
		while (inStream >> ch)
		{
			tree.InsertItem(ch);
		}
	}
	inStream.close();
	tree.DeleteTreeItem(forDel);
	tree.Show(tree.GetRoot());
	outStream.close();
	return 0;
}