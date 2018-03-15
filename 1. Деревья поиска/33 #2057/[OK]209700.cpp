#include <iostream>
#include <fstream>


class Tree {
	class TreeKey {
		int fDMin() {
			if (left->left != nullptr) {
				return left->fDMin();
			}
			else {
				int value;
				TreeKey *tmp = left;
				value = left->value;
				left = left->right;
				delete tmp;
				return value;
			}
		}
		void destroy3() {
			if (right->left == nullptr) {
				TreeKey *tmp = right;
				this->value = right->value;
				right = right->right;
				delete tmp;
			}
			else {
				value = right->fDMin();
			}
		}
	public:
		int height;
		TreeKey *left, *right, *parent;
		int value;
		int getHeight() {
			if (this == nullptr)
				return -1;
			return height;
		}
		TreeKey(int value, TreeKey* parent) :value(value), left(nullptr), right(nullptr), parent(parent) {}
		void add(int value) {
			if (value < this->value) {
				if (left != nullptr) {
					left->add(value);
				}
				else {
					left = new TreeKey(value, this);
				}
			}
			else if (value > this->value) {
				if (right != nullptr) {
					right->add(value);
				}
				else {
					right = new TreeKey(value, this);
				}
			}
		}
		void straith(std::ostream &out) {
			out << value << std::endl;
			if (left != nullptr)
				left->straith(out);
			if (right != nullptr)
				right->straith(out);
		}
		void destroy(int value) {
			if (value > this->value) {
				right->destroy2(value);
			}
			else if (value < this->value) {
				left->destroy2(value);
			}
			else {
				if (left != nullptr) {
					if (right != nullptr)
						destroy3();
					else {
						TreeKey* tmp = left;
						this->value = left->value;
						right = left->right;
						left = left->left;
						delete tmp;
					}
				}
				else {
					if (right != nullptr) {
						TreeKey* tmp = right;
						this->value = right->value;
						left = right->left;
						right = right->right;
						delete tmp;
					}
				}
			}
		}
		void destroy2(int value) {
			if (this == nullptr)
				return;
			if (this->value == value) {
				if (left != nullptr) {
					if (right != nullptr)
						destroy3();
					else {
						if (parent->left == this)
							parent->left = left;
						else
							parent->right = left;
						delete this;
					}
				}
				else {
					if (right != nullptr) {
						if (parent->left == this)
							parent->left = right;
						else
							parent->right = right;
						delete this;
					}
					else {
						if (parent->left == this)
							parent->left = nullptr;
						else
							parent->right = nullptr;
					}
				}
			}
			else {
				if (value > this->value) {
					right->destroy2(value);
				}
				else if (value < this->value) {
					left->destroy2(value);
				}
			}
		}
	}*root = nullptr;
	class Halfway {
	public:
		int max = 0;
		TreeKey* vertex = nullptr;
		void foundMaxADel() {
			int leftHeight = vertex->left->getHeight(), rightHeight = vertex->right->getHeight();
			if (max % 2 != 0)
				return;
			TreeKey* leaf = vertex;
			if (leftHeight == rightHeight)
				vertex->destroy(vertex->value);
			else {
				int left = 0; int right = 0;
				int middle = (leftHeight + rightHeight + 4) / 2;
				if (leftHeight < rightHeight) {
					left += leftHeight + 2;
					leaf = leaf->right;
				}
				else {
					right += rightHeight + 2;
					leaf = leaf->left;
				}
				while (true) {
					if (leaf->left->getHeight() >= leaf->right->getHeight()) {
						right++;
						if (right == middle)
							break;
						leaf = leaf->left;
					}
					else {
						left++;
						if (left == middle)
							break;
						leaf = leaf->right;
					}
				}
				leaf->destroy2(leaf->value);
			}
		}
	};
	int fDMin(TreeKey* v) {
		if (v->left != nullptr) {
			return fDMin(v->left);
		}
		else {
			return v->value;
		}
	}
	TreeKey* DeleteRecursively(TreeKey* v, int x) {
		if (v == nullptr) {
			return nullptr;
		}
		if (x > v->value) {
			v->right = DeleteRecursively(v->right, x);
			return v;
		}
		else if (x < v->value) {
			v->left = DeleteRecursively(v->left, x);
			return v;
		}
		if (v->left == nullptr) {
			return v->right;
		}
		else if (v->right == nullptr) {
			return v->left;
		}
		else {
			int minKey = fDMin(v->right);
			v->value = minKey;
			v->right = DeleteRecursively(v->right, minKey);
			return v;
		}
	}
public:
	int foundHeight(TreeKey* v, Halfway * max) {
		int leftHeight, rightHeight;
		if (v->left != nullptr)
			leftHeight = foundHeight(v->left, max);
		else
			leftHeight = -1;
		if (v->right != nullptr)
			rightHeight = foundHeight(v->right, max);
		else
			rightHeight = -1;
		int maxWay = 0;
		if (v->left != nullptr) {
			maxWay = leftHeight;
			maxWay++;
		}
		if (v->right != nullptr) {
			maxWay += rightHeight;
			maxWay++;
		}
		v->height = std::fmax(leftHeight, rightHeight ) + 1;
		if (max->vertex == nullptr) {
			max->max = maxWay;
			max->vertex = v;
		}
		else{
			if (max->max < maxWay) {
				max->max = maxWay;
				max->vertex = v;
			}
			else if (max->max == maxWay && v->value < max->vertex->value) {
				max->max = maxWay;
				max->vertex = v;
			}
		}
		return v->height;
	}
	Halfway* task33() {
		Halfway* way = new Halfway;
		foundHeight(root, way);
		way->foundMaxADel();
		return way;
	}
	void add(int value) {
		if (root == nullptr) {
			root = new TreeKey(value, nullptr);
		}
		else {
			root->add(value);
		}
	}
	void straith(std::ostream &out) {
		if (root != nullptr) {
			root->straith(out);
		}
	}
	void destroy(int value) {
		if (root != nullptr) {
			if (root->value != value || root->left != nullptr || root->right != nullptr) {
				root->destroy(value);
			}
			else {
				delete root;
				root = nullptr;
			}
		}
	}
	void DeleteRecursively(int x) {
		if (root != nullptr) {
			if (root->value != x || root->left != nullptr || root->right != nullptr)
				root = DeleteRecursively(root, x);
			else
				root = nullptr;
		}
	}
};

void main() {
	std::ifstream input("input.txt");
	std::ofstream output("output.txt");
	int key;
	Tree tree;
	while (input >> key)
	{
		tree.add(key);
	}
	tree.task33();
	tree.straith(output);
}