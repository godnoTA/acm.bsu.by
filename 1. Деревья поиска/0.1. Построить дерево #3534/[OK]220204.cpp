#include <iostream>
#include <fstream>

struct TreeItem {
	int value;
	TreeItem* left;
	TreeItem* right;

	TreeItem(int _value) {
		this->value = _value;
		left = NULL;
		right = NULL;
	}
	
};

void left_round(TreeItem* &root, std::ofstream & fout);
void add_item(TreeItem *root, int number);

int main(int argc, char const *argv[])
{

	std::ifstream fin("input.txt");
	std::ofstream fout("output.txt");

	int number;

	fin >> number;
	TreeItem* root = new TreeItem(number);

	while(!fin.eof()) {
		fin >> number;
        add_item(root, number);
	}

	left_round(root, fout);
	return 0;
}


void left_round(TreeItem* &root, std::ofstream & fout) {
	TreeItem* now = root;
	if (now) {
		fout << now->value << std::endl;
		left_round(now->left, fout);
		left_round(now->right, fout);
	}
}


void add_item(TreeItem *root, int number) {
	TreeItem* now = root;
	TreeItem* add_item = new TreeItem(number);

	while(true) {
		if (number == now->value) return;

		if (number < now->value) {
			if (!now->left) {
				now->left = add_item;
				return;
			} else {
			now = now->left;
			}
		}
		else {
			if (!now->right) {
				now->right = add_item;
				return;
		} else {
			now = now->right;
			}
		}
	}		
}