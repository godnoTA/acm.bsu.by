#include <iostream>
#include <fstream>

using namespace std;

class Node{
public:
	int key;
	Node* left;
	Node* right;

	Node(int x)
	{
		this->key = x;
		this->left = nullptr;
		this->right = nullptr;
	}
};

class Tree{
public:
	Node* root;

	Tree()
	{
		root = nullptr;
	}
	
	void Insert(int x)
	{
		Node* v;
		v = this->root;
		Node* parent;
		parent = nullptr;

		while (v != nullptr){
			parent = v;
			if (x < v->key)
				v = v->left;
			else if (x > v->key)
				v = v->right;
			else return;
		}

		Node* y = new Node(x);

		if (parent == nullptr)
			this->root = y;
		else if (parent->key > x)
			parent->left = y;
		else if (parent->key < x)
			parent->right = y;
	}

	void Obxod (Node* v, ofstream &f)
	{
		if (v != NULL)
		{
			f << v->key << endl;
			Obxod(v->left, f);
			Obxod(v->right, f);
		}
	}
};


int main(){

	Tree tr;
	int x;

	ifstream fin;
	fin.open("input.txt");
	ofstream fout;
	fout.open("output.txt");

	while (fin.peek() != EOF)
	{
		fin >> x;		
		tr.Insert(x);
	}
	tr.Obxod(tr.root, fout);
	return 0;
}