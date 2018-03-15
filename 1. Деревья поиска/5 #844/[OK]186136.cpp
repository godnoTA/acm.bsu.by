#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

class Node{
public:
	long long key;
	Node* left;
	Node* right;
	int height_no;
	int length;
	long long left_sum;
	long long right_sum;
	long long sum;

	Node(long long x)
	{
		this->key = x;
		this->left = nullptr;
		this->right = nullptr;
		left_sum= 2147483647;
		right_sum = 2147483647;
		height_no = 0;
		sum = 0;
	}
};

class Tree{
public:
	vector<int> vect;
	Node* root;
	int enter;
	int min_h;
	long long min_sum;
	

	Tree()
	{
		root = nullptr;
		min_h = 2147483647;
		min_sum = 2147483647;		
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

	void ObxodSnizu(Node* v){
		if (v != NULL){			
			ObxodSnizu(v->left);
			ObxodSnizu(v->right);
			Marker(v);
		}
	}

	void Marker(Node* v){
		if (v->left == NULL && v->right == NULL)
		{
			v->height_no = 0;
			v->length = 0;

			v->left_sum = v->key;
			v->right_sum = v->key;
			v->sum = v->key;
		}
		else if(v->left != NULL && v->right == NULL)
		{
			v->height_no = v->left->height_no + 1;
			v->length = v->left->length + 1;

			v->left_sum = min(v->left->left_sum, v->left->right_sum) + v->key;
			v->sum = v->left_sum;
		}
		else if(v->left == NULL && v->right != NULL)
		{
			v->height_no = v->right->height_no + 1;
			v->length = v->right->length + 1;

			v->right_sum = min(v->right->left_sum, v->right->right_sum) + v->key;
			v->sum = v->right_sum;
		}
		else if (v->left != NULL && v->right != NULL)
		{
			v->height_no = min(v->left->height_no, v->right->height_no) + 1;
			v->length = v->left->height_no + v->right->height_no + 2;

			min_h = min(v->length, min_h);

			v->left_sum = min(v->left->left_sum, v->left->right_sum) + v->key;
			v->right_sum = min(v->right->left_sum, v->right->right_sum) + v->key;
			if (v->length == min_h)
			min_sum = min(min_sum, v->left_sum + v->right_sum - v->key);
			
			v->sum = v->left_sum + v->right_sum - v->key;
		}
	}

	void ObxodSverhu(Node* v){
		if(v != NULL){
			Pusher(v);
			ObxodSverhu(v->left);
			ObxodSverhu(v->right);
		}
	}
	
	void Pusher(Node* v){
		if (v->length == min_h && v->sum == min_sum)
		{
			if (vect.empty())
				this->enter = v->key;
			else 
				if (v->key > this->enter) return;
				else{ 
					this->enter = v->key;
					vect.clear();
				}
			vect.push_back(v->key);
			
			if(v->left != NULL)
				InnerPusher(v->left, true);
			if(v->right != NULL)
				InnerPusher(v->right, false);
		}
	}

	void  InnerPusher(Node* v, bool mod){
		while(v != NULL){
			if (mod == false)
				vect.push_back(v->key);
			else
				vect.insert(vect.begin(),v->key);
			
			if (v->left == NULL && v->right == NULL)
				break;

			else if (v->left != NULL && v->right == NULL)
				v = v->left;

			else if (v->left == NULL && v->right != NULL)
				v = v->right;

			else if (v->left != NULL && v->right != NULL)
				if (v->left->height_no < v->right->height_no)
					v = v->left;
				else if (v->left->height_no > v->right->height_no)
					v = v->right;
				else if (v->left->height_no == v->right->height_no)
					v= v->left;
		}

	}

	void Print(Node* v, ofstream &f)
	{
		if (v != NULL)
		{
			f << v->key << endl;
			Print(v->left, f);
			Print(v->right, f);
		}
	}
};


int main(){

	Tree tr;
	long long x;

	ifstream fin;
	fin.open("in.txt");
	ofstream fout;
	fout.open("out.txt");
	
	while (fin.peek() != EOF)
	{
		fin >> x;		
		tr.Insert(x);
	}

	tr.ObxodSnizu(tr.root);
	tr.ObxodSverhu(tr.root);

	if (tr.vect.size() % 2 == 1)
	tr.Delete(tr.vect[tr.vect.size() / 2]);
	
	tr.Print(tr.root, fout);

	return 0;
}