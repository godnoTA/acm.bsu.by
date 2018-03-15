#include<iostream>
#include<fstream>

using namespace std;

//Входной и выходной файлы

ifstream in("input.txt");
ofstream out("output.txt");
long long sum = 0;
struct node{
	int key; 
	node *left; 
	node *right; 
};

node * tree=NULL;    
void push(int a,node *&t)
{
	
	if (t == NULL)   
	{
		sum+=a;
		t = new node; //Выделяем память
        t -> key = a; //Кладем в выделенное место аргумент a
        t -> left = t -> right = NULL; //Очищаем память для следующего роста
		return;
	}
	if ((a > t->key)&&(t->right == NULL))
	{
		sum+=a;
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
			sum+=a;
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
int main()
{
	setlocale(LC_ALL, ".1251");
	node* root = NULL;
	while (!in.eof()){
		int l;
		in >> l;
		push(l, root);
	}
	//output(root);
	out<<sum;
	return 0;
}
