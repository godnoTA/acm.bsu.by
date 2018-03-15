#include <iostream>
#include <fstream>
using namespace std;
 struct TreeItem{
        int Info;
        TreeItem* Father;
        TreeItem* Lson;
        TreeItem* Rson;
		TreeItem(){ 
			Lson=NULL; 
			Rson = NULL; 
			Father = NULL;
		}
 };

class Tree {
private:
       friend void EraseItem(TreeItem * &s){
        if (s->Father != NULL){      
            if ((s->Father)->Lson == s)
                (s->Father)->Lson = NULL;
            else
                (s->Father)->Rson = NULL;
        }
		delete s;
		s = NULL;
    }
	void EraseNode(TreeItem* &s);
	 
public:
	TreeItem* Root;
    bool find(int n, TreeItem* &t, int &numb);
    bool insert( int info);
    bool Erase(int info);
    void post_order_walk(TreeItem *s, void visit(TreeItem* &c));

    void Destroy();
    void printTree(TreeItem *n);
 
    Tree();
    ~Tree();
	
};
 
Tree:: Tree() { Root=NULL;}
Tree:: ~Tree() { Destroy();}

bool Tree::find(int n,TreeItem* &t,int &numb){
	numb=0;
    if (Root==NULL){
        t=NULL;
        return false;
    }
    t=Root;
    while(true){
        if(t->Info==n){
            numb++;
            return true;
        }
        if((t->Info)>n){
            numb++;
            if((t->Lson)==NULL)
                return false;
            t=t->Lson;
        }
        else{
			numb++;
            if((t->Rson)==NULL)
                return false;
            t=t->Rson;
        }
    }      
}
 
 
bool Tree::insert(int info){
    TreeItem *p,*q;
    int num=0;
    if(find(info,p,num))
        return false;
    q=new TreeItem;
    q->Info=info;
 
    if(p==NULL){
        Root=q;
		q->Father = NULL;
    }
    else{
        q->Father=p;
        if((p->Info)>info)
            p->Lson=q;
        else
            p->Rson=q;
        return true;
    }
	return true;
}

//удаление вершины, имеющей не более одного сына
void Tree::EraseNode (TreeItem* &s){
    TreeItem *q=NULL;
    if(s->Lson!=NULL)
        q=s->Lson;
    else
        q=s->Rson;

    if (q!=NULL)
        q->Father=s->Father;
 
    if (s->Father == NULL)
        Root=q;
    else{
        if ((s->Father)->Lson == s)
            (s->Father)->Lson = q;
        else
            (s->Father)->Rson = q; 
        delete s;
    }
    s=NULL;
}

// удаление вершины по значению
bool Tree:: Erase(int info){
    TreeItem *s,*q;
    int num=0;
    if (!find(info,s,num))
        return false;
    else{
        if ((s->Lson != NULL)&&(s->Rson != NULL)){
            q=s->Rson;
            while (q->Lson!=NULL)
                q=q->Lson;
            s->Info=q->Info;
            EraseNode(q);
        }
        else
            EraseNode(s);
        return true;
    }
}
 
void Tree:: post_order_walk(TreeItem *s, void visit(TreeItem* &s)){
    if(s!=NULL){
        post_order_walk(s->Lson,visit);
        post_order_walk(s->Rson,visit);
        visit(s);
    }
}

ofstream fout("output.txt", ios:: out);
void Tree::printTree(TreeItem *n) {
        if (n==NULL )
            return;
		fout<<n->Info<<endl;
        printTree(n->Lson);
		printTree(n->Rson);
    }

 
void Tree:: Destroy(){
    post_order_walk(Root, EraseItem);
    Root=NULL;
}
 
int main(){
    setlocale(LC_ALL,".1251");
	ifstream fin("input.txt", ios:: in);
	int number;
	int r=0;
	Tree root;
	fin>>r;

    while (!fin.eof()){
		fin>>number;
		root.insert(number);
		if (fin.eof())
			break;
	}
	root.Erase(r);
	root.printTree(root.Root);
	fout.close();
	fin.close();
}