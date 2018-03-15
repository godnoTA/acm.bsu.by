#include<iostream>
#include<fstream>
#include<stack>
#include<string>
#include<set>
#include<algorithm>

using namespace std;
class Node {
	public:
		int key;
		Node *left=NULL;
		Node *right=NULL;
		Node *parent = NULL;
		int Height;
		int Number = 1;
		int b = 0;
		int a;
		Node(int key) {
			this->key = key;
		}
};

class Tree {
public: Node *root=NULL;
	void replace(Node *a, Node *b) {
		if (a->parent == NULL)
			root = b;
		else if (a == a->parent->left)
			a->parent->left = b;
		else
			a->parent->right = b;
		if (b != NULL)
			b->parent = a->parent;
	}
	void remove(Node *t, int key) {
		if (t == NULL)
			return;
		if (key < t->key)
			remove(t->left, key);
		else if (key > t->key)
			remove(t->right, key);
		else if (t->left != NULL && t->right != NULL) {
			Node *m = t->right;
			while (m->left != NULL)
				m = m->left;
			t->key = m->key;
			replace(m, m->right);
		}
		else if (t->left != NULL) {
			replace(t, t->left);
		}
		else if (t->right != NULL) {
			replace(t, t->right);
		}
		else {
			replace(t,NULL);
		}
	}
	void remove(int key) {
		remove(root, key);
	}
	void insert(int x) {
		root = doInsert(root, x);
	}
	Node* doInsert(Node *node, int x) {
		if (node == NULL) {
			Node *test = new Node(x);
			return test;
		}
		if (x < node->key) {
			node->left = doInsert(node->left, x);
			node->left->parent = node;
		}
		else if (x > node->key) {
			node->right = doInsert(node->right, x);
			node->right->parent = node;
		}
		return node;
	}
};

static string leftDirect(Tree *binaryTree,ofstream &out) {
	stack<Node*> steck;
	Node* top = binaryTree->root;
	string str = "";
	while (top != NULL || !steck.empty()) {
		if (!steck.empty()) {
			top = steck.top();
			steck.pop();
		}
		while (top != NULL) {
			out<<top->key<<endl;
			if (top->right != NULL)
				steck.push(top->right);
			top = top->left;
		}
	}
	return str;
}
static set<int> Rmax;
static int border = INT_MIN;
static void reverse(Node* root) {
	if (root->left != NULL)
		reverse(root->left);
	if (root->right != NULL)
		reverse(root->right);
	if (root->left == NULL&&root->right == NULL) {
		root->Height = 0;
		if (border<0) {
			border = 0;
			Rmax.clear();
			Rmax.insert(root->key);
		}
		else if (border == 0)
			Rmax.insert(root->key);
	}
	else if (root->left == NULL) {
		root->Number = root->right->Number;
		root->Height = root->right->Height + 1;
		if (border<root->right->Height) {
			Rmax.clear();
			border = root->right->Height;
			Rmax.insert(root->key);
		}
		else if (border == root->right->Height)
			Rmax.insert(root->key);
	}
	else if (root->right == NULL) {
		root->Number = root->left->Number;
		root->Height = root->left->Height + 1;
		if (border<root->left->Height) {
			Rmax.clear();
			border = root->left->Height;
			Rmax.insert(root->key);
		}
		else if (border == root->left->Height)
			Rmax.insert(root->key);
	}
	else {
		root->Height = max(root->left->Height, root->right->Height) + 1;
		if (root->Height == root->left->Height + 1 && root->Height == root->right->Height + 1)
			root->Number = root->right->Number + root->left->Number;
		if (root->Height == root->left->Height + 1 && root->Height>root->right->Height + 1)
			root->Number = root->left->Number;
		if (root->Height == root->right->Height + 1 && root->Height>root->left->Height + 1)
			root->Number = root->right->Number;
		if (border<root->left->Height + root->right->Height) {
			Rmax.clear();
			border = root->left->Height + root->right->Height;
			Rmax.insert(root->key);
		}
		else if (border == root->left->Height + root->right->Height)
			Rmax.insert(root->key);
		if (border == 0)
		{
			border = 1;
			Rmax.clear();
			Rmax.insert(root->key);
		}
	}
}
static void leftDirect1(Tree* binaryTree) {
	stack<Node*> steck;
	Node *top = binaryTree->root;
	while (top != NULL || !steck.empty()) {
		if (!steck.empty()) {
			top = steck.top();
			steck.pop();
		}
		while (top != NULL) {
			if (Rmax.find(top->key)!=Rmax.end()) {
				if (top->right != NULL&&top->left != NULL)
					top->b = top->right->Number * top->left->Number;
				else if (top->right != NULL)
					top->b = top->right->Number;
				else if (top->left != NULL)
					top->b = top->left->Number;
				else
					top->b = 0;
			}
			if (top->key == binaryTree->root->key)
				top->a = 0;
			if (top->left != NULL&&top->right != NULL)
			{
				if (top->left->Height == top->right->Height)
				{
					double res = top->left->Number + top->right->Number;
					int f1 = (int)(top->a / res*top->left->Number);
					int f2 = top->a - f1;
					top->left->a = top->b + f1;
					top->right->a = top->b + f2;
				}
				else if (top->left->Height<top->right->Height) {
					top->right->a = top->b + top->a;
					top->left->a = top->b;
				}
				else
				{
					top->left->a = top->b + top->a;
					top->right->a = top->b;
				}
			}
			else if (top->left != NULL)
				top->left->a = top->a + top->b;
			else if (top->right != NULL)
				top->right->a = top->a + top->b;
			if (top->right != NULL)
				steck.push(top->right);
			top = top->left;
		}
	}
}
static void leftDirect2(Tree* binaryTree) {
	stack<Node*> steck;
	Node* top = binaryTree->root;
	while (top != NULL || !steck.empty()) {
		if (!steck.empty()) {
			top = steck.top();
			steck.pop();
		}
		while (top != NULL) {
			if ((top->a + top->b) % 2 == 0)
				Rmax.insert(top->key);
			if (top->right != NULL)
				steck.push(top->right);
			top = top->left;
		}
	}
}
int main() {
	Tree *binaryTree = new Tree();
		ifstream in;
		in.open("tst.in");
		ofstream out;
		out.open("tst.out");
		string str;
		while (!in.eof()) {
			in >> str;
			binaryTree;
			binaryTree->insert(atoi(str.c_str()));
		}
		in.close();
		if (binaryTree->root == NULL)
			out<<"\n";
		else if (binaryTree->root->left == NULL&&binaryTree->root->right == NULL)
			out << "\n";
		else {
			reverse(binaryTree->root);
			leftDirect1(binaryTree);
			Rmax.clear();
			leftDirect2(binaryTree);
			set<int>::iterator t=Rmax.begin();
			if (Rmax.size() % 2 == 1) {
				int *mas = new int[Rmax.size()];
				for (int i = 0;t!=Rmax.end(); i++, t++)
					mas[i] = *t;
				binaryTree->remove(mas[(int)Rmax.size() / 2]);
			}
			leftDirect(binaryTree,out);
		}
		out.close();
		return 0;
	}

	