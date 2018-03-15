#include <iostream>
#include <fstream>
#include <list>
#include <iterator>
using namespace std;
list <long long> leaves;
ofstream fout("tst.out");
struct TreeAlt
{
   long long info; 
   TreeAlt *left,*right,*parent; 
};
struct Tree
{
   long long info; 
   Tree *left,*right; 
};

void findLeaf(Tree *&T)
{
	if (T!=NULL)
	{
		if((T->left==NULL)&&(T->right==NULL))
			leaves.push_back(T->info); 
	    findLeaf(T->left); 
	    findLeaf(T->right);
	}
}
TreeAlt * findanc (TreeAlt *&MyTree, long long x)
{
   if (!MyTree)
	   return NULL;
   else if (x < MyTree->info)
      MyTree->left = findanc(MyTree->left, x);
   else if (x > MyTree-> info)
      MyTree->right = findanc(MyTree->right, x);
   else
   {
	   return MyTree->parent;
   }
}
void print(Tree *&T)
{
	if (T!=NULL)
	{
	   fout << T->info << endl; 
	   print(T->left); 
	   print(T->right);
	}
}
void add(Tree *&MyTree, long long info)
{
	if (MyTree==NULL)
	{
		MyTree=new Tree; 
		MyTree->info=info;
		MyTree->left=NULL;
		MyTree->right=NULL; 
	}
	if (info < MyTree->info)
	{
		if (MyTree->left!=NULL)
			add(MyTree->left,info);
		else 
		{
			MyTree->left=new Tree;  
			MyTree->left->left=MyTree->left->right=NULL;
			MyTree->left->info=info;
		}
	} 
	if (info > MyTree->info) 
	{
		if (MyTree->right!=NULL) 
			add(MyTree->right, info); 
		else 
		{
			MyTree->right=new Tree;  
			MyTree->right->left=MyTree->right->right=NULL;
            MyTree->right->info=info; 
		}
	}        
} 
void addAlt(TreeAlt *&MyTree, long long info)
{
	if (MyTree==NULL)
	{
		MyTree=new TreeAlt; 
		MyTree->info=info;
		MyTree->left=NULL;
		MyTree->right=NULL; 
		MyTree->parent=NULL; 
	}
	if (info < MyTree->info)
	{
		if (MyTree->left!=NULL)
			addAlt(MyTree->left,info);
		else 
		{
			MyTree->left=new TreeAlt;  
			MyTree->left->left=MyTree->left->right=NULL;
			MyTree->left->info=info;
			MyTree->left->parent=MyTree; 
		}
	} 
	if (info > MyTree->info) 
	{
		if (MyTree->right!=NULL) 
			addAlt(MyTree->right, info); 
		else 
		{
			MyTree->right=new TreeAlt;  
			MyTree->right->left=MyTree->right->right=NULL;
            MyTree->right->info=info; 
			MyTree->right->parent=MyTree;
		}
	}        
} 
Tree * del (Tree *&MyTree, long long x)
{
   Tree * P, *v;
   if (!MyTree)
	   return NULL;
   else if (x < MyTree->info)
      MyTree->left = del(MyTree->left, x);
   else if (x > MyTree-> info)
      MyTree->right = del(MyTree->right, x);
   else
   {
      P = MyTree;
      if (!MyTree->right)
		  MyTree = MyTree->left;
      else if (!MyTree->left)
		  MyTree = MyTree->right;
      else
      {
         v = MyTree->right;
         if (v->left)
         {
            while (v->left->left)
				v = v->left;
            MyTree->info = v->left->info;
            P = v->left;
            v->left = v->left->right;
         }
         else
         {
            MyTree->info = v->info;
            P = v;
            MyTree->right = MyTree->right->right;
         }
      }
      free(P);
   }
   return MyTree;
}

int main()
{
	long long ff;
	ifstream fin("tst.in");	
	if(fin == NULL )
        return 1;
	Tree *MyTree=NULL; 
	TreeAlt *MyTreeAlt=NULL; 
	while(!fin.eof())
	{
		long long chis;
		fin >> chis;
		add(MyTree,chis); 
		addAlt(MyTreeAlt,chis);
    }
	findLeaf(MyTree);
	if(leaves.size()%2!=0)
	{
		int num=1;
		for(list<long long>::iterator it2=leaves.begin(); it2!=leaves.end(); it2++)
		{
			int t1 = num-1;
			int t2 = leaves.size()/2;
			if(t1==t2)
			{
				long long numy = findanc(MyTreeAlt,*it2)->info;		
				del(MyTree,numy);
				break;
			}
			num++;
		}
	}
	print(MyTree);
    fin.close();
	fout.close();
    return 0;

}