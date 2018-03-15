#include<iostream>
#include<fstream>
#include<string>
#include<bits/stdc++.h>
using namespace std;
struct TNode
{
    int key;
    TNode *Left=NULL, *Right=NULL;
    TNode *Father=NULL;
    TNode *root=NULL;
};
void display(TNode *&Tree,ofstream& out)
{
    if (Tree != NULL)
    {
        cout<<Tree->key<<" ";
        //cout<<Tree->Ml;
        cout<<"\n";
        out<<Tree->key;
        out<<"\n";
        display(Tree->Left,out);
        display(Tree->Right,out);
    }
}
void add_node(int x, TNode *&MyTree, TNode *&root)
{
    if (NULL == MyTree)
    {
        MyTree = new TNode;
        MyTree->key = x;
        MyTree->Left = NULL;
        MyTree->Right = NULL;
        root = MyTree;
    }
    if (x<MyTree->key)
    {
        if (MyTree->Left != NULL)
            add_node(x, MyTree->Left, root);
        else
        {
            MyTree->Left = new TNode;
            MyTree->Left->key = x;
            MyTree->Left->Left = NULL;
            MyTree->Left->Right = NULL;
            MyTree->Left->Father = MyTree;
        }
    }

    if (x>MyTree->key)
    {
        if (MyTree->Right != NULL)
            add_node(x, MyTree->Right, root);
        else
        {
            MyTree->Right = new TNode;
            MyTree->Right->key = x;
            MyTree->Right->Left = NULL;
            MyTree->Right->Right = NULL;
            MyTree->Right->Father = MyTree;
        }
    }
}
void replace1(TNode *&a,TNode *&b, TNode*&root)
{
    if (a->Father == NULL) root = b;
    else if (a == a->Father->Left) a->Father->Left = b;
    else a->Father->Right = b;
    if (b != NULL) b->Father = a->Father;
}
void del(TNode *&a, TNode*&root)
{
    if (a->Father == NULL) root = NULL;
    else if (a == a->Father->Left) a->Father->Left = NULL;
    else a->Father->Right = NULL;
}
void remove1(TNode *&t, int key, TNode*&root)
{
    if (t == NULL) return;
    if (key < t->key) remove1(t->Left, key, root);
    else if (key > t->key) remove1(t->Right, key, root);
    else if (t->Left != NULL && t->Right != NULL)
    {
        TNode *m = t->Right;
        while (m->Left != NULL) m = m->Left;
        t->key = m->key;
        replace1(m, m->Right, root);
        //del(m, root);
        //t->Right->Father=t;
    }
    else if (t->Left != NULL && t->Right == NULL)
    {
        replace1(t, t->Left, root);
    }
    else if (t->Right != NULL && t->Left == NULL)
    {
        replace1(t, t->Right, root);
    }
    else
    {
        del(t, root);
    }
}
void remove1(int key, TNode *&root)
{
    remove1(root, key, root);
}
int main()
{
    setlocale(LC_ALL, ".1251");
    srand((unsigned)time(NULL));
    TNode *Tree = NULL;
    string str, k;
    ifstream fin("input.txt");
    ofstream fout("output.txt");
    if (!fin)
    {
        cout<<"Cannot read!"<<endl;
        return -1;
    }
    fin>>k;
    int l=atoi(k.c_str());
    TNode*& root=Tree;
    while (!fin.eof())
    {
        fin >> str;
        add_node(atoi(str.c_str()), Tree, root);
    }
    //display(Tree, fout);
    //cout<<border;
    remove1(l, root);
    cout<<endl;
    display(Tree, fout);
    fin.close();
    fout.close();
    return 0;
}