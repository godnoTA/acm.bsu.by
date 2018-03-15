#include <fstream>
#include <iostream>
using namespace std;
ofstream fout("out.txt");
ifstream fin("in.txt");
class Node {
public:
	int value;
	int hei;
	int sum;
	Node *left;
	Node *right;
	Node()
	{
		value = 0;
		left = NULL;
		right = NULL;

	}
	Node(int var)
	{
		value = var;
		left = NULL;
		right = NULL;

	}
	void print(){
		cout << value << endl;
	}
};
bool flagRoot = false;
int maximum;
int maxFun(int a, int b){
	if (a == b) return a;
	if (a > b) return a;
	if (b > a) return b;
}
class BST{
public:
	Node *root;
	BST()
	{
		root = NULL;
	}
	BST(int val)
	{
		root = new Node(val);
	}
	void print()
	{
		if (root != NULL)
		{
			print(root);
		}

	}
	void print(Node *n)
	{

		if (n != NULL)
		{

			fout << n->value << endl;
			print(n->left);
			print(n->right);
		}
	}
	void add(int val)
	{
		if (root == NULL)
		{
			root = new Node(val);
		}

		Node *temp = root;
		Node *pred = NULL;
		int compare = 0;
		while (temp != NULL)
		{
			pred = temp;
			compare = val - temp->value;
			if (compare > 0)
			{
				temp = temp->right;
			}
			else if (compare< 0)
			{
				temp = temp->left;
			}
			else if (compare == 0)
			{
				return;
			}
		}
		if (compare > 0)
		{
			pred->right = new Node(val);
		}
		else if (compare < 0)
		{
			pred->left = new Node(val);
		}
	}
	void heightSum()
	{
		if (root != NULL)
		{
			heightSum(root);
		}
	}
	void heightSum(Node *n)
	{
		int heiLeft;
		int heiRight;

		if (n->right != NULL)
		{
			heightSum(n->right);
			heiRight = n->right->hei;
		}
		else heiRight = 0;

		if (n->left != NULL)
		{
			heightSum(n->left);
			heiLeft = n->left->hei;
		}
		else heiLeft = 0;

		n->hei = (maxFun(heiRight, heiLeft) + 1);
		n->sum = heiRight + heiLeft;
		cout << n->value << " has s: " << n->sum << endl;
		cout << n->value << " has h: " << n->hei << endl;
		if (n->sum > maximum)
		{
			maximum = n->sum;
			cout << "Now, maximum is" << n->sum << endl;
		}
	}

	void searchRoot()
	{

		if (root != NULL)
		{
			searchRoot(root, NULL);
		}
	}
	void searchRoot(Node *n, Node *pred)
	{
		if (n->left != NULL)
		{
			searchRoot(n->left, n);
		}

		if (n->sum == maximum)
		{
			if (flagRoot == false)
			{
				Node *nodeRoot = NULL;
				nodeRoot = n;
				flagRoot = true;
				cout << nodeRoot->value << "root" << endl;
				delR(nodeRoot, pred);
				cout << " Root del only " << endl;

			}

		}
		if (n->right != NULL)
		{
			searchRoot(n->right, n);
		}

	}
	void delR(Node *n, Node *pred)
	{
		if (n == root)
		{
			if (n->left == NULL)
			{
				root = root->right;
			}
			if (n->right == NULL)
			{
				root = root->left;
			}

		}

		if (n->left == NULL && pred != NULL)
		{
			if (pred->left == n)
			{
				pred->left = n->right;
			}
			if (pred->right == n)
			{
				pred->right = n->right;
			}
		}
		if (n->right == NULL && pred != NULL)
		{
			if (pred->left == n)
			{
				pred->left = n->left;
			}
			if (pred->right == n)
			{
				pred->right = n->left;
			}
		}


		if ((n->right != NULL) && (n->left != NULL))
		{
			Node *tempNode = n->right;
			Node *tempPred = n;
			while (true)
			{
				if (tempNode->left != NULL)
				{
					tempPred = tempNode;
					tempNode = tempNode->left;
				}
				else break;
			}
			n->value = tempNode->value;
			if (tempNode->right != NULL)
			{
				if (tempPred != n)
				{
					tempPred->left = tempNode->right;
				}
				else
				{
					tempPred->right = tempNode->right;
				}
			}
			else
			{
				if (tempPred != n)
				{
					tempPred->left = NULL;
				}
				else
				{
					tempPred->right = NULL;
				}
			}

		}
	}

};

void main(){
	cout << "Start" << endl;
	BST MyTree = *(new BST());
	int value;
	while (fin.eof() != true)
	{
		fin >> value;
		MyTree.add(value);
	}
	MyTree.heightSum();
	MyTree.searchRoot();
	MyTree.print();
}