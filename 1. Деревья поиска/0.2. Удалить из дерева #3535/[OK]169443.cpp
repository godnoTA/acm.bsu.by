#include<iostream>
#include<fstream>

using namespace std;

//Входной и выходной файлы

ifstream in("input.txt");
ofstream out("output.txt");

struct node{
	int key; 
	node *left; 
	node *right; 
};   
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
int main()
{
	setlocale(LC_ALL, ".1251");
	node* tree = NULL;
	int val;
	in>>val;
	while (!in.eof()){
		int l;
		in >> l;
		push(l, tree);
	}
	del(val,tree);
	output(tree);
	return 0;
}
