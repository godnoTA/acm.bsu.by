

#include <iostream>

using namespace std;


#ifndef BST_TREE_H
#define BST_TREE_H

//#define NULL nullptr

#include<iostream>

class Tree {
private:
    struct TreeItem
    {
        int info;
        TreeItem* Parent;
        TreeItem* LSon;
        TreeItem* RSon;
        TreeItem(): Parent(NULL), LSon(NULL), RSon(NULL){}
        TreeItem(int data): info(data), Parent(NULL), LSon(NULL), RSon(NULL){}
    };
    TreeItem* Root;
    TreeItem* clone (TreeItem*, TreeItem*);
    void clean(TreeItem*);
    TreeItem* lookFor (TreeItem*, int, int&) const;
public:
    Tree():Root(NULL){}
    Tree(const Tree&);

    const Tree& operator = (const Tree&);

    bool insert(int);
    bool erase(int);
    bool find(int, int&) const;

    bool forward(void (*f)(FILE*, int), FILE*, TreeItem* item = NULL, bool root = true) const;
    void reverse(void (*f)(FILE*, int), FILE*, TreeItem* item = NULL, bool root = true) const;

    ~Tree();
};

#endif //BST_TREE_H



//#include "head.h"



Tree::TreeItem *Tree::clone(Tree::TreeItem *where, Tree::TreeItem *from) {
    if (from == NULL)
        return NULL;
    TreeItem* temp = new TreeItem();
    temp->info = from->info;
    temp->Parent = where;
    temp->LSon = clone(temp, from->LSon);
    temp->RSon = clone(temp, from->RSon);
    return temp;
}

Tree::Tree(const Tree &tree) {
    Root = clone(NULL, tree.Root);
}

Tree::~Tree() {
    clean(Root);
}

void Tree::clean(Tree::TreeItem *node) {
    if (node == NULL)
        return;
    clean(node->LSon);
    clean(node->RSon);
    delete node;
}

const Tree &Tree::operator=(const Tree &tree) {
    if (&tree == this)
        return *this;
    clean(Root);
    Root = clone(NULL, tree.Root);
    return *this;
}

Tree::TreeItem *Tree::lookFor(Tree::TreeItem *item, int i, int &num) const{
    num++;
    if (item == NULL)
        return NULL;
    if (item->info == i)
        return item;
    else if (item->LSon != NULL && item->info > i)
        return lookFor(item->LSon, i, num);
    else if (item->RSon != NULL && item->info < i)
        return lookFor(item->RSon, i, num);
    else
        return item;
}

bool Tree::insert(int x) {
    TreeItem* item = new TreeItem(x);
    if (Root == NULL)
    {
        Root = item;
        return true;
    }
    int c = 0;
    TreeItem* where = lookFor(Root, item->info, c);
    if (where == NULL)
        return false;
    if (where->info == item->info)
        return false;
    else if (where->info < item->info)
    {
        where->RSon = item;
        item->Parent = where;
        return true;
    }
    else
    {
        where->LSon = item;
        item->Parent = where;
        return true;
    }
}

