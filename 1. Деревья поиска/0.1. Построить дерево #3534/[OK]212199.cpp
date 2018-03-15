#include <iostream> 
#include <fstream> 
#include <set> 
#include <iterator> 
using namespace std;

ofstream fout("output.txt"); 
ifstream fin("input.txt"); 

struct TreeItem{
			int Info;
			TreeItem* Father;
			TreeItem* LSon;
			TreeItem* RSon;
			TreeItem(){LSon=RSon=NULL;}
		};
		TreeItem* Root=NULL;
		long long SUMMA=0;
		bool find(TreeItem* R,int a, TreeItem* &t)
		{
			{
				if (R == NULL)
				{
					t = NULL;
					return false;
				}
				t = R;
				for (;;)
				{
					if (t->Info == a)
						return true;
					if (t->Info > a)
					{
						if (t->LSon == NULL)
							return false;
						t = t->LSon;
					}
					else
					{
						if (t->RSon == NULL)
							return false;
						t = t->RSon;
					}
				}
			}
		}
		bool Insert(TreeItem* &R,int info)
		{
			TreeItem *s,*q;
			if (find(R,info,s))
				return false;
			q = new TreeItem;
			q->Info = info;
			if (s == NULL)
			{
				R = q;
				q->Father = NULL;
			}
			else
			{
				q->Father = s;
				if (s->Info < info)
					s->RSon = q;
				else
					s->LSon = q;
			}
		}
		void Print(TreeItem* &s)
			{fout << s->Info<<endl;}

		void post_order_walk_p(TreeItem* s,void vizit(TreeItem* &s))
		{
			if (s != NULL)
			{
				vizit(s);
				post_order_walk_p(s->LSon,vizit);
				post_order_walk_p(s->RSon,vizit);
							
			}
		}

		void post_order_walk(TreeItem* s,void vizit(TreeItem* &s))
		{
			if (s != NULL)
			{
				
				post_order_walk(s->LSon,vizit);
				post_order_walk(s->RSon,vizit);
				vizit(s);
							
			}
		}
		void post_order_walk_Sum(TreeItem* s)
		{

			if (s != NULL)
			{
				post_order_walk_Sum(s->LSon);
				post_order_walk_Sum(s->RSon);
				SUMMA=SUMMA+s->Info;
			}
		}

		void EraseItem(TreeItem *&s)
		{
			if (s->Father != NULL)
			{

				if ((s->Father)->LSon == s)
					(s->Father)->LSon = NULL;
				else
					(s->Father)->RSon = NULL;
			}
			else 
				Root=NULL; 
			delete s;
			s = NULL;
		}
		void post_order(TreeItem* R,void vizit(TreeItem* &s))
		{
			if (R == NULL)
			{
				cout << "Empty tree" << endl;
				return;
			}
			post_order_walk(R, vizit);
		}
		void Destroy(TreeItem * &R)
		{

			post_order_walk(R, EraseItem);
		}


int main() { 
	TreeItem *T = NULL; 
	int x = 0; 
	int y = 0; 
	while (!fin.eof()) { 
		fin >> y;
		Insert(T,y);
	} 
	fin.close(); 
	post_order_walk_p(T,Print);
	fout.close(); 
	Destroy(T); 
	return 0;
}