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
    int Mh;
    int Ml=1;
    int b=0;
    int a;
};
void display(TNode *&Tree,ofstream& out)
{
    if (Tree != NULL)
    {
        cout<<Tree->key<<" ";
        cout<<Tree->Mh<<" ";
        cout<<Tree->Ml<<" ";
        cout<<Tree->a<<" ";
        cout<<Tree->b<<" ";
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
    if (MyTree==NULL)
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
void pass1(TNode *&MyTree, set<int> &Rmax, int &z)
{
    if(MyTree != NULL)
    {
        if(Rmax.find(MyTree->key) != Rmax.end())
            if(MyTree->Right==NULL&&MyTree->Left==NULL)
                MyTree->b=0;
            else if (MyTree->Right == NULL)
                MyTree->b = MyTree->Left->Ml;
            else if (MyTree->Left == NULL)
                MyTree->b = MyTree->Right->Ml;
            else
                MyTree->b=MyTree->Right->Ml*MyTree->Left->Ml;
        if(MyTree->key==z) MyTree->a=0;
        if(MyTree->Left!=NULL&&MyTree->Right!=NULL)
        {
            if(MyTree->Left->Mh==MyTree->Right->Mh)
            {
                double res=MyTree->Left->Ml+MyTree->Right->Ml;
                int f1=(int)(MyTree->a/res*MyTree->Left->Ml);
                int f2=MyTree->a-f1;
                MyTree->Left->a=MyTree->b+f1;
                MyTree->Right->a=MyTree->b+f2;
            }
            else if(MyTree->Left->Mh<MyTree->Right->Mh)
            {
                MyTree->Right->a=MyTree->b+MyTree->a;
                MyTree->Left->a=MyTree->b;
            }
            else
            {
                MyTree->Left->a=MyTree->b+MyTree->a;
                MyTree->Right->a=MyTree->b;
            }
        }
        else if(MyTree->Left!=NULL) MyTree->Left->a=MyTree->a + MyTree->b;
        else if(MyTree->Right!=NULL) MyTree->Right->a=MyTree->a + MyTree->b;
        if (MyTree->Right != NULL) pass1(MyTree->Right, Rmax, z);
        if (MyTree->Left != NULL) pass1(MyTree->Left, Rmax, z);
    }
}
void pass2(TNode *&Tree, set<int> Rmax)
{
    if (Tree != NULL)
    {
        if((Tree->a+Tree->b)%2==0)
        {
            Rmax.insert(Tree->key);
            //copy( Rmax.begin(), Rmax.end(), ostream_iterator<int>(cout, " "));
            //cout<<Tree->Mh<<"v"<<Tree->Ml<<"v"<<Tree->key<<" ";
        }
        pass2(Tree->Left, Rmax);
        pass2(Tree->Right, Rmax);
    }
}
void pass3(TNode *&Tree, int &num)
{
    if(Tree!=NULL)
    {
        if ((Tree->a+Tree->b)%2==1)
        {
            if(Tree->key>num)
                num=Tree->key;
        }
        pass3(Tree->Right,num);
        pass3(Tree->Left,num);
    }
}
void reverse(TNode *&MyTree, int &num, set<int>& Rmax)
{
    if (MyTree->Left != NULL) reverse(MyTree->Left, num, Rmax);
    if (MyTree->Right != NULL) reverse(MyTree->Right, num, Rmax);
    if (MyTree->Left == NULL && MyTree->Right == NULL)
    {
        MyTree->Mh = 0;
        if(num<0)
        {
            num = 0;
            Rmax.clear();
            Rmax.insert(MyTree->key);
        }
        else if(num==0) Rmax.insert(MyTree->key);
    }
    else if(MyTree->Left == NULL)
    {
        MyTree->Ml=MyTree->Right->Ml;
        MyTree->Mh = MyTree->Right->Mh + 1;
        if(num<MyTree->Right->Mh+1)
        {
            Rmax.clear();
            num = MyTree->Right->Mh + 1;
            Rmax.insert(MyTree->key);
        }
        else if(num==MyTree->Right->Mh+1) Rmax.insert(MyTree->key);
    }
    else if(MyTree->Right == NULL)
    {
        MyTree->Ml=MyTree->Left->Ml;
        MyTree->Mh = MyTree->Left->Mh + 1;
        if(num<MyTree->Left->Mh+1)
        {
            Rmax.clear();
            num = MyTree->Left->Mh + 1;
            Rmax.insert(MyTree->key);
        }
        else if(num==MyTree->Left->Mh+1) Rmax.insert(MyTree->key);
    }
    else
    {
        MyTree->Mh = max(MyTree->Left->Mh, MyTree->Right->Mh) + 1;
        if(MyTree->Mh==MyTree->Left->Mh+1&&MyTree->Mh==MyTree->Right->Mh+1) MyTree->Ml=MyTree->Right->Ml+MyTree->Left->Ml;
        else if(MyTree->Mh==MyTree->Left->Mh+1&&MyTree->Mh>MyTree->Right->Mh+1) MyTree->Ml=MyTree->Left->Ml;
        else if(MyTree->Mh==MyTree->Right->Mh+1&&MyTree->Mh>MyTree->Left->Mh+1) MyTree->Ml=MyTree->Right->Ml;
        if(num<MyTree->Left->Mh+MyTree->Right->Mh+2)
        {
            Rmax.clear();
            num = MyTree->Left->Mh + MyTree->Right->Mh + 2;
            Rmax.insert(MyTree->key);
        }
        else if(num==MyTree->Left->Mh+MyTree->Right->Mh+2) Rmax.insert(MyTree->key);
        if (num == 0)
        {
            num = 1;
            Rmax.clear();
            Rmax.insert(MyTree->key);
        }
    }
}
void replace(TNode *&a,TNode *&b, TNode*&root)
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
void remove(TNode *&t, int key, TNode*&root)
{
    if (t == NULL) return;
    if (key < t->key) remove(t->Left, key, root);
    else if (key > t->key) remove(t->Right, key, root);
    else if (t->Left != NULL && t->Right != NULL)
    {
        TNode *m = t->Right;
        while (m->Left != NULL) m = m->Left;
        t->key = m->key;
        replace(m, m->Right, root);
    }
    else if (t->Left != NULL)
        replace(t, t->Left, root);
    else if (t->Right != NULL)
        replace(t, t->Right, root);
    else
        del(t, root);
}
void remove(int key, TNode *&root)
{
    remove(root, key, root);
}
int main()
{
    setlocale(LC_ALL, ".1251");
    srand((unsigned)time(NULL));
    TNode *Tree = NULL;
    //static TreeSet<Integer> Rmax=new TreeSet<>();
    set<int> Rmax;
    string str;
    int num=-100000000;
    ifstream fin("tst.in");
    ofstream fout("tst.out");
    if (!fin)
    {
        cout<<"Cannot read!"<<endl;
        return -1;
    }
    bool flag=true;
    int z;
    TNode*& root=Tree;
    while (!fin.eof())
    {
        fin >> str;
        if(flag)
        {
            z=atoi(str.c_str());
            flag=false;
        }
        add_node(atoi(str.c_str()), Tree, root);
    }
    reverse(Tree, num, Rmax);
    //Rmax.insert(z);
    pass1(Tree, Rmax, z);
    Rmax.clear();
    pass2(Tree, Rmax);
    //display(Tree, fout);
    num=-100000000;
    pass3(Tree, num);
    cout<<num<<endl;
    //display(Tree, fout);
    remove(num, root);
    //Rmax.key_compare
    //copy( Rmax.begin(), Rmax.end(), ostream_iterator<int>(cout, " "));
    display(Tree, fout);
    fin.close();
    fout.close();
    return 0;
}