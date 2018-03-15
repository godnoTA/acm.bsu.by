#include <iostream>
#include <fstream>
#include <algorithm>
#include <iterator>
#include <vector>

using namespace std;

ofstream fout("out.txt");
long long maxl = 0, maxs = 0,root=0;
vector <long long> MAX;
vector<long long> maxvector;

struct TreeItem {
	TreeItem* leftson;
	TreeItem* rightson;
	long long value;
	long long height;
	vector <long long> way;
	vector <long long> coolson;
};


void add_el(long long val, TreeItem *&Tree_) {
	if (Tree_ == nullptr) {
		Tree_ = new TreeItem;
		Tree_->value = val;
		Tree_->leftson = nullptr;
		Tree_->rightson = nullptr;
	}

	if (val<Tree_->value)
	{
		if (Tree_->leftson != nullptr)
			add_el(val, Tree_->leftson);
		else {
			Tree_->leftson = new TreeItem;
			Tree_->leftson->leftson = nullptr;
			Tree_->leftson->rightson = nullptr;
			Tree_->leftson->value = val;
		}
	}

	if (val>Tree_->value)
	{
		if (Tree_->rightson != nullptr)
			add_el(val, Tree_->rightson);
		else {
			Tree_->rightson = new TreeItem;
			Tree_->rightson->leftson = nullptr;
			Tree_->rightson->rightson = nullptr;
			Tree_->rightson->value = val;
		}
	}
}


void inverse_bypass(TreeItem *&Tree_) {
	if (Tree_ != nullptr) {
		inverse_bypass(Tree_->leftson);
		inverse_bypass(Tree_->rightson);

		if (Tree_->rightson == nullptr && Tree_->leftson == nullptr) {
			Tree_->height = 0;
		}
		else {
			if (Tree_->rightson != nullptr && Tree_->leftson == nullptr) {
				Tree_->height = Tree_->rightson->height + 1;
				copy((Tree_->rightson->coolson).begin(), (Tree_->rightson->coolson).end(), back_inserter(Tree_->way));
				(Tree_->way).push_back(Tree_->rightson->value);
				(Tree_->way).push_back(Tree_->value);
				copy((Tree_->rightson->coolson).begin(), (Tree_->rightson->coolson).end(), back_inserter(Tree_->coolson));
				(Tree_->coolson).push_back(Tree_->rightson->value);
			}

			else {
				if (Tree_->rightson == nullptr && Tree_->leftson != nullptr) {
					Tree_->height = Tree_->leftson->height + 1;
					copy((Tree_->leftson->coolson).begin(), (Tree_->leftson->coolson).end(), back_inserter(Tree_->way));
					(Tree_->way).push_back(Tree_->leftson->value);
					(Tree_->way).push_back(Tree_->value);
					copy((Tree_->leftson->coolson).begin(), (Tree_->leftson->coolson).end(), back_inserter(Tree_->coolson));
					(Tree_->coolson).push_back(Tree_->leftson->value);
				}

				else {
					Tree_->height = max(Tree_->rightson->height, Tree_->leftson->height) + 1;
					if (Tree_->rightson->height < Tree_->leftson->height) {
						copy((Tree_->leftson->coolson).begin(), (Tree_->leftson->coolson).end(), back_inserter(Tree_->way));
						(Tree_->way).push_back(Tree_->leftson->value);
						copy((Tree_->leftson->coolson).begin(), (Tree_->leftson->coolson).end(), back_inserter(Tree_->coolson));
						(Tree_->coolson).push_back(Tree_->leftson->value);
						
						(Tree_->way).push_back(Tree_->value);

						copy((Tree_->rightson->coolson).begin(), (Tree_->rightson->coolson).end(), back_inserter(Tree_->way));
						(Tree_->way).push_back(Tree_->rightson->value);
					}
					else {
						copy((Tree_->rightson->coolson).begin(), (Tree_->rightson->coolson).end(), back_inserter(Tree_->way));
						(Tree_->way).push_back(Tree_->rightson->value);
						copy((Tree_->rightson->coolson).begin(), (Tree_->rightson->coolson).end(), back_inserter(Tree_->coolson));
						(Tree_->coolson).push_back(Tree_->rightson->value);

						(Tree_->way).push_back(Tree_->value);
						
						copy((Tree_->leftson->coolson).begin(), (Tree_->leftson->coolson).end(), back_inserter(Tree_->way));
						(Tree_->way).push_back(Tree_->leftson->value);
					}
				}
			}
		}
	}
}

void left_bypass(TreeItem *&Tree_) {
	if (Tree_ != nullptr) {
		fout << Tree_->value << endl;
		left_bypass(Tree_->leftson);
		left_bypass(Tree_->rightson);
	}
}

long long max_way(TreeItem *&Tree_) {
	long long sum = 0;
	vector<long long>::iterator the_iterator;
	if (Tree_ != NULL) {
		if ((Tree_->way).size() > maxl) {
			maxl = Tree_->way.size();
			the_iterator = (Tree_->way).begin();
			while (the_iterator != (Tree_->way).end()) {
				sum += *the_iterator++;
			}
			maxs = sum;
			maxvector.clear();
			copy(Tree_->way.begin(), Tree_->way.end(), back_inserter(maxvector));
			root = Tree_->value;
		}
		else {
			if((Tree_->way).size() == maxl) {
			the_iterator = (Tree_->way).begin();
			while (the_iterator != (Tree_->way).end())
				sum += *the_iterator++;
			if (sum > maxs) {
				maxs = sum;
				maxvector.clear();
				copy(Tree_->way.begin(), Tree_->way.end(), back_inserter(maxvector));
				root = Tree_->value;
			}
		}
		}
		max_way(Tree_->leftson);
		max_way(Tree_->rightson);
	}
//	MAX.clear();
//	copy(maxvector.begin(), maxvector.end(), back_inserter(MAX));
	return root;
}

TreeItem*& find_min(TreeItem*& min) {
	if (min->leftson == nullptr)
		return min;
	return find_min(min->leftson);
}

TreeItem*& right_del(long long val, TreeItem *&Tree_) {
	if (Tree_ == nullptr) {
		return Tree_;
	}

	if (val < Tree_->value)
		Tree_->leftson = right_del(val, Tree_->leftson);
	else {
		if (val > Tree_->value) {
			Tree_->rightson = right_del(val, Tree_->rightson);
		}
		else {
			if (Tree_->leftson != nullptr && Tree_->rightson != nullptr) {
				Tree_->value = find_min(Tree_->rightson)->value;
				Tree_->rightson = right_del(Tree_->value, Tree_->rightson);
			}
			else {
				if (Tree_->leftson != nullptr)
					Tree_ = Tree_->leftson;
				else
					Tree_ = Tree_->rightson;
			}
		}
	}
	return Tree_;
}



int main() {
	ifstream fin("in.txt");

	long long value;
	TreeItem* Tree = nullptr;
	while (!fin.eof()) {
		if (fin >> value)
			add_el(value, Tree);
	}
	inverse_bypass(Tree);
	long long root = max_way(Tree);
	long long center=-1;
	if (maxvector.size() % 2 != 0)
		center = maxvector[maxvector.size() / 2];
	if (root==center)
		right_del(root, Tree);
	else {
		if (center != -1)
			right_del(center,Tree);
		right_del(root,Tree);
	}
	left_bypass(Tree);
	fin.close();
	return 0;
}