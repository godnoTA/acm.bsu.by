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

node * tree=NULL;    

void push(int a,node *&t)
{
	
	if (t == NULL)   
	{
		t = new node; //Выделяем память
        t -> key = a; //Кладем в выделенное место аргумент a
        t -> left = t -> right = NULL; //Очищаем память для следующего роста
		return;
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
void output (node *tree) 
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

	node* root = NULL;
	while (!in.eof()){
		int l;
		in >> l;
		push(l, root);
	}
	output(root);
	return 0;
}