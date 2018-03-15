#include <iostream>
#include <fstream>

using namespace std;
ofstream out("out.txt");

class Tree
{
public:
	class Node
	{
	public:
		int key;
		Node* left;
		Node* right;
		int ver;
		Node(int a)
			: key(a), left(0), right(0), ver(0)
		{}
	};

	Tree()
	{
		root=0;
		count=0;
	}

	~Tree()
	{
		destroyNode(root);
	}

	void add(int a)
	{
		Node** cur=&root;
		while(*cur)
		{
			Node& n=**cur;
			if(a<n.key)
				cur=&n.left;
			else
				if(a>n.key)
					cur=&n.right;
				else return;
		}
		*cur=new Node(a);
	}
	
	void lcrobhod()
	{
		if(count%2!=0)
		{
			count++;
			count/=2;
			lcrobhod(root);
			Delete(&root, srver->key);
		}
		clrobhod(root);
	}

	void lrcobhod()
	{
		lrcobhod(root);
	}

private:
	Node* root;
	Node* srver;
	int count;

	void destroyNode(Node* n)
	{
		if(n!=0)
		{
			destroyNode(n->left);
			destroyNode(n->right);
			delete(n);
		}
	}

	void Delete (Node **Tree, int k)
	{
		Node *q;
		if (*Tree==NULL);
		else 
			if (k<(**Tree).key)
			Delete (&((**Tree).left), k); 
		else 
			if (k>(**Tree).key)
				Delete (&((**Tree).right), k); 
		else
		{ 
			q = *Tree; 
			if ((*q).right==NULL)
			{
				*Tree = (*q).left;
				delete q;
			} 
			else
			  if ((*q).left==NULL)
			  {
				  *Tree = (*q).right;
				  delete q;
			  }
			  else Delete_1 (&((*q).right), &q);
		}
	}

	void Delete_1 (Node **r, Node **q)
	{
		Node *s;

		if ((**r).left==NULL)
		{
			(**q).key = (**r).key;
			*q = *r; 
			s = *r;
			*r = (**r).right;
			delete s;
		}
		else Delete_1 (&((**r).left), q);
	}

	void lcrobhod(Node *n)
	{
		if(count<=0)
			return;
		if (n!=0)
		{
			lcrobhod(n->left);

			if((n->left!=0)&&(n->right!=0))
			{	
				if(n->left->ver!=n->right->ver)
					count--;
			}
			else 
				if((n->left!=0)&&(n->right==0))
					count--;
				else
					if((n->left==0)&&(n->right!=0))
						count--;
			if(count==0)
				srver=n;
			lcrobhod(n->right);
		}
	}

	void lrcobhod(Node *n)
	{
		if (n!=0)
		{
			lrcobhod(n->left);
			lrcobhod(n->right);
			n->ver++;
			if(n->left!=0)
				n->ver+=n->left->ver;
			if(n->right!=0)
				n->ver+=n->right->ver;
			if((n->left!=0)&&(n->right!=0))
			{	
				if(n->left->ver!=n->right->ver)
					count++;
			}
			else 
				if((n->left!=0)&&(n->right==0))
					count++;
				else
					if((n->left==0)&&(n->right!=0))
						count++;
		}
	}

	void clrobhod(Node *n)
	{
		if(n!=0)
		{
			out<<n->key<<endl;
			clrobhod(n->left);
			clrobhod(n->right);
		}
	}
};

int main()
{
	Tree tree;
	ifstream in;
	
	int a;
	in.open("in.txt");
	if(in.peek()==in.eof())
		return 0;
	while(!in.eof())
	{
		in>>a;
		tree.add(a);
	}
	in.close();
	tree.lrcobhod();
	tree.lcrobhod();
}