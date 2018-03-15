#include <iostream>
#include <fstream>
#include <algorithm>

using namespace std;

class btree
{
	long long int key, deep;
	btree* left;
	btree* right;
	btree* par;
public:
    friend void check(btree *, btree*);
    friend long long int sred(btree *, btree*);
    long long int get_key() {return key; }
	btree() {key = 0; left = NULL; right = NULL; par = NULL; deep = 0;}
	btree(long long int a) {key = a; left = NULL; right = NULL; par = NULL; deep = 0;}
	btree(long long int a, btree *b) {key = a; left = NULL; right = NULL; par = b; deep = par->deep + 1; }
	void add(long long int a)
	{
		if (key > a)
		{
			if (left == NULL) left = new btree(a, this);
			else left->add(a);
		}
		if (key < a)
		{
			if (right == NULL) right = new btree(a, this);
			else right->add(a);
		}
	}
	void out(ofstream& f2)
	{
		f2<<key<<endl;
		if (left != NULL) left->out(f2);
		if (right != NULL) right->out(f2);
	}

	long long int sum()
	{
		long long int s = 0;
		if (left != NULL) s += left->sum();
		if (right != NULL) s += right->sum();
		s += key;
		return s;
	}
	bool erase(long long a)
	{
        if (key == a)
        {
            if (left == NULL && right == NULL)
            {
                if (par != NULL)
                {
                    if (par->left == this) par->left = NULL;
                    if (par->right == this) par->right = NULL;
                }
                return false;
            }
            if (left == NULL && right != NULL)
            {
                if (right->left != NULL) right->left->par = this;
                if (right->right != NULL) right->right->par = this;
                key = right->key;
                left = right->left;
                right = right->right;
                return true;
            }
            if (left != NULL && right == NULL)
            {
                if (left->left != NULL) left->left->par = this;
                if (left->right != NULL) left->right->par = this;
                key = left->key;
                right = left->right;
                left = left->left;
                return true;
            }
            if (left != NULL && right != NULL)
            {
                if (right->left == NULL)
                {
                    if (right->right != NULL) right->right->par = this;
                    key = right->key;
                    right = right->right;
                }
                else
                {
                    btree *g = right;
                    while (g->left != NULL) g = g->left;
                    key = g->key;
                    g->erase(key);
                }
            return true;
            }
        }
        else if (key > a && left != NULL) left->erase(a);
             else if (key < a && right != NULL) right->erase(a);
    return true;
	}
	void fillmas();
};

class tree
{
	btree* kor;
public:
	tree() {kor = NULL; }
	bool isempty()
	{
		return (kor == NULL);
	}
	void add(long long int a)
	{
		if (!isempty()) kor->add(a);
		else kor = new btree(a);
	}
	void out(ofstream& f2)
	{
		if (!isempty()) kor->out(f2);
		else cout<<"Empty"<<endl;
	}
	long long int sum()
	{
		if (!isempty()) return kor->sum();
		else return 0;
	}
	//~tree() { if (!isempty()) { kor->clear(); delete kor; } }
	void erase(long long int a)
	{
	    bool flag;
		if (!isempty()) flag = kor->erase(a);
		if (!flag) kor = NULL;
	}

	void fillmas()
	{
        kor->fillmas();
	}
};

btree **mas;
btree *one, *two;
long long int kol(0), maxlen(0), n(0);

void btree::fillmas()
{
    if (par == NULL) deep = 0;
    else deep = par->deep+1;
    if ((left == NULL && right == NULL && par != NULL) || (left != NULL && right == NULL && par == NULL) || (left == NULL && right != NULL && par == NULL))
        mas[kol++] = this;
    if (left != NULL) left->fillmas();
    if (right != NULL) right->fillmas();
}

long long int sred(btree* a, btree* b)
{
    btree* buf1 = a;
    btree* buf2 = b;
    int k = 0;
    long long int* mass = new long long int [n];
    while (buf1->deep != buf2->deep)
    {
        if (buf1->deep > buf2->deep) { mass[k++] = buf1->key; buf1 = buf1->par; }
        else if (buf2->deep > buf1->deep) { mass[k++] = buf2->key; buf2 = buf2->par;}
    }
    while (buf1 != buf2)
    {
        mass[k++] = buf1->key;
        mass[k++] = buf2->key;
        buf1 = buf1->par;
        buf2 = buf2->par;
    }
    mass[k++] = buf1->key;
    sort(mass, mass+k);
    return mass[k/2];
}

void check(btree *a, btree *b)
{
    btree* buf1 = a;
    btree* buf2 = b;
    while (buf1->deep != buf2->deep)
    {
        if (buf1->deep > buf2->deep) buf1 = buf1->par;
        else if (buf2->deep > buf1->deep) buf2 = buf2->par;
    }
    while (buf1 != buf2)
    {
        buf1 = buf1->par;
        buf2 = buf2->par;
    }
    long long int len = a->deep + b->deep - 2*buf1->deep + 1;
    if (len == maxlen)
    {
        if (one == NULL)
        {
            one = a;
            two = b;
            maxlen = len;
        }
        else
        {
            if ((one->key + two->key) > (a->key + b->key))
            {
                one = a;
                two = b;
                maxlen = len;
            }
        }
    }
    if (len > maxlen)
    {
        one = a;
        two = b;
        maxlen = len;
    }
}

int main()
{
	tree *a = new tree();
	one = NULL;
	two = NULL;
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	long long int b;
	while (!f1.eof())
	{
	    n++;
		f1>>b;
		a->add(b);
	}
	mas = new btree* [n];
	a->fillmas();
	for (int i = 0; i<kol-1; i++)
        for (int j = i+1; j<kol; j++)
            check(mas[i], mas[j]);
    //cout<<one->get_key()<<' '<<two->get_key()<<endl;
    if (maxlen % 2 == 1)
    {
        a->erase(sred(one, two));
    }
    //else cout<<maxlen<<endl;
	a->out(f2);
	delete a;
	f1.close();
	f2.close();
	return 0;
}
