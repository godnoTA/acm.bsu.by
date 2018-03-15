

#include <iostream>
#include <vector>
#include <algorithm>
#include <assert.h>

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
		int Height;
        TreeItem(): Parent(NULL), LSon(NULL), RSon(NULL), Height(0){}
        TreeItem(int data): info(data), Parent(NULL), LSon(NULL), RSon(NULL), Height(0){}
    };
	
    TreeItem* clone (TreeItem*, TreeItem*);
    void clean(TreeItem*);
   

    
	
public:
	int HeightRoot(TreeItem*);
	int Height(TreeItem*);
	TreeItem* lookFor (TreeItem*, int, int&) ;
	 TreeItem* Root;
    Tree():Root(NULL){}
    Tree(const Tree&);
	void lookForH (TreeItem*, int, vector<int>&) ;
    const Tree& operator = (const Tree&);

    bool insert(int);
    bool erase(int);
    bool find(int, int&);

    bool forward(void (*f)(FILE*, int), FILE*, TreeItem* item = NULL, bool root = true) const;
    void reverse(void (*f)(FILE*, int), FILE*, TreeItem* item = NULL, bool root = true) const;
	bool EqualSon(TreeItem*);

    ~Tree();
};

#endif //BST_TREE_H



//#include "head.h"



Tree::TreeItem *Tree::clone(Tree::TreeItem *where, Tree::TreeItem *from) {
    if (from == NULL)
        return NULL;
    TreeItem* temp = new TreeItem();
    temp->info = from->info;
	temp->Height = from->Height;
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

Tree::TreeItem *Tree::lookFor(Tree::TreeItem *item, int i, int &num) {
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


int Tree::HeightRoot(Tree::TreeItem* item){
	   int l, r, h = -1;
    if(item != NULL){
        l = HeightRoot(item->LSon);
		r = HeightRoot(item->RSon);
		if(item->LSon != NULL|| item->RSon != NULL)
			h = ((l > r) ? l : r) + 1 ;	
		else
			h = 0;
    }
    return h;
}
int Tree::Height(Tree::TreeItem* item){
	int H = HeightRoot(Root);
	int h = 0;
	//int h = HeightRoot(item);
	find(item->info, h);
	h--;
	//if(item->LSon != NULL && item->RSon != NULL)
		return H-h;
	//else
		//return 0;
	  
}
bool Tree::EqualSon(Tree::TreeItem *item){
	int h1 = 0, h2 = 0;
	if(item->LSon != NULL)
		h1 = HeightRoot(item->LSon);
	else
		h1 = -1;
	if(item->RSon != NULL)
		h2 = HeightRoot(item->RSon);
	else 
		h2 = -1;
	 if(h1 == h2)
		 return true;
	 else
		 return false;

}
void Tree::lookForH(Tree::TreeItem *item, int i, vector<int>& v) {
    
    if (item == NULL)
        return;
	vector<int>::iterator it = v.begin();
  
	
	
	int h = 0;
	if(item->LSon != NULL){	
		h = Height(item->LSon);
	
		if ( h >= i)
		  lookForH(item->LSon, i, v);
	}
	
	it = v.begin();
	if(item != NULL){
	h = Height(item);
	  if (h == i){
		   bool w1 = EqualSon(item);
		   if(w1){
			  			 
				int temp = item->info;
				v.push_back(temp);
					// lookForH(Root, i, v);
		
			  }
		   }
			 
        
	}
     if(item->RSon != NULL)
		{
			h = 0;
			h = Height(item->RSon);
			if ( h >= i)
			  lookForH(item->RSon, i, v);
			
	}
	
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
	//	where->RSon->Height = where->Height+1;
        item->Parent = where;
        return true;
    }
    else
    {
        where->LSon = item;
	//	where->LSon->Height = where->Height+1;
        item->Parent = where;
        return true;
    }
}

bool Tree::erase(int i) {
    TreeItem* item = new TreeItem(i);
    int c = 0;
    TreeItem* where = lookFor(Root, item->info, c);
	/*if (where == Root)
    {
        if (where->LSon == NULL && where->RSon == NULL)
        {
			Root = NULL;
            delete where;
            return true;
        }
        else if (where->LSon == NULL || where->RSon == NULL)
        {
            if (where->RSon != NULL)
                Root = where->RSon;
            else
			{
                Root = where->LSon;
			}
            delete where;
            return true;
        }
	}
	else{	if(where->RSon != NULL){
			TreeItem* temp = where->RSon;
			while(temp->LSon != NULL)
				temp = temp->LSon;
			where->info = temp->info;
			temp->Parent->LSon = NULL;
			delete temp;
		}
		else if(where->LSon != NULL){
			where->Parent->LSon == NULL;
			delete where;
		}
		else{ if(where == where->Parent->LSon)
				where->Parent->LSon == NULL;
		else
			where->Parent->RSon == NULL;
			delete where;
		}
	}
	return true;*/

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
            if (where->RSon != NULL)
                Root = where->RSon;
            else
			{
                Root = where->LSon;
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
            if (where->Parent->LSon == where){
				where->LSon->Height--;
                where->Parent->LSon = where->LSon;
			}
            else{
				where->LSon->Height--;
                where->Parent->RSon = where->LSon;
			}
            where->LSon->Parent = where->Parent;
            delete where;
            return true;
        }
        else
        {
            if (where->Parent->LSon == where){
				//where->RSon->Height--;
                where->Parent->LSon = where->RSon;
			}
            else
			{
			//	where->RSon->Height--;
                where->Parent->RSon = where->RSon;
			}
            where->RSon->Parent = where->Parent;
            delete where;
            return true;
        }
    }
    else
    {
        TreeItem* temp = where->RSon;
        while (temp->LSon != NULL){
            temp = temp->LSon;
			temp->Height--;
		}
        where->info = temp->info;
        if (temp->Parent->LSon == temp)
        {
			//temp->RSon->Height--;
            temp->Parent->LSon = temp->RSon;
			
            if (temp->RSon != NULL)
                temp->RSon->Parent = temp->Parent;
        }
        else
        {
         // temp->RSon->Height--;
		  temp->Parent->RSon = temp->RSon;
            if (temp->RSon != NULL)
                temp->RSon->Parent = temp->Parent;
        }
        delete temp;
        return true;
    }

}

