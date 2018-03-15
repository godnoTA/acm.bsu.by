#pragma comment(linker, "/STACK:16777216")
#include <iostream>
#include <fstream>
#include <math.h>
#include <vector>
#include <algorithm>
#include "limits.h"
using namespace std;

ifstream inStream("in.txt", ios::in);
ofstream outStream("out.txt", ios::out);

struct TreeItem
{
	int data;
	int info = 1;
	int nl = 0, nr = 0;
	TreeItem *father, *lson, *rson;
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
			m = ((q->data) < (m->data)) ? m->lson : m->rson;
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
				m->nl++;
				m = m->lson;

			}
			else
			{
				m->nr++;
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
	float Height(PTreeItem m)
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
	int Across(int h)
	{
		vector<PTreeItem> n1, n2;
		n1.push_back(Root);
		int lm;
		for (int i = 0; i< h; i++)
		{
			lm = 0;;
			PTreeItem k = n1.back();
			while (!n1.empty())
			{

				if (k->lson != NULL)
				{
					n2.push_back(k->lson);
				}
				if (k->rson != NULL)
				{
					n2.push_back(k->rson);
				}
				n1.pop_back();
				if (!n1.empty())
					k = n1.back();
			}
			n1 = n2;
			n2.clear();
		}
		TreeItem* m = n1.front();
		vector<int> res;
		for (int i = 0; i < n1.size(); i++)
		{
			if (n1[i]->nr < n1[i]->nl)
				res.push_back(n1[i]->data);
		}
		sort(res.begin(), res.end());
		if (res.size() == 0)
			return INT_MIN;
		if (res.size() % 2 == 1)
			return res[(res.size()) / 2];
		else return INT_MIN;
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
	float height = tree.Height(tree.GetRoot()) - 1;
	if (height>0)
	{
		int halfHeight = ceil(height / 2);
		int l = tree.Across(halfHeight);
		if (l != INT_MIN)
			tree.DeleteTreeItem(l);
	}
	tree.Show(tree.GetRoot());
	outStream.close();
	return 0;
}