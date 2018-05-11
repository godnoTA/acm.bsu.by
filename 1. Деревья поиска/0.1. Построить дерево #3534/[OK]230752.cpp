#include <iostream>
#include <fstream>

std::ofstream fout("output.txt");

template<class T>
class bin_tree {
	T key;
	bin_tree*left, *right;
public:
	bin_tree() = delete;
	bin_tree(const T&a) :key(a), left(NULL), right(NULL) {}
	~bin_tree() {
		if (left != 0) {
			delete left;
		}
		if (right != 0) {
			delete right;
		}
	}

	void insert(const T&a) {
		if (a != key) {
			if (a < key)
				if (left != NULL)
					left->insert(a);
				else
					left = new bin_tree(a);
			else
				if (right != NULL)
					right->insert(a);
				else
					right = new bin_tree(a);
		}
	}

	void traverse() {
		fout << key << '\n';
		if (left)
			left->traverse();
		if (right)
			right->traverse();
	}

};

int main() {
	std::ifstream fin("input.txt");
	int key;
	fin >> key;
	bin_tree<int> bst(key);
	while (fin >> key){
		bst.insert(key);
	}
	bst.traverse();
	return 0;
}