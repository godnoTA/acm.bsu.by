#include <fstream>

using namespace std;

FILE *output = fopen("output.txt", "w");

enum State { START, LEFT, RIGHT, PARENT };
const int SHOW_LEFT = 1;
const int SHOW_RIGHT = 2;
const int SHOW_PARENT = 4;

class Tree {

	class Node {
	public:
		long long  value;
		Node * parent;
		Node * left;
		Node * right;

		Node(long long value, Node * left, Node * parent, Node * right) {
			this->value = value;
			this->left = left;
			this->parent = parent;
			this->right = right;
		}
	};
private:
	Node * root;

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
	Tree(long long rootValue) {
		root = new Node(rootValue, nullptr, nullptr, nullptr);
	}

	void addKey(long long x) {
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
		*cur = new Node(x, nullptr, *prev, nullptr);
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
					fprintf(output, "%lld", node->value);
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
					fprintf(output, "%lld", node->value);
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
					fprintf(output, "%lld", node->value);
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

	Node * foundKey(long long x) {
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

	bool removeKey(long long value) {
		return removeKey(this->foundKey(value));
	}
};

int main() {
	FILE *pFile = fopen("input.txt", "r");

	long long key = 0;
	long long node = 0;

	fscanf(pFile, "%lld", &key);
	fscanf(pFile, "%lld", &node);
	Tree * tree = new Tree(node);

	while (fscanf(pFile, "%lld", &node) != EOF){
		tree->addKey(node);
	}

	tree->removeKey(key);
	tree->traverse(SHOW_LEFT);

	return 0;
}