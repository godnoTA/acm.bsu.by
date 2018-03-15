#include <iostream>
#include <fstream>
#include <ctime>
using namespace std;


class Tree;


class Node
{
private:
	int data;
	Node *left;
	Node *right;
public:
	friend Tree;
	Node() { data = 0; left = 0; right = 0; }
	Node(int x) : data(x), left(0), right(0) {}
	int getdata() { return data; }
	Node *getleft() { return left; }
	Node *getright() { return right; }
};





class Tree
{
	Node *root;
public:
	Tree() :root(0) {}



	bool insert(int x)
	{
		bool b = true;
		Node** ppPrev = &root;
		Node* p = root;
		while (p)
		{
			if (x < p->data)
			{
				ppPrev = &(p->left);
				p = p->left;
			}
			else
			{
				if (x > p->data)
				{
					ppPrev = &(p->right);
					p = p->right;
				}
				else
				{
					b = false;
					return b;
				}
			}
		}
		if (b == true)
		{
			*ppPrev = new Node(x);
		}
		return b;
	}





	/////////////////////////////////////////////////
	//              Рекурсивный отсортированный вывод (inorder)
	void inorder_rec(ofstream& fo)
	{
		inorder_rec2(root, fo);
	}

	void inorder_rec2(Node *t, ofstream& fo)
	{
		if (t == nullptr)
		{
			return;
		}
		else
		{
			inorder_rec2(t->left, fo);
			fo << t->data << "\n";
			inorder_rec2(t->right, fo);
		}
	}




	/////////////////////////////////////////////////
	//                       Рекурсивный прямой вывод (preorder)
	void preorder_rec(ofstream& fo)
	{
		preorder_rec2(root, fo);
	}

	void preorder_rec2(Node *t, ofstream& fo)
	{
		if (t == nullptr)
		{
			return;
		}
		else
		{

			
			fo << t->data << endl;
			preorder_rec2(t->left, fo);
			preorder_rec2(t->right, fo);
		}
	}




	/////////////////////////////////////////////////
	//                     Рекурсивный обратный вывод (postorder)
	void postorder_rec(ofstream& fo)
	{
		postorder_rec2(root, fo);
	}

	void postorder_rec2(Node *t, ofstream& fo)
	{
		if (t == nullptr)
		{
			return;
		}
		else
		{
			postorder_rec2(t->left, fo);
			postorder_rec2(t->right, fo);
			fo << t->data << endl;
		}
	}



};




int main() {

	Tree tree;
	int x;

	ifstream fi("input.txt");

	while (fi >> x)
	{
		tree.insert(x);
	}


	

	ofstream fo("output.txt");

	tree.preorder_rec(fo);


	cout << endl << clock();

	system("pause");
	return 0;

}