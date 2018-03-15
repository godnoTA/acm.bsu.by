#include <iostream>
#include <fstream>
#include <set>
#include <vector>

using namespace std;

ofstream fout("tst.out");

int min(int a, int b){
	if (a > b) return b;
	else return a;
}

struct Node{
	int high = 0;
	int value;
	int leftNode = 0;
	int rightNode = 0;

	Node *parent = NULL, *left = NULL, *right = NULL;
	Node *theMostRight = NULL;
	bool f = false;
};

int minLen = -1;
Node *head, *boss;

vector<int> key;

void insert(Node * cur, int value){
	if (cur == NULL)
	{
		Node * temp = new Node;
		temp->value = value;
		temp->left = NULL;
		temp->right = NULL;
		head = temp;
		Node * prom = new Node;
		boss = prom;
		boss->left = head;
		head->parent = boss;
		return;
	}
	if (value == cur->value) return;
	else if (value < cur->value)
	{
		if (cur->left == NULL)
		{
			Node * temp = new Node;
			cur->left = temp;
			temp->value = value;
			temp->parent = cur;
			temp->left = NULL;
			temp->right = NULL;
			return;
		}
		insert(cur->left, value);
	}
	else
	{
		if (cur->right == NULL)
		{
			Node * temp = new Node;
			cur->right = temp;
			temp->value = value;
			temp->parent = cur;
			temp->left = NULL;
			temp->right = NULL;
			return;
		}
		insert(cur->right, value);
	}
}

void leftRemove(int value)
{

	Node *x = head, *prevNode = NULL;
	while (x != NULL)
	{
		if (x->value == value) break;
		prevNode = x;
		if (x->value > value) x = x->left;
		else x = x->right;
	}
	if (x == NULL) return;
	if (x->parent == NULL && x->right == NULL)
	{
		head = x->left;
		x->left->parent = NULL;
		return;
	}
	if (x->right == NULL && x->left != NULL)
	{
		if (x == prevNode->left)
		{
			prevNode->left = x->left;
			x->left->parent = prevNode;
		}
		else
		{
			prevNode->right = x->right;
			x->right->parent = prevNode;
		}
		return;
	}
	if (x->left == NULL)
	{
		if (prevNode == NULL)
		{
			head = x->right;
			x->right->parent = NULL;
		}
		else if (x == prevNode->left) prevNode->left = x->right;
		else prevNode->right = x->right;
	}
	else
	{
		Node* temp = x->left;
		Node* ourLeaf = NULL;
		while (temp->right != NULL)
		{
			ourLeaf = temp;
			temp = temp->right;
		}
		if (ourLeaf != NULL)
		{
			ourLeaf->right = temp->left;
		}
		else
		{
			x->left = temp->left;
		}
		x->value = temp->value;
		x->f = temp->f;
	}
}

void delAll(Node * cur){
	if (cur == NULL) return;
	if (cur->left != NULL)
	{
		delAll(cur->left);
	}
	if (cur->right != NULL)
	{
		delAll(cur->right);
	}
	delete cur;
}

void calcHigh(Node * cur){
	if (cur == NULL) return;
	calcHigh(cur->left);
	calcHigh(cur->right);
	if (cur->left) cur->high = cur->left->high; 
	if (cur->right) cur->high = cur->right->high;
	if (cur->right && cur->left) cur->high = min(cur->left->high, cur->right->high);
	if (cur->left || cur->right) cur->high += 1;
}

void findTheMostRight(Node * cur){
	if (!cur) return;
	if (cur->left){
		findTheMostRight(cur->left);
	}
	if (cur->right){
		findTheMostRight(cur->right);
	}
	if (!cur->right){
		cur->theMostRight = cur;
	}
	else {
		cur->theMostRight = cur->right->theMostRight;
	}
}

void deleteTrueNode(Node * cur){
	if (!cur) return;
	if (cur->left) deleteTrueNode(cur->left);
	if (cur->f){
		key.push_back(cur->value);
	}
	if (cur->right) deleteTrueNode(cur->right);
}

void del(){
	for (int i = 0; i < key.size(); i++){
		leftRemove(key[i]);
	}
}

void checkNode(Node * cur){
	if (cur){
		if ((cur->leftNode == minLen / 2 && cur->right && cur->right->high + 1 == cur->high) || 
			(cur->rightNode == minLen / 2 && cur->left && cur->left->high + 1 == cur->high) || 
			(cur->leftNode == minLen / 2 && cur->rightNode == minLen / 2)){
			cur->f = true;
		}
		if (cur->left && cur->left->high + 1 == cur->high){
			cur->left->leftNode = cur->leftNode;
			cur->left->rightNode = cur->rightNode+1;
			if (cur->left->high+1 == cur->high) checkNode(cur->left);
		}
		if (cur->right && cur->right->high+1 == cur ->high){
			cur->right->leftNode = cur->leftNode+1;
			cur->right->rightNode = cur->rightNode;
			if (cur->right->high+1 == cur->high) checkNode(cur->right);
		}
	}
}

void printAll(Node * cur){
	if (cur == NULL) return;
	fout << cur->value << endl;
	if (cur->left != NULL)
	{
		printAll(cur->left);
	}
	if (cur->right != NULL)
	{
		printAll(cur->right);
	}
}

int main(){
	ifstream fin("tst.in");
	int n = 0;
	int value;
	while (fin >> value)
	{
		insert(head, value);
		n++;
	}
	calcHigh(head);
	if (head) minLen = head->high;
	findTheMostRight(head);
//	cout << minLen << endl;
	if (minLen % 2 == 0){
		checkNode(head);
		deleteTrueNode(head);
		del();
	}

	if (head != NULL)
	{
		printAll(head);
		delAll(head);
	}

	return 0;
}