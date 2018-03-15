#include <iostream>
#include <fstream>
#include <set>
#include <vector>

using namespace std;

ofstream fout("tst.out");

int max(int a, int b){
	if (a > b) return a;
	else return b;
}

struct Node{
	int high = 0;
	int maxLength = 0;
	int lengthTop = -1;
	int value;
	vector<int> down;
	vector<int> downToLeft;
	vector<int> downToRight;
	vector<int> up;

	Node *parent = NULL, *left = NULL, *right = NULL;
	bool f = false;
};

Node *head, *boss;
int k = 0, ansValue = -1;
bool flag = false;

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

Node * next(Node * cur){
	if (cur->left == NULL)
	{
		return cur;
	}
	else
	{
		next(cur->left);
	}
}

/*
void del(Node * cur, int value){
	if (cur == NULL) return;
	if (cur->value == value)
	{

		if (cur->left == NULL && cur->right == NULL)
		{
			if (cur->parent->left == cur)
			{
				cur->parent->left = NULL;
			}
			else
			{
				cur->parent->right = NULL;
			}
			delete cur;
			return;
		}
		Node * temp;
		if (cur->right == NULL || cur->left == NULL)
		{
			if (cur->left == NULL)
			{
				temp = cur->right;
			}
			else
			{
				temp = cur->left;	
			}
			if (cur->parent->left == cur)
			{
				cur->parent->left = temp;
			}
			else
			{
				cur->parent->right = temp;
			}
			delete cur;
			return;
		}
		temp = next(cur->right);
		if (cur->right != temp){
			temp->parent->left = temp->right;
		}
		else{
			cur->right = temp->right;
		}
		cur->value = temp->value;
		delete temp;
		return;
	}
	if (value < cur->value)
	{
		del(cur->left, value);
	}
	else
	{
		del(cur->right, value);
	}
}*/

void rightRemove(int value)
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
	if (x->right == NULL)
	{
		if (prevNode == NULL)
		{
			head = x->left;
		}
		else if (x == prevNode->right) prevNode->right = x->left;
		else prevNode->left = x->left;
	}
	else
	{
		Node* temp = x->right;
		Node* ourLeaf = NULL;
		while (temp->left != NULL)
		{
			ourLeaf = temp;
			temp = temp->left;
		}
		if (ourLeaf != NULL)
		{
			ourLeaf->left = temp->right;
		}
		else
		{
			x->right = temp->right;
		}
		x->value = temp->value;
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

int calcHigh(Node * cur){
	if (cur == NULL) return -1;
	if (cur) cur->high = max(calcHigh(cur->left), calcHigh(cur->right))+1;
}

void checkNode(Node * cur){
	if (cur){
		if (cur == head){
			cur->lengthTop = 0;
			cur->maxLength = cur->high;
			if (cur->maxLength < k) cur->f = true;
		}
		else{
			cur->lengthTop = cur->parent->lengthTop + 1;
			if (cur == cur->parent->left && cur->parent->right) cur->lengthTop = max(cur->lengthTop, cur->parent->right->high + 2);
			if (cur == cur->parent->right && cur->parent->left) cur->lengthTop = max(cur->lengthTop, cur->parent->left->high + 2);
			cur->maxLength = max(cur->high, cur->lengthTop);
			if (cur->maxLength < k) cur->f = true;
		}
		checkNode(cur->left);
		checkNode(cur->right);
	}
}

void initVector(Node * cur){
	if (cur == NULL) return;
	cur->down.resize(k + 2, 0);
	cur->downToLeft.resize(k + 2, 0);
	cur->downToRight.resize(k + 2, 0);
	cur->up.resize(k + 2, 0);
	initVector(cur->left);
	initVector(cur->right);
}

void calcDown(Node * cur){
	if (cur == NULL) return;
	if (cur->left == NULL && cur->right == NULL){
		cur->down[0] = 1;
	}
	if (cur->left){
		calcDown(cur->left);
		for (int it = 0; it < k; it++)
			if (cur->left->down[it] == 1){ 
				cur->down[it + 1] = 1;
				cur->downToLeft[it + 1] = 1;
			}
	}
	if (cur -> right){
		calcDown(cur->right);
		for (int it = 0; it < k; it++)
			if (cur->right->down[it] == 1){
				cur->down[it + 1] = 1;
				cur->downToRight[it + 1] = 1;
			}
	}
}

void calcUp(Node * cur){
	if (cur == NULL) return;
	if (cur == head){
		calcUp(cur->left);
		calcUp(cur->right);
		return;
	}
	if (cur->parent){
		for (int it = 0; it < k; it++)
			if (cur->parent->up[it] == 1) cur->up[it + 1] = 1;
	}
	if (cur -> parent && cur -> parent -> right && cur -> parent -> left == cur){
		for (int it = 0; it < k; it++)
			if (cur->parent->right->down[it] == 1) cur->up[it + 2] = 1;
	}
	if (cur->parent && cur->parent->left && cur->parent->right == cur){
		for (int it = 0; it < k; it++)
			if (cur->parent->left->down[it] == 1) cur->up[it + 2] = 1;
	}
	calcUp(cur->left);
	calcUp(cur->right);
}

void findNode(Node * cur){
	if (cur == NULL || flag) return;
	findNode(cur->right);
	if (flag) return;
	bool f = false;
	for (int i = 0; i <= k; i++){
		if (cur->down[i] == 1 && cur->up[k - i] == 1) f = true;
	}
	for (int i = 0; i <= k; i++){
		if (cur->downToLeft[i] == 1 && cur->downToRight[k - i] == 1) f = true;
	}
	if (!f && cur->f) {
		ansValue = cur->value;
		flag = true;
	}
	findNode(cur->left);
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
	fin >> k;
	int n = 0;
	int value;
	while (fin >> value)
	{
		insert(head, value);
		n++;
	}
	if (k > n){
		k = n;
	}
	if (k <= 0){
		if (head != NULL)
		{
			printAll(head);
			delAll(head);
		}
		else fout << "Empty" << endl;
		return 0;
	}
	calcHigh(head);
	checkNode(head);
	initVector(head);
	calcDown(head);
	calcUp(head);
	findNode(head);

	if (ansValue != -1 && head){
		//del(head, ansValue);
		rightRemove(ansValue);
		//if (boss) head = boss->left;
	}
	
	if (head != NULL)
	{
		printAll(head);
		delAll(head);
	}
	else fout << "Empty" << endl;

	return 0;
}
