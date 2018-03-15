#include <iostream>
#include <fstream>
class TreeKey {
public:
	TreeKey *left, *right;
	int value;
	TreeKey(int value) :value(value), left(nullptr), right(nullptr) {}
	void add(int value) {
		if (value < this->value) {
			if (left != nullptr) {
				left->add(value);
			}
			else {
				left = new TreeKey(value);
			}
		}
		else if (value > this->value) {
			if (right != nullptr) {
				right->add(value);
			}
			else {
				right = new TreeKey(value);
			}
		}
	}
	void straith(std::ostream &out) {
		out << value << std::endl;
		if(left != nullptr)
			left->straith(out);
		if (right != nullptr)
			right->straith(out);
	}
	~TreeKey() {
		delete left, right;
	}
};

void main() {
	std::ifstream input("input.txt");
	if (input.is_open()) {
		std::ofstream output("output.txt");
		int key;
		input >> key;
		TreeKey tree(key);
		while (input >> key) {
			tree.add(key);
		}
		tree.straith(output);
	}
}