#include <iostream>
#include <fstream>

using namespace std;


ofstream fout("output.txt");

long long summ=0;

struct TreeItem {
	TreeItem* leftson;
	TreeItem* rightson;
	int value;
};


void add_el(int val, TreeItem *&Tree_) {
	if (NULL == Tree_) {
		summ += val;
		Tree_ = new TreeItem;;
		Tree_->value = val;
		Tree_->leftson = Tree_->rightson = NULL;
	}

	if (val<Tree_->value)
	{
		if (Tree_->leftson != NULL)
			add_el(val, Tree_->leftson);
		else {
			summ += val;
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
			summ += val;
			Tree_->rightson = new TreeItem;
			Tree_->rightson->leftson = Tree_->rightson->rightson = NULL;
			Tree_->rightson->value = val;
		}
	}
}

int main() {
	ifstream fin("input.txt");
	int value = 0;
	TreeItem* Tree = NULL;
	while (!fin.eof()) {
		fin >> value;
		add_el(value,Tree);
	}
	fout << summ;
	fin.close();
	return 0;
}

