#include<iostream>
#include<fstream>
#include<cmath>
#include<vector>
#include <algorithm>

using namespace std;

//Входной и выходной файлы

ifstream in("tst.in");
ofstream out("tst.out");

struct node{
	int key;
	node *left; 
	node *right;
};   
long long Count=0;
vector <int> nodes;
void quickSort(int l, int r)
{
    int x = nodes[l + (r - l) / 2];
    //запись эквивалентна (l+r)/2, 
    //но не вызввает переполнения на больших данных
    int i = l;
    int j = r;
    //код в while обычно выносят в процедуру particle
    while(i <= j)
    {
        while(nodes[i] < x) i++;
        while(nodes[j] > x) j--;
        if(i <= j)
        {
            swap(nodes[i], nodes[j]);
            i++;
            j--;
        }
    }
    if (i<r)
                quickSort(i, r);
    
    if (l<j)    
        quickSort(l, j);    
}
node*& findmin(node *& min) {
	if (min->left == NULL)
		return min;
	return findmin(min->left);
}
node*& del(int val,node *&Tree_) {
	if (Tree_ == NULL) {
		return Tree_;
	}

	if (val < Tree_->key) 
		Tree_->left = del(val,Tree_->left);
	else {
		if (val > Tree_->key) {
			Tree_->right = del(val, Tree_->right);
		}
		else {
			if (Tree_->left != NULL && Tree_->right != NULL) {
				Tree_->key = findmin(Tree_->right)->key;
				Tree_->right = del(Tree_->key, Tree_->right);
			}
			else {
				if (Tree_->left != NULL)
					Tree_ = Tree_->left;
				else
					Tree_ = Tree_->right;
			}
		}
	}
	return Tree_;
}
void push(int a,node *&t)
{  
	if (t == NULL)   
	{
		t = new node; //Выделяем память
        t -> key = a; //Кладем в выделенное место аргумент a
        t -> left = t -> right = NULL; //Очищаем память для следующего роста
	}
	if ((a > t->key)&&(t->right == NULL))
	{
		t->right = new node();
		t->right->key = a;
		t->right->left = NULL;
		t->right->right = NULL;
	}
	else if ((a > t->key)&&(t->right != NULL))
	{
		push(a, t->right);
		
	}
	if ((a < t->key)&&(t->left == NULL))
	{
			t->left = new node();
			t->left->key = a;
			t->left->left = NULL;
			t->left->right = NULL;
	}
	else if ((a < t->key)&&(t->left != NULL))
	{
			push(a, t->left);
			
	}
}
void output (node *&tree) 
{
    if (tree==NULL)
		return; //Если дерево пустое, то отображать нечего, выходим
    else //Иначе
    {
		out<<tree->key<<endl; //И записываем элемент
		output(tree->left);//С помощью рекурсивного посещаем левое поддерево
		//for (int i=0;i<a;++i)
		//a--;
    }
    output(tree->right); //С помощью рекурсии посещаем правое поддерево
}

int getHeight(node*& c) {
		 
	if (c->left != NULL && c->right != NULL)
		return max(getHeight(c->left), getHeight(c->right)) + 1;
	if (c->left != NULL)
		return getHeight(c->left) + 1;
	if (c->right != NULL)
		return getHeight(c->right) + 1;
	/*if (c->key!=NULL && c->left == NULL && c->right == NULL)
		return 0;*/
	return -1;
			
}

void Delete(node*& c){
			
		 if (c->left==NULL && c->right==NULL){
			 nodes.push_back(c->key);
			 Count++;}
			if (c->left==NULL && c->right!=NULL)
				Delete(c->right);
			if (c->left!=NULL && c->right==NULL)
				Delete(c->left);
			if (c->left != NULL && c->right != NULL) {
				if(getHeight(c->left)== getHeight(c->right)){
					nodes.push_back(c->key);
					Count++;
				}
				Delete(c->left);
				Delete(c->right);
			}
		}




void av(node*& c){
	Delete(c);
	quickSort(0, nodes.size()-1);
	if(Count%2!=0){
	del((nodes[nodes.size()/2]),c);
	}
	else
		return;
}


int main()
{
	setlocale(LC_ALL, ".1251");
	node* tree = NULL;
	while (!in.eof()){
		int l;
		in >> l;
		push(l, tree);
	}
	av(tree);
	output(tree);
	system("pause");
	return 0;
}
