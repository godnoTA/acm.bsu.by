#include <iostream>
#include <fstream>
using namespace std;

ofstream output("output.txt");

struct BinTree
{
	BinTree *Left;
	BinTree *Right; 
    long t; 
   
};
void Slozenie(BinTree *&T)
{
	if (T!=NULL)
	{
	
	output << T->t << "\n"; 
	   Slozenie(T->Left); 
	  
	   Slozenie(T->Right);
	}
}
void add(BinTree *&MyBinTree, long t)
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
	if(input == NULL )
        return 1;
	BinTree *MyBinTree=NULL; 	
	while(!input.eof())
	{
		long Ch;
		input >> Ch;
		add(MyBinTree,Ch);     
    }
	Slozenie(MyBinTree);   
	input.close();
	output.close();
    return 0;

}