bool Tree::erase(int i) {
    TreeItem* item = new TreeItem(i);
    int c = 0;
    TreeItem* where = lookFor(Root, item->info, c);
    if (where == NULL || where->info != item->info)
        return false;
    delete item;
    if (where == Root)
    {
        if (where->LSon == NULL && where->RSon == NULL)
        {
			Root = NULL;
            delete where;
            return true;
        }
        else if (where->LSon == NULL || where->RSon == NULL)
        {
            if (where->LSon != NULL)
                Root = where->LSon;
            else
			{
                Root = where->RSon;
			}
            delete where;
            return true;
        }
	}
    if (where != Root && where->LSon == NULL && where->RSon == NULL)
    {
        if (where->Parent->LSon == where)
            where->Parent->LSon = NULL;
        else
            where->Parent->RSon = NULL;
        delete where;
        return true;
    }
    else if (where != Root && where->LSon == NULL || where->RSon == NULL)
    {
        if (where->LSon != NULL)
        {
            if (where->Parent->LSon == where)
                where->Parent->LSon = where->LSon;
            else
                where->Parent->RSon = where->LSon;
            where->LSon->Parent = where->Parent;
            delete where;
            return true;
        }
        else
        {
            if (where->Parent->LSon == where)
                where->Parent->LSon = where->RSon;
            else
                where->Parent->RSon = where->RSon;
            where->RSon->Parent = where->Parent;
            delete where;
            return true;
        }
    }
    else
    {
        TreeItem* temp = where->RSon;
        while (temp->LSon != NULL)
            temp = temp->LSon;
        where->info = temp->info;
        if (temp->Parent->LSon == temp)
        {
            temp->Parent->LSon = temp->RSon;
            if (temp->RSon != NULL)
                temp->RSon->Parent = temp->Parent;
        }
        else
        {
            temp->Parent->RSon = temp->RSon;
            if (temp->RSon != NULL)
                temp->RSon->Parent = temp->Parent;
        }
        delete temp;
        return true;
    }

}

bool Tree::find(int i, int &num) const {
    TreeItem* temp = lookFor(Root, i, num);
    if ((temp != NULL) && (temp->info == i))
        return true;
    else
        return false;
}

bool Tree::forward(void (*f)(FILE*,int),FILE* out, TreeItem* item, bool root) const {
    if (root){
        item = Root;
		f(out,Root->info);
	}

    if (item != NULL)
    {
		
        if (item->LSon != NULL){
			f(out,item->LSon->info);
            forward(f, out, item->LSon, false);
			}
        
        if (item->RSon != NULL){
			f(out,item->RSon->info);
			forward(f, out, item->RSon, false);}
		return true;
    }
	return false;
}

void Tree::reverse(void (*f)(FILE*,int),FILE* out, Tree::TreeItem *item, bool root) const {
    if (root)
        item = Root;
    if (item != NULL)
    {
        if (item->RSon != NULL)
            reverse(f,out, item->RSon, false);
        f(out, item->info);
        if (item->LSon != NULL)
            reverse(f, out, item->LSon, false);
    }
}


void Print(FILE* out,int k){
	fprintf(out,"%d\n",k);
}

int main()
{
	setlocale(LC_ALL,".1251");
	FILE *in,*out;
	Tree T=Tree();
	int z;
	char r;
	if ((in=fopen("input.txt","r"))==NULL)
	{
		cout<<"Невозможно открыть input файл."<<endl;
		return 1;
	}
	if ((out=fopen("output.txt","w"))==NULL)
	{
		cout<<"Невозможно открыть output файл."<<endl;
		return 1;
	}
	int d;
	fscanf(in, "%d", &d);

	do
	{
		fscanf(in,"%d",&z);
		T.insert(z);
		/*switch(r)
		{
			case('I'):
				fscanf(in,"%d",&z);
				if(T.insert(z))
					fprintf(out,"%d: inserted\n",z);
				else
					fprintf(out,"%d: not inserted\n",z);
				break;
			case('D'):
				fscanf(in,"%d",&z);
				if(T.erase(z))
					fprintf(out,"%d: deleted\n",z);
				else
					fprintf(out,"%d: not deleted\n",z);
				break;
			case('F'):
			{
				int l=0;
				fscanf(in,"%d",&z);
				if(T.find(z,l))
					fprintf(out,"%d: found after %d comparasion(s)\n",z,l);
				else
					fprintf(out,"%d: not found after %d comparasion(s)\n",z,l);
				break;
			}
			case('L'):
				fprintf(out,"List of elements:\n");
				if(!T.forward(Print, out))
					fprintf(out,"Empty tree\n");

				break;
			default:break;
		}*/
	//	fscanf(in,"\n%c",&r);
		
	}while(!feof(in));
	T.erase(d);
	Tree T2;
	T2 = T;
	//fprintf(out,"List of element:\n");
	if(!T.forward(Print,out))
	
		fprintf(out,"Empty tree\n");
	//T.reverse(Print,out);


	
	return 0;
}