bool Tree::find(int i, int &num) {
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
	if ((in=fopen("in.txt","r"))==NULL)
	{
		cout<<"Невозможно открыть input файл."<<endl;
		return 1;
	}
	if ((out=fopen("out.txt","w"))==NULL)
	{
		cout<<"Невозможно открыть output файл."<<endl;
		return 1;
	}
	int d;
	//fscanf(in, "%d", &d);
	
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
	vector<int> v(0);
	vector<int> v2(0);
	int H = T.HeightRoot(T.Root);
	cout << H << "\n";
	int hhh = 0;
	//T.find((int)13, hhh);
	int sum = 0;
	int count = 0;
	T.lookForH(T.Root,H/2,v);
	vector<int>::iterator it = v.begin();
	/*while(it != v.end()){
		count++;
		//sum+=*it;
		//	fprintf(out,"% d", it->first);
				

		it++;
	}*/
	/*if(count != 0)
		if(sum%count == 0){
			sum/=count;
			int teee = 0;
			bool w = false;
			if(T.find(sum, teee)){
				it = v.begin();
				while(it != v.end()){
					if(*it == sum){
						w = true;
					//	fprintf(out,"% d", it->first);
					}
		
					it++;
				}
			}
			if(w)
				T.erase(sum);
		}
	*/
	vector <int> temp(v);
	sort(temp.begin(), temp.end());
	assert(temp == v);
	if(v.size()%2 == 1){
		T.erase(v[v.size()/2]);

	}

	cout << v.size() << "\n";
	for (int i = 0 ; i < v.size(); i++) {
		cout << v[i] << " ";
	}

	//T.erase(d);
	
	//fprintf(out,"List of element:\n");
	if(!T.forward(Print,out))	
		fprintf(out,"Empty tree\n");
	//T.reverse(Print,out);


	
	return 0;
}
