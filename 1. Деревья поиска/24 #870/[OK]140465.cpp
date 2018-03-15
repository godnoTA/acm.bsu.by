// binary_search_trees_24.cpp : Defines the entry point for the console application.
//

#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

struct tree_node {
	int key, depth;
	tree_node *left, *right;
	vector<int> vect;
};

void insert(int key, tree_node **node_ptp, int depth, int &tree_height) {
	if(*node_ptp == 0) {
		*node_ptp = new tree_node;
		(*node_ptp)->key = key;
		(*node_ptp)->depth = depth;
		(*node_ptp)->left = (*node_ptp)->right = 0;
		if(tree_height < depth)
			tree_height = depth;
	} else if(key < (*node_ptp)->key)
		insert(key, &(*node_ptp)->left, ++depth, tree_height);
	else if(key > (*node_ptp)->key)
		insert(key, &(*node_ptp)->right, ++depth, tree_height);
}

void pre_order_traversal(tree_node *node_ptr) {
	if(node_ptr != 0) {
		cout << node_ptr->key << endl;
		pre_order_traversal(node_ptr->left);
		pre_order_traversal(node_ptr->right);
	}
}

void write(ostream &out, tree_node *node_ptr) {
	if(node_ptr != 0) {
		out << node_ptr->key << endl;
		write(out, node_ptr->left);
		write(out, node_ptr->right);
	}
}

vector<int> find_hp_roots(tree_node *node_ptr, int tree_height, vector<tree_node*> *hp_roots) {
	if(node_ptr != 0) {
		if(node_ptr->left == 0 && node_ptr->right == 0) {
			node_ptr->vect.push_back(1);
			return node_ptr->vect;
		}
		node_ptr->vect = find_hp_roots(node_ptr->left, tree_height, hp_roots);
		vector<int> right_vect = find_hp_roots(node_ptr->right, tree_height, hp_roots);
		for(int i = 0; i < node_ptr->vect.size(); i++) {
			for(int j = 0; j < right_vect.size(); j++) {
				if(node_ptr->vect[i] + right_vect[j] == tree_height) {
					hp_roots->push_back(node_ptr);
					break;
				}
			}
			if(hp_roots->size() != 0 && hp_roots->back() == node_ptr)
				break;
		}
		node_ptr->vect.insert(node_ptr->vect.end(), right_vect.begin(), right_vect.end());
		for(int i = 0; i < node_ptr->vect.size(); i++)
			node_ptr->vect[i]++;
		return node_ptr->vect;
	}
	vector<int> empty_vect;
	return empty_vect;
}

struct have_max_depth {
	int depth_to_leave;
	have_max_depth(int max_depth) : depth_to_leave(max_depth) {}
	bool operator()(tree_node *curr_node_ptr) {
		return curr_node_ptr->depth != depth_to_leave;
	}
};

bool condition_for_sort(tree_node *first, tree_node *second) {
	return first->key < second->key;
}

void find_root_to_remove(vector<tree_node*> *hp_roots, tree_node **root_to_remove) {
	int max_depth = hp_roots->at(0)->depth;
	for(int i = 1; i < hp_roots->size(); i++) {
		if(max_depth < hp_roots->at(i)->depth)
			max_depth = hp_roots->at(i)->depth;
	}

	hp_roots->erase(remove_if(hp_roots->begin(), hp_roots->end(), have_max_depth(max_depth)), hp_roots->end());

	if(hp_roots->size() % 2 == 0)
		return;
	
	sort(hp_roots->begin(), hp_roots->end(), condition_for_sort);

	*root_to_remove = hp_roots->at((hp_roots->size() - 1) / 2);
}

int find_min_vertex(tree_node **node_ptr) {
	int min_vertex_value = (*node_ptr)->key;
	if((*node_ptr)->left != 0) {
		min_vertex_value = find_min_vertex(&(*node_ptr)->left);
	} else if((*node_ptr)->right != 0) {
		*node_ptr = (*node_ptr)->right;
	} else {
		*node_ptr = 0;
	}
	return min_vertex_value;
}

void remove_root(tree_node *root_to_remove) {
	int min_vertex_value = find_min_vertex(&root_to_remove->right);
	root_to_remove->key = min_vertex_value;
}

int main()
{
	ifstream in("tst.in");

	tree_node *tree_ptr = 0;
	int key, tree_height = 0;
	while(!in.eof()) {
		in >> key;
		insert(key, &tree_ptr, 0, tree_height);
	}

	in.close();

	vector<tree_node*> hp_roots;
	find_hp_roots(tree_ptr, tree_height, &hp_roots);

	tree_node *root_to_remove = 0;
	find_root_to_remove(&hp_roots, &root_to_remove);
	if(root_to_remove != 0)
		remove_root(root_to_remove);

	ofstream out("tst.out");

	write(out, tree_ptr);

	out.close();

	return 0;
}

