#include <iostream> 
#include <cstdio> 
#include <vector> 
#include <algorithm> 

using namespace std;

struct Node {
	Node* left;
	Node* right;
	int value;
	Node(int _value) : left(NULL), right(NULL), value(_value) {}
};

void add(Node* root, Node* parent, int value) {
	while (root != NULL) {
		parent = root;
		if (root->value > value) {
			root = root->left;
		}
		else {
			root = root->right;
		}
	}
	if (parent->value > value) {
		parent->left = new Node(value);
	}
	else {
		parent->right = new Node(value);
	}
}

vector<int> ans(0);

int calcHeight(Node* root) {
	if (root == NULL) {
		return -1;
	}
	int l = calcHeight(root->left);
	int r = calcHeight(root->right);
	if (l != r) {
		ans.push_back(root->value);
	}
	return max(l, r) + 1;
}

Node*& getMin(Node*& root) {
	if (root->left == NULL) {
		return root;
	}
	return getMin(root->left);
}

void removeValue(Node* &root, int value) {
	if (root == NULL) {
		return;
	}
	if (root->value == value) {
		if (root->left != NULL && root->right != NULL) {
			Node*& minVal = getMin(root->right);
			root->value = minVal->value;
			minVal = minVal->right;
		}
		else if (root->left != NULL) {
			root = root->left;
		}
		else if (root->right != NULL) {
			root = root->right;
		}
		else {
			root = NULL;
		}
		return;
	}
	if (root->value > value) {
		removeValue(root->left, value);
	}
	else {
		removeValue(root->right, value);
	}
}

void print(Node* root) {
	if (root == NULL) {
		return;
	}
	cout << root->value << endl;
	print(root->left);
	print(root->right);
}

bool haveKey(Node* root, int x) {
if (root == NULL) {
return false;
}
if (root->value == x){
return true;
}
if (root->value > x) {
return haveKey(root->left, x);
}
return haveKey(root->right, x);
}

int main() {
	freopen("in.txt", "r", stdin);
	freopen("out.txt", "w", stdout);
	int x;
	Node* root = NULL;
	while (cin >> x) {
if (haveKey(root, x)) {
continue;
}
		if (root == NULL) {
			root = new Node(x);
		}
		else {
			add(root, NULL, x);
		}
	}
	calcHeight(root);
	if (ans.size() % 2 != 0) {
		sort(ans.begin(), ans.end());
		int del = ans[ans.size() / 2];
		removeValue(root, del);
	}
	print(root);
	return 0;
}