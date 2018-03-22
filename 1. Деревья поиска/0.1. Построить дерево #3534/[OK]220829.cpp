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

public:
	Tree(long long rootValue) {
		root = new Node(rootValue, nullptr, nullptr, nullptr);
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
};



int main() {
	FILE *pFile = fopen("input.txt", "r");

	long long node = 0;

	fscanf(pFile, "%lld", &node);
	Tree * tree = new Tree(node);

	while (fscanf(pFile, "%lld", &node) != EOF){
		tree->addKey(node);
	}

	tree->traverse(SHOW_LEFT);

	return 0;
}
