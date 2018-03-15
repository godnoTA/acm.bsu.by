#include<iostream>
#include<fstream>
#include<string>
using namespace std;
struct TNode {
	int key;
	TNode *Left=NULL, *Right=NULL;
};
void show(TNode *&Tree,ofstream& out)
{
	string str = "";
	if (Tree != NULL) 
	{
		out<<to_string(Tree->key)+"\n";
		show(Tree->Left,out); 
		show(Tree->Right,out); 
	}
}
void del(TNode *&Tree) {
	if (Tree != NULL) 
	{
		del(Tree->Left); 
		del(Tree->Right); 
		delete Tree; 
		Tree = NULL;
	}

}
void add_node(int x, TNode *&MyTree) 
{
	if (NULL == MyTree)  
	{
		MyTree = new TNode;
		MyTree->key = x; 
		MyTree->Left = MyTree->Right = NULL; 
	}
	if (x<MyTree->key)   
	{
		if (MyTree->Left != NULL) add_node(x, MyTree->Left); 
		else 
		{
			MyTree->Left = new TNode; 
			MyTree->Left->Left = MyTree->Left->Right = NULL; 
			MyTree->Left->key = x; 
		}
	}

	if (x>MyTree->key)   
	{
		if (MyTree->Right != NULL) add_node(x, MyTree->Right); 
		else 
		{
			MyTree->Right = new TNode;  
			MyTree->Right->Left = MyTree->Right->Right = NULL; 
			MyTree->Right->key = x;
		}
	}
}
void main() {
	ifstream in;
	in.open("input.txt");
	TNode *Tree = NULL;
	string str;

	while (!in.eof()) {
		in >> str;
		add_node(atoi(str.c_str()), Tree);
	}
	in.close();
	ofstream out;
	out.open("output.txt");
	show(Tree, out);
	out.close();
}
