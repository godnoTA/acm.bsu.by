#include <iostream>
#include <fstream>

using namespace std;

class Node{
public:
	long long key;
	Node* left;
	Node* right;

	Node(long long x)
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
	
	void Insert(long long x)
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
	
	void ReplaceChild (Node* parent, Node* old, Node* item)
	{
		if (parent == NULL)
			this->root = item;
		else if(parent->left == old)
			parent->left = item;
		else if(parent->right == old)
			parent->right = item;
	}

	void Delete(long long x){
		Node* parent;
		parent = NULL;
		Node* v;
		v = this->root;

		while (true)
		{
			if (v == NULL)
				return;
			else if (x < v->key){
				parent = v;
				v = v->left;
			}
			else if (x > v->key){
				parent = v;
				v = v->right;
			}
			else if (x == v->key)
				break;
		}

		Node* result;
		result = NULL;

		if (v->left == NULL)
			result = v->right;
		else if (v->right == NULL)
			result = v->left;
		else
		{
			Node* tempParent;
			tempParent = v;
			Node* tempResult;
			tempResult = v->right;
			
			while(tempResult->left != NULL){
				tempParent = tempResult;
				tempResult = tempResult->left;
			}

			result = v;
			v->key = tempResult->key;

			ReplaceChild(tempParent, tempResult, tempResult->right);
				
		}

		ReplaceChild(parent, v, result);
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
	long long x, delElement;

	ifstream fin;
	fin.open("input.txt");
	ofstream fout;
	fout.open("output.txt");
	
	fin >> delElement;
	
	while (fin.peek() != EOF)
	{
		fin >> x;		
		tr.Insert(x);
	}

	tr.Delete(delElement);
	tr.Obxod(tr.root, fout);
	
	return 0;
}