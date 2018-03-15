#include <iostream>
#include <fstream>
using namespace std;

ofstream output("output.txt");

struct BinTree
{
   BinTree *Left;
   BinTree *Right; 
   long long t; 
};

void print(BinTree *&BT)
{
	if (BT!=NULL)
	{
	   output << BT->t << endl; 
	   print(BT->Left); 
	   print(BT->Right);
	}
}

void add(BinTree *&MyBinTree, long long t)
{
	if (MyBinTree==NULL)
	{
		MyBinTree=new BinTree; 
		MyBinTree->t=t;
		MyBinTree->Left=NULL;
		MyBinTree->Right=NULL; 
	}
	if (t < MyBinTree->t)
	{
		if (MyBinTree->Left!=NULL)
			add(MyBinTree->Left,t);
		else 
		{
			MyBinTree->Left=new BinTree;  
			MyBinTree->Left->Left=MyBinTree->Left->Right=NULL;
			MyBinTree->Left->t=t;
		}
	} 
	if (t > MyBinTree->t) 
	{
		if (MyBinTree->Right!=NULL) 
			add(MyBinTree->Right, t); 
		else 
		{
			MyBinTree->Right=new BinTree;  
			MyBinTree->Right->Left=MyBinTree->Right->Right=NULL;
            MyBinTree->Right->t=t; 
		}
	}        
} 
BinTree * Udalenie (BinTree *&MyBinTree, long long x)
{
   BinTree * N, *K;
   if (!MyBinTree)
	   return NULL;
   else if (x < MyBinTree->t)
      MyBinTree->Left = Udalenie(MyBinTree->Left, x);
   else if (x > MyBinTree-> t)
      MyBinTree->Right = Udalenie(MyBinTree->Right, x);
   else
   {
      N = MyBinTree;
      if (!MyBinTree->Right)
		  MyBinTree = MyBinTree->Left;
      else if (!MyBinTree->Left)
		  MyBinTree = MyBinTree->Right;
      else
      {
         K = MyBinTree->Right;
         if (K->Left)
         {
            while (K->Left->Left)
				K = K->Left;
            MyBinTree->t = K->Left->t;
            N = K->Left;
            K->Left = K->Left->Right;
         }
         else
         {
            MyBinTree->t = K->t;
            N = K;
            MyBinTree->Right = MyBinTree->Right->Right;
         }
      }
      free(N);
   }
   return MyBinTree;
}

int main()
{
	long long in;
	ifstream input("input.txt");	
	if(input == NULL )
        return 1;
	BinTree *MyBinTree=NULL; 
	input >> in;
	while(!input.eof())
	{
		long long Ch;
		input >> Ch;
		add(MyBinTree,Ch);     
    }
	Udalenie(MyBinTree,in);
	print(MyBinTree);   
    input.close();
	output.close();
    return 0;

}