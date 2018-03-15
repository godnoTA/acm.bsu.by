#include<iostream>
#include<fstream>
#include<string>
#include<bits/stdc++.h>
using namespace std;
struct TNode
{
    int key;
    TNode *Left=NULL, *Right=NULL;
};
void display(TNode *&Tree,ofstream& out)
{
    if (Tree != NULL)
    {
        out<<Tree->key;
        out<<"\n";
        display(Tree->Left,out);
        display(Tree->Right,out);
    }
}
void add_node(int x, TNode *&MyTree)
{
    if (NULL == MyTree)
    {
        MyTree = new TNode;
        MyTree->key = x;
        MyTree->Left = NULL;
        MyTree->Right = NULL;
    }
    if (x<MyTree->key)
    {
        if (MyTree->Left != NULL)
            add_node(x, MyTree->Left);
        else
        {
            MyTree->Left = new TNode;
            MyTree->Left->key = x;
            MyTree->Left->Left = NULL;
            MyTree->Left->Right = NULL;
        }
    }

    if (x>MyTree->key)
    {
        if (MyTree->Right != NULL)
            add_node(x, MyTree->Right);
        else
        {
            MyTree->Right = new TNode;
            MyTree->Right->key = x;
            MyTree->Right->Left = NULL;
            MyTree->Right->Right = NULL;
        }
    }
}
int main()
{
    setlocale(LC_ALL, ".1251");
    srand((unsigned)time(NULL));
    TNode *Tree = NULL;
    string str;
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    if (!fin)
    {
        cout<<"Файл не найден"<<endl;
        return -1;
    }
    while (!fin.eof())
    {
        fin >> str;
        add_node(atoi(str.c_str()), Tree);
    }
    display(Tree, fout);
    fin.close();
    fout.close();
    return 0;
}
