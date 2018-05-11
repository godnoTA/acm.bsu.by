#include<iostream>
#include<fstream>

using namespace std;

ifstream in("input.txt");
ofstream out("output.txt");
long long summa = 0;
	struct nite{
		int key; 
		nite *left; 
		nite *right; 
};

nite * tree=NULL;    
void push(int a,nite *&m)
{
	
	while (m == NULL)   
	{
		summa += a;
		m = new nite; 
      	  m -> key = a; 
      	  m -> left = m -> right = NULL; 
		return;
	}
	if ((a > m -> key)&&(m ->right == NULL))
	{
		summa += a;
			m -> right = new nite();
			m -> right -> key = a;
			m -> right -> left = NULL;
			m -> right -> right = NULL;
	}
	else if ((a > m -> key)&&(m -> right != NULL))
	{
		push(a, m -> right);
	}
	if ((a < m -> key)&&(m -> left == NULL))
	{
			summa += a;
			m -> left = new nite();
			m -> left -> key = a;
			m -> left -> left = NULL;
			m -> left -> right = NULL;
	}
	else if ((a < m -> key)&&(m -> left != NULL))
	{
			push(a, m -> left);
	}
}
int main()
{
	setlocale(LC_ALL, ".1251");
	nite* root = NULL;
	while (!in.eof()){
		int l;
		in >> l;
		push(l, root);
	}
	out<<summa;
	return 0;
}