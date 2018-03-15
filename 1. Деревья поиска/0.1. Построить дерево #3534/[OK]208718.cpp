#include <iostream>
#include <fstream>

using namespace std;

class btree
{
	long long int key;
	btree* left;
	btree* right;
public:
	btree() {key = 0; left = NULL; right = NULL; }
	btree(long long int a) {key = a; left = NULL; right = NULL; }
	void add(long long int a)
	{
		if (key > a)
		{
			if (left == NULL) left = new btree(a);
			else left->add(a);
		}
		if (key < a)
		{
			if (right == NULL) right = new btree(a);
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
	/*void clear()
	{
		if (left != NULL)
		{
			if (left->left != NULL || left->right != NULL) left->clear();
			else delete left;
		}
		if (right != NULL)
		{
			if (right->left != NULL || right->right != NULL) right->clear();
			else delete left;
		}
	}*/
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
};

int main()
{
	tree *a = new tree();
	ifstream f1("input.txt");
	ofstream f2("output.txt");
	long long int b;
	while (!f1.eof())
	{
		f1>>b;
		a->add(b);
	}
	a->out(f2);
	delete a;
	f1.close();
	f2.close();
	return 0;
}