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
	if ( Tree_== NULL) {
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

TreeItem*& findmin(TreeItem*& min) {
	if (min->leftson == NULL)
		return min;
	return findmin(min->leftson);
}

TreeItem*& rightdel(int val,TreeItem *&Tree_) {
	if (Tree_ == NULL) {
		return Tree_;
	}

	if (val < Tree_->value) 
		Tree_->leftson = rightdel(val,Tree_->leftson);
	else {
		if (val > Tree_->value) {
			Tree_->rightson = rightdel(val, Tree_->rightson);
		}
		else {
			if (Tree_->leftson != NULL && Tree_->rightson != NULL) {
				Tree_->value = findmin(Tree_->rightson)->value;
				Tree_->rightson = rightdel(Tree_->value, Tree_->rightson);
			}
			else {
				if (Tree_->leftson != NULL)
					Tree_ = Tree_->leftson;
				else
					Tree_ = Tree_->rightson;
			}
		}
	}
	return Tree_;
}

int main() {
	ifstream fin("input.txt");

	int delvalue, value;
	fin >> delvalue;
	
	TreeItem* Tree = NULL;
	while (!fin.eof()) {
		if(fin >> value)
			add_el(value, Tree);
	}

	leftbypass(rightdel(delvalue, Tree));
	fin.close();
	return 0;
}
