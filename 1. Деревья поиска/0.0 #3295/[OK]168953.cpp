#include <iostream>
#include <fstream>
using namespace std;

long long Sum = 0;

struct BinTree
{
	BinTree *Left;
	BinTree *Right; 
    long long t; 
   
};

void Slozenie(BinTree *&BT)
{
	if (BT!=NULL)
	{
	   Slozenie(BT->Left); 
	   Sum+=BT->t; 
	   Slozenie(BT->Right);
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
int main()
{
	ifstream input("input.txt");
	ofstream output("output.txt");
	if(input == NULL )
        return 1;
	BinTree *MyBinTree=NULL; 	
	while(!input.eof())
	{
		long long Ch;
		input >> Ch;
		add(MyBinTree,Ch);     
    }
	Slozenie(MyBinTree);   
	output << Sum;
    input.close();
	output.close();
    return 0;

}