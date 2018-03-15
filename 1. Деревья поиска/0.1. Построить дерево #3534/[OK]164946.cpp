#include <iostream>
#include <fstream>

using namespace std;


ofstream fout("output.txt");

struct TreeItem {
	TreeItem* leftson;
	TreeItem* rightson;
	int value;
};


void add_el(int val, TreeItem *&Tree_) {
	if (NULL == Tree_) {
		Tree_ = new TreeItem;;
		Tree_->value = val;
		Tree_->leftson = Tree_->rightson = NULL;
	}

	if (val<Tree_->value)
	{
		if (Tree_->leftson != NULL)
			add_el(val, Tree_->leftson);
		else {
			Tree_->leftson = new TreeItem;
			Tree_->leftson->leftson = Tree_->leftson->rightson = NULL;
			Tree_->leftson->value = val;
		}
	}

	if (val>Tree_->value)
	{
		if (Tree_->rightson != NULL)
			add_el(val, Tree_->rightson);
		else {
			Tree_->rightson = new TreeItem;
			Tree_->rightson->leftson = Tree_->rightson->rightson = NULL;
			Tree_->rightson->value = val;
		}
	}
}

void leftbypass(TreeItem *&Tree_) {
	if (Tree_ != NULL) {
		fout << Tree_->value << endl;
		leftbypass(Tree_->leftson);
		leftbypass(Tree_->rightson);
	}
}

int main() {
	ifstream fin("input.txt");
	if (!fout)
	{
		cout << "file isn't created" << endl;
		return 1;
	}
	int value = 0;
	TreeItem* Tree = NULL;
	while (!fin.eof()) {
		fin >> value;
		add_el(value, Tree);
	}
	leftbypass(Tree);
	fin.close();
	return 0;
}

