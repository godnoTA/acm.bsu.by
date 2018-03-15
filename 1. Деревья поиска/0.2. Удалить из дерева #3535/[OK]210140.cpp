#include <iostream>
#include <fstream>

using namespace std;

class btree
{
	long long int key;
	btree* left;
	btree* right;
	btree* par;
public:
    long long int get_key() {return key; }
	btree() {key = 0; left = NULL; right = NULL; par = NULL;}
	btree(long long int a) {key = a; left = NULL; right = NULL; par = NULL; }
	btree(long long int a, btree *b) {key = a; left = NULL; right = NULL; par = b; }
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
};

int main()
{
	tree *a = new tree();
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	long long int er, b;
	f1>>er;
	while (!f1.eof())
	{
		f1>>b;
		a->add(b);
	}
	a->erase(er);
	a->out(f2);
	delete a;
	f1.close();
	f2.close();
	return 0;
}
