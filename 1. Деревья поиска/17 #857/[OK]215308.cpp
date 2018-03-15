#define _CRT_SECURE_NO_WARNINGS 
#include <iostream> 
#include <fstream> 
#include "cmath"
using namespace std;

struct Node {
	int key;
	int height;
	Node *left;
	Node *right;
};

Node* findMin(Node *node) {
	if (node->left != NULL) {
		return findMin(node->left);
	}
	else {
		return node;
	}
}

void keyDelete(int delKey, Node*& node) {
	if (node == NULL) {
		return;
	}
	if (delKey > node->key) {
		keyDelete(delKey, node->right);
		return;
	}
	else if (delKey < node->key){
		keyDelete(delKey, node->left);
		return;
	}
	if (node->left == NULL){
		node = node->right;
		return;
	}
	else if (node->right == NULL){
		node = node->left;
		return;
	}
	else {
		int min = findMin(node->right)->key;
		node->key = min;
		keyDelete(min, node->right);
		return;
	}
}

void inOrder(Node *node, ofstream &out){
	if (node == NULL){
		return;
	}
	out << node->key << endl;
	inOrder(node->left, out);
	inOrder(node->right, out);
}

void searchDelKey(Node *&node, int& max2, int& count, int average, int max, bool& flag) {
	
		if (node == NULL) {
			return;
		}
		int diff = 0;
		searchDelKey(node->left, max2, count, average, max, flag);
		if (flag == false) {
			if (node->right == NULL && node->left == NULL) {
				diff = 0;
			}
			if (node->right != NULL && node->left != NULL) {
				diff = abs(node->left->height - node->right->height);
			}
			if (node->right != NULL && node->left == NULL) {
				diff = node->right->height + 2;
			}
			if (node->right == NULL && node->left != NULL) {
				diff = node->left->height + 2;
			}
			if (diff == max) {
				if (max <= diff) {
					if (max == diff) {
						count++;
					}
					if (max < diff) {
						count = 1;
					}
					max = diff;
				}
			}
			if (count == average) {
				cout << endl;
				cout << node->key << endl;
				keyDelete(node->key, node);
				flag = true;
			}
		}
		
			searchDelKey(node->right, max2, count, average, max, flag);
	
}

void add(int value, Node *&node){
	if (node != NULL){
		if (value > node->key){
			add(value, node->right);
		}
		if (value < node->key){
			add(value, node->left);
		}

	}
	else{
		node = new Node;
		node->key = value;
		node->left = node->right = NULL;

	}
}

int maximum(int a, int b){
	if (a > b){
		return a;
	}
	if (b > a){
		return b;
	}
	return a;
}

void height(Node* node, int &max, int &count){
	if (node == NULL){
		return;
	}
	int diff = 0;
	height(node->left, max, count);
	height(node->right, max, count);
	if (node->right == NULL && node->left == NULL){
		node->height = -1;
		diff = 0;
	}
	if (node->right != NULL && node->left != NULL){
		node->height = maximum(node->left->height, node->right->height) + 1;
		diff = abs(node->left->height - node->right->height);
	}
	if (node->right != NULL && node->left == NULL){
		node->height = node->right->height + 1;
		diff = node->right->height + 2;
	}
	if (node->right == NULL && node->left != NULL){
		node->height = node->left->height + 1;
		diff = node->left->height + 2;
	}
	if (max <= diff) {
		if (max == diff) {
			count++;
		}
		if (max < diff) {
			count = 1;
		}
		max = diff;
	}
}



void main(){
	Node *tree = NULL;
	int key = 0;
	int average = 0;
	int delKey = 0;
	int max = 0;
	bool flag = false;
	int max2 = 0;
	int count = 0;
	ofstream out("out.txt");
	ifstream in("in.txt");
	while (in >> key){
		add(key, tree);
	}
	//keyDelete(delKey, tree);
	height(tree, max, count);
	cout << "count" << count << endl;
	if (count % 2 != 0 && count >= 1) {
		average = floor(count / 2) + 1;
		cout << average;
		count = 0;
		searchDelKey(tree, max2, count, average, max, flag);
	}
	inOrder(tree, out);
	out.close();
	in.close();
	system("pause");
}