#include <fstream>
#include <algorithm>
#include<vector>
#include <ctime>
#include <iostream>

using namespace std;

FILE *output = fopen("out.txt", "w");

enum State { START, LEFT, RIGHT, PARENT };
const int SHOW_LEFT = 1;
const int SHOW_RIGHT = 2;
const int SHOW_PARENT = 4;

class Tree {

	vector<int> nodes;
	class Node {
	public:
		int  value;
		Node * parent;
		Node * left;
		Node * right;
		int leftHeight;
		int rightHeight;
		int leftChildren;
		int rightChildren;

		Node(int value, Node * parent): value(value), left(nullptr), right(nullptr), parent(parent)
		, leftChildren(0), rightChildren(0), leftHeight(0), rightHeight(0){}
	};

private:
	Node * root;

	void investigateTheNode(Node * node) {
		if (node->left) {
			node->leftHeight = max(node->left->leftHeight, node->left->rightHeight) + 1;
			node->leftChildren = node->left->leftChildren + node->left->rightChildren + 1;
		}
		if (node->right) {
			node->rightHeight = max(node->right->leftHeight, node->right->rightHeight) + 1;
			node->rightChildren = node->right->leftChildren + node->right->rightChildren + 1;
		}
		if (node->leftHeight == node->rightHeight && node->leftChildren != node->rightChildren) {
			nodes.push_back(node->value);
		}
	}

	bool removeKey(Node * node) {

		if (node == nullptr)
			return false;

		Node* parent = node->parent;

		if (node->left == nullptr && node->right == nullptr) {     // первый случай: удаляемый элемент - лист
			if (node == root) {
				root = nullptr;
				return true;
			}
			if (parent->left == node) {
				parent->left = nullptr;
			}
			if (parent->right == node) {
				parent->right = nullptr;
			}
			return true;
		}

		else if (node->left == nullptr || node->right == nullptr) {
			if (node->left == nullptr) {
				//////////////////////////////
				if (node == root) {
					root = node->right;
					return true;
				}
				///////////////////////
				if (parent->left == node) {
					parent->left = node->right;
				}
				else
				{
					parent->right = node->right;
				}
				node->right->parent = parent;
				return true;
			}
			else {
				/////////////
				if (node == root) {
					root = node->left;
					return true;
				}
				/////////////
				if (parent->left == node) {
					parent->left = node->left;
				}
				else {
					parent->right = node->left;
				}
				node->left->parent = parent;
			}
			return true;
		}

		if (node->right != nullptr && node->left != nullptr) {
			Node * temp = node;
			temp = temp->right;
			if (temp->left == nullptr) {
				node->value = temp->value;
				node->right = temp->right;
			}
			else {
				while (temp->left != nullptr)
					temp = temp->left;
				node->value = temp->value;
				removeKey(temp);
			}
			return true;
		}
		return false;
	}

public:
	Tree(int rootValue) {
		root = new Node(rootValue, nullptr);
	}

	void addKey(int x) {
		Node** cur = &root;
		Node ** prev = nullptr;
		while (*cur) {
			Node& node = **cur;
			if (x < node.value) {
				prev = cur;
				cur = &node.left;
			}
			else if (x > node.value) {
				prev = cur;
				cur = &node.right;
			}
			else {
				return;
			}
		}
		*cur = new Node(x, *prev);
	}

	void traverse(int show) {
		Node * node = root;
		State state = START;
		do {
			switch (state) {
			case START:
				state = LEFT;
				break;
			case LEFT:
				if (show & SHOW_LEFT) {
					fprintf(output, "%d", node->value);
					fprintf(output, "%s", "\n");
				}
				if (node->left) {
					node = node->left;
					state = LEFT;
				}
				else {
					state = RIGHT;
				}
				break;
			case RIGHT:
				if (show & SHOW_RIGHT) {
					fprintf(output, "%d", node->value);
					fprintf(output, "%s", "\n");
				}
				if (node->right) {
					node = node->right;
					state = LEFT;
				}
				else {
					state = PARENT;
				}
				break;
			case PARENT:
				if (show & SHOW_PARENT) {
					fprintf(output, "%d", node->value);
					fprintf(output, "%s", "\n");
				}
				if (node->parent) {
					if (node->parent->left == node) {
						node = node->parent;
						state = RIGHT;
					}
					else {
						node = node->parent;
						state = PARENT;
					}
				}
				else {
					state = START;
				}
				break;
			}
		} while (state != START);
	}

	Node * foundKey(int x) {
		Node** cur = &root;
		while (*cur) {
			Node& node = **cur;
			if (x < node.value) {
				cur = &node.left;
			}
			else if (x > node.value) {
				cur = &node.right;
			}
			else {
				return *cur;
			}
		}
		return nullptr;
	}

	bool removeKey(int value) {
		return removeKey(this->foundKey(value));
	}
	
	void investigateTheTree() {
		int show = SHOW_PARENT;
		Node * node = root;
		State state = START;
		do {
			switch (state) {
			case START:
				state = LEFT;
				break;
			case LEFT:
				if (show & SHOW_LEFT) {
					investigateTheNode(node);
				}
				if (node->left) {
					node = node->left;
					state = LEFT;
				}
				else {
					state = RIGHT;
				}
				break;
			case RIGHT:
				if (show & SHOW_RIGHT) {
					investigateTheNode(node);
				}
				if (node->right) {
					node = node->right;
					state = LEFT;
				}
				else {
					state = PARENT;
				}
				break;
			case PARENT:
				if (show & SHOW_PARENT) {
					investigateTheNode(node);
				}
				if (node->parent) {
					if (node->parent->left == node) {
						node = node->parent;
						state = RIGHT;
					}
					else {
						node = node->parent;
						state = PARENT;
					}
				}
				else {
					state = START;
				}
				break;
			}
		} while (state != START);

		if (nodes.size() % 2 == 1) {
			sort(nodes.begin(), nodes.end());
			removeKey(nodes[(nodes.size() - 1) / 2]);
		}
	}
};

int main() {

	FILE *pFile = fopen("in.txt", "r");

	int node = 0;
	
	fscanf(pFile, "%d", &node);
	Tree * tree = new Tree(node);

	while (fscanf(pFile, "%d", &node) != EOF){
		tree->addKey(node);
	}


	tree->investigateTheTree();

	tree->traverse(SHOW_LEFT);

	return 0;
}