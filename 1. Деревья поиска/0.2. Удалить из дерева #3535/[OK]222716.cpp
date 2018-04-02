#include <iostream>
#include <fstream>
#include <vector>
#include <list>
#define TRUE 1
#define FALSE 0

using namespace std;

struct  node
{
  int Key;
  int Count;
  node *Left;
  node *Right;
};

class TREE
{
  private:
    node *Tree;
    node  *Res;
    int B;
    void Search (int, node**);
    void Delete_1 (node**,node**);
  public:
    TREE() { Tree = NULL;}
    node** GetTree() {return &Tree;}
    void  BuildTree (vector<int>vector);
    void Vyvod (node**,list<int>& list);
    void Delete (node**, int);
};

void main ()
{
	ifstream fin ("input.txt");
	ofstream fout ("output.txt");
	setlocale(LC_ALL,".1251");
	TREE A;
	int el;
	int udalit;
	vector<int> vector;
	fin >> udalit;
	while(fin >> el)
		vector.push_back(el);
	list<int> list1;
	A.BuildTree (vector); 

 
  A.Delete (A.GetTree(),udalit);  
  A.Vyvod (A.GetTree(),list1);
  list<int>::iterator it;
  int d, y;
  d = list1.size();
  for(int i = 0; i < d; i++)
  {
	  if (i == d - 1)
	  {
		y = list1.front();
		list1.pop_front();
		fout << y;
	  }
	  else
	  {
		y = list1.front();
		list1.pop_front();
		fout << y << endl;
	  }
  }
 
}

void TREE::BuildTree (vector<int> vector)
{

  for(int i = 0; i < vector.size(); i++)
    { Search (vector[i],&Tree); }
}

void TREE::Vyvod (node **w,list<int>& list)
{
  int i;

  if  (*w!=NULL)
  {
	list.push_back((**w).Key);
    Vyvod (&((**w).Left),list);
	Vyvod (&((**w).Right),list);
  }
}


void TREE::Search (int x,node **p)
{
  if  (*p==NULL)
  { 
    *p = new(node);
    (**p).Key = x;     (**p).Count = 1;
    (**p).Left = (**p).Right = NULL;
  }
  else
  if  (x<(**p).Key) Search (x,&((**p).Left));
  else
    if  (x>(**p).Key) Search (x,&((**p).Right));
    else  (**p).Count += 1;
}


void TREE::Delete (node **p,int k)
{
  node *q;

  if  (*p==NULL) 
	  cout<<"net";
  else
	 if  (k<(**p).Key) Delete (&((**p).Left),k);
	 else
		if  (k>(**p).Key) Delete (&((**p).Right),k);
		else
		  {
                    q = *p;
                    if  ((*q).Right==NULL) {*p = (*q).Left; delete q;}
                    else
                     if  ((*q).Left==NULL) { *p = (*q).Right; delete q; }
                     else  Delete_1 (&((*q).Right),&q);
		  }
}

void TREE::Delete_1 (node **r,node **q)
{
  node *s;

  if  ((**r).Left==NULL)
  {
    (**q).Key = (**r).Key; (**q).Count = (**r).Count;
    *q = *r;
    s = *r; *r = (**r).Right; delete s;
  }
  else  Delete_1 (&((**r).Left), q);
